package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.paradigm.graph.UnionFind;
import com.siwoo.algo.paradigm.graph.ZippedUnionFind;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [kruskal]
 *  가중치 순의 간선 e 가 두 mst 집합의 횡단 간선이라면 e 을 추가하고 두 집합을 병합한다.
 *  이때 모든 mst 집합이 하나로 병합되었다면 mst 을 구한 것.
 *  
 * problem
 *  * 무방향 가중 그래프 G 에서 mst 을 구하고 싶다.
 *
 * components
 *  1. crossing edge
 *  2. union-find   (두 mst 집합의 순환 확인을 위한)
 *  3. priority queue (가중치 순으로 처리하기 위한)
 *
 *  time complexity
 *      * ElogE
 */
public class Kruskal<E> implements MinimumSpanningTree<E> {
    private SymbolTable<E, LinkedList<WeightedEdge<E>>> MST = new HashTable<>();
    private double weight;
    private int E;
    
    public Kruskal(Graph<E> G) {
        checkNotNull(G);
        if (G.sizeOfVertexes() == 0) return;
        UnionFind<E> uf = new ZippedUnionFind<>();
        for (E v: G.vertexes()) {
            uf.put(v);
            MST.put(v, new LinkedList<>());
        }
        PriorityQueue<WeightedEdge<E>> pq = new PriorityQueue<>((e1, e2) -> e2.compareTo(e1));
        for (Edge<E> e: G.edges()) {
            WeightedEdge<E> we = (WeightedEdge<E>) e;
            pq.enqueue(we);
        }
        while (!pq.isEmpty() && E != G.sizeOfEdges()-1) {   // E = V-1
            WeightedEdge<E> e = pq.dequeue();
            E v = e.v;
            if (uf.connected(v, e.other(v))) continue;
            uf.union(v, e.other(v));
            addEdge(e);
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
     * add the edge into the mst.
     * 
     * @param edge
     */
    @Override
    public void addEdge(Edge<E> edge) {
        WeightedEdge<E> we = (WeightedEdge<E>) edge;
        MST.get(we.v).add(we);
        MST.get(we.w).add(we.reverse());
        weight += we.weight;
        E++;
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
}
