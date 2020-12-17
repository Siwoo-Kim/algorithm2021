package com.siwoo.algo.sedgewick.collection;

import java.util.HashMap;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * [algo] [prim - mst]
 *  prim 알고리즘은 greedy 알고리즘으로 자르기 속성을 이용해 최소 가중치 횡단 간선을 선택하며 트리를 키워나간다.
 *  V-1 간선이 채워지면 mst 가 완성.
 *  
 * 
 * problem
 *  * 무방향 가중 간선 그래프에서 mst 을 구하고 싶다.
 *
 * components
 *  1. cut property {@link MinimumSpanningTree}
 *  2. priority queue {@link PriorityQueue}
 *  3. mst 에 속한 정점을 관리하는 visit
 *
 *  algorithm.
 *      1. 임의의 정점 s 을 선택해 s 의 모든 부속된 간선을 우선순위 큐에 넣는다.
 *      2. 큐에서 가장 가중치가 적은 간선을 꺼내 mst 에 추가한다. (이때 간선은 mst 와 그 외의 집합에 대한 횡단 간선이여야 한다.)
 *      3. 해당 v 에 부속된 간선 우선순위 큐에 넣는다. (인접한 정점 w 가 mst 에 소속되지 않았다면)
 *      
 * 
 * prim 알고리즘 개선하기.
 *  현재까지 mst 와 그외 정점 집합 no-mst 의 최소 가중치를 업데이트 하면 좀 더 많은 간선을 스킵할 수 있다.
 *  
 *  mst-w 의 간선에 대해서
 *      distTo[w] > edge.weight (현재까지 알려진 간선의 길이보다 현재 방문한 횡단 간선이 적다면)
 *      
 *  만약 우선순위큐가 index 삭제를 지원한다면 우선순위큐에는 최단 거리의 횡단 간선만 유지할 수 있다.
 *  (이 방법은 다익스트라에서도 유사하게 사용)
 *  
 *  time complexity
 *      * ElogE
 */
public class Prim<E> implements MinimumSpanningTree<E> {
    private double weight;
    private SymbolTable<E, LinkedList<WeightedEdge<E>>> MST = new HashTable<>();
    private final Double MAX = (double) 239583429;
    private int E;

    public Prim(Graph<E> G) {
        checkNotNull(G);
        ConnectedComponent<E> cc = new CC<>(G);
        if (cc.count() != 1)
            throw new IllegalArgumentException("Graph should be connected.");
        E source = G.vertexes().iterator().next();
        PriorityQueue<WeightedEdge<E>> pq = new PriorityQueue<>((e1, e2) -> e2.compareTo(e1));
        Set<E> visit = new Set<>(); //mst
        SymbolTable<E, Double> distTo = new HashTable<>();
        for (E v: G.vertexes()) {
            distTo.put(v, MAX);
            MST.put(v, new LinkedList<>());
        }
        distTo.put(source, 0.);
        visit(source, visit, pq, distTo, G);
        while (!pq.isEmpty() && E - 1 != MST.size()) {  // E = V-1
            WeightedEdge<E> edge = pq.dequeue();
            if (visit.contains(edge.v) 
                    && visit.contains(edge.w))  //skip both are already in mst  
                continue;
            addEdge(edge);
            visit(edge.w, visit, pq, distTo, G);
        }
    }

    private void visit(E v, Set<E> visit, 
                       PriorityQueue<WeightedEdge<E>> pq, 
                       SymbolTable<E, Double> distTo,
                       Graph<E> G) {
        visit.add(v);
        for (Edge<E> e: G.edgeOf(v)) {
            WeightedEdge<E> we = (WeightedEdge<E>) e;
            E w = we.other(v);
            // check w is already in mst
            if (visit.contains(w)) continue;
            // only shorter crossing edge is applicable
            if (distTo.get(w) <= we.weight) continue;
            distTo.put(we.other(v), we.weight);
            pq.enqueue(we);
        }
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public int sizeOfVertexes() {
        return MST.size();
    }

    @Override
    public int sizeOfEdges() {
        return E;
    }

    /**
     * add the edge into mst.
     * 
     * @param edge
     */
    @Override
    public void addEdge(Edge<E> edge) {
        WeightedEdge<E> we = (WeightedEdge<E>) edge;
        MST.get(edge.v).add(we);
        MST.get(edge.w).add(we.reverse());
        E++;
        weight += we.weight;
    }

    @Override
    public Iterable<E> vertexes() {
        return MST.keys();
    }

    @Override
    public Iterable<Edge<E>> edgeOf(E v) {
        LinkedList<Edge<E>> edges = new LinkedList<>();
        for (Edge<E> e: MST.get(v))
            edges.add(e);
        return edges;
    }
    
    @Override
    public String toString() {
        return asString();
    }
}
