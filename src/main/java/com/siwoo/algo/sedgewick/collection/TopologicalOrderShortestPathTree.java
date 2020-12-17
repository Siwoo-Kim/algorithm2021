package com.siwoo.algo.sedgewick.collection;

/**
 * [algo] [topological order spt]
 *  정점들을 위상 순서에 따라 이완하면, 가중 간선 DAG 에서의 단일 원점 최단 경로 문제를 풀 수 있다.
 *  
 * problem
 *  * 가중 DAG (directed acyclic graph) 에서 spt 을 구하고 싶다.
 *  
 * components
 *  1. topological order 
 *      어떤 정점 w 으로 가기 위한 경로는 위상 정렬에서 먼저 등장하므로, 그 순서에 따라
 *      간선을 이완하면 최종적으로 모든 정점의 v 에 대하여 distTo[w] <= v + e.weight 의 겨로가를 얻을 수 있다.
 *  2. 간선 이완.
 *   
 *  topological order 로 최장 트리 또한 구할 수 있다.
 *      Double.NEGATIVE_INFINITY 로 모든 정점의 거리를 업데이트를 하고, 간선 이완의 부호만 반전한다.
 */
public class TopologicalOrderShortestPathTree<E> implements ShortestPathTree<E> {
    private SymbolTable<E, WeightedDiedge<E>> edgeTo = new HashTable<>();
    private SymbolTable<E, Double> distTo = new HashTable<>();
    private final E source;

    public TopologicalOrderShortestPathTree(Digraph<E> G, E source) {
        this.source = source;
        for (E v: G.vertexes())
            distTo.put(v, Double.POSITIVE_INFINITY);
        distTo.put(source, 0.);
        edgeTo.put(source, null);
        
        TopologicalOrder<E> top = new TopologicalOrder<>(G);
        for (E e: top.getOrder())
            relax(e, G);
    }

    private void relax(E v, Digraph<E> G) {
        for (Diedge<E> e: G.edgeOf(v)) {
            WeightedDiedge<E> we = (WeightedDiedge<E>) e;
            E w = we.to();
            if (distTo.get(w) > distTo.get(v) + we.weight) {
                distTo.put(w, distTo.get(v) + we.weight);
                edgeTo.put(w, we);
            }
        }
    }

    @Override
    public double distanceTo(E v) {
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
