package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [bellman-ford for shortest path tree]
 *  가중 간선 방향 그래프 G 에서, 원점 s 에서 도달 가능한 음의 순환이 없는 경우,
 *  단일 원점 s 로부터의 최단 경로 문제 (spt) 을 해결한다.
 *  
 * problem
 *  * 음수가 허용된 가중 간선 방향 그래프 G 에서 원점 s 에 대한 spt 을 구하고 싶다.
 *
 * components
 *  1. 음의 순환 (negative cycle)
 *      가중 간선 방향 그래프 G 에서 음의 순환은 방향 순환이면서 
 *      경로의 총합이 음수인 순환이다.
 *      
 *      음의 순환이 존재하는 그래프에선 최단 거리를 구할 수 없다.
 *      v -> w -> u -> v
 *       -66   33   3
 *       이 순환을 반복해서 돌면 무한대로 더 작은 가중치의 최단 경로가 만들 수 있다.
 *       
 *  algorithm.
 *      1. V-1 번 만큼 간선들의 이완을 시도한다.
 *      2. 이완할 정점을 모아둔 큐.
 *          distTo[u] 의 최단 거리에 변경을 가할 수 있는 간선은 
 *          반복 주기에서 distTo[v] 값이 바뀐 정점이란 점을 이용한다.
 *          (i 번 반복 주기에서 distTo[vi] 의 값이 바뀌지 않았다면 그 값은 최단 거리이다.
 *          모든 정점만큼 반복하였으므로)
 *          
 *      3. 중복 연산을 피하기 위한 set    
 *      
 *      음의 순환 탐지 알고리즘.
 *          음의 순환으로 큐가 계속 쌓이는 점을 이용한다.
 *          음의 순환에 대해서 distTo[w] > distTo[v] + e.weight 은 항상 새로운 값을 반환하므로.
 *          비용을 상쇄하기 위해 벨만 포드의 i 번의 주기가 끝난 뒤 검사를 실행한다.
 *          순환 로직은 {@link DirectedGraphCycle} 과 같다. (스택의 경로를 이용한 순환 탐지)
 *
 *  time complexity
 *      * EV
 */
public class BellmanFord<E> implements ShortestPathTree<E> {
    private SymbolTable<E, Double> distTo = new HashTable<>();
    private SymbolTable<E, Diedge<E>> edgeTo = new HashTable<>();
    private final E source;
    private int cost;
    private Iterable<E> cycle;

    public BellmanFord(Digraph<E> G, E source) {
        checkNotNull(G, source);
        this.source = source;
        for (E v: G.vertexes())
            distTo.put(v, Double.POSITIVE_INFINITY);
        distTo.put(source, 0.);
        edgeTo.put(source, null);
        Queue<E> q = new LinkedList<>();
        q.enqueue(source);
        Set<E> onQ = new Set<>();
        onQ.add(source);
        while (!q.isEmpty() && !hasNegativeCycle()) {
            E v = q.dequeue();
            onQ.delete(v);
            relax(v, q, onQ, G);
        }
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
    }

    private void relax(E v, Queue<E> q, Set<E> onQ, Digraph<E> G) {
        for (Diedge<E> e: G.edgeOf(v)) {
            WeightedDiedge<E> we = (WeightedDiedge<E>) e;
            E w = we.to();
            if (distTo.get(w) > distTo.get(v) + we.weight) {
                distTo.put(w, distTo.get(v) + we.weight);
                edgeTo.put(w, we);
                if (!onQ.contains(w)) {
                    q.enqueue(w);
                    onQ.add(w);
                }
            }
            if (cost++ % G.sizeOfVertexes() == 0) // 주기마다 확인한다
                findNegativeCycle();
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.size();
        Digraph<E> G = new DirectedGraph<>();
        for (E v: edgeTo.keys())
            G.addEdge(edgeTo.get(v));
        DirectedGraphCycle<E> cycle = new DirectedGraphCycle<>(G);
        this.cycle = cycle.cycle();
    }

    @Override
    public double distanceTo(E v) {
        checkNotNull(v);
        return distTo.get(v);
    }

    @Override
    public Iterable<Diedge<E>> edgeTo(E v) {
        Stack<Diedge<E>> stack = new LinkedList<>();
        for (Diedge<E> e=edgeTo.get(v); e!=null; e=edgeTo.get(v)) {
            stack.push(e);
            v = e.from();
        }
        return stack;
    }

    @Override
    public E source() {
        return source;
    }

    @Override
    public boolean hasPathTo(E v) {
        return distanceTo(v) < Double.POSITIVE_INFINITY;
    }
}   
