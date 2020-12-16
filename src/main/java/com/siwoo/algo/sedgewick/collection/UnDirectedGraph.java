package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Adjacency-list graph
 *      
 * time complexity
 *  space,  add edge,   edge between v and w,   iterate over v adjacent to v
 *  V+E      1              degree(v)                 degree(v)
 */
public class UnDirectedGraph<E> implements Graph<E> {
    private SymbolTable<E, LinkedList<Edge<E>>> G = new HashTable<>();
    private int edges;
    
    @Override
    public int sizeOfVertexes() {
        return G.size();
    }

    @Override
    public int sizeOfEdges() {
        return edges;
    }

    @Override
    public void addEdge(Edge<E> edge) {
        if (!G.contains(edge.v))
            G.put(edge.v, new LinkedList<>());
        if (!G.contains(edge.w))
            G.put(edge.w, new LinkedList<>());
        G.get(edge.v).add(edge);
        G.get(edge.w).add(edge.reverse());
        edges++;
    }

    @Override
    public Iterable<E> adj(E v) {
        LinkedList<E> list = new LinkedList<>();
        for (Edge<E> e: G.get(v))
            list.add(e.w);
        return list;
    }

    @Override
    public Iterable<E> vertexes() {
        return G.keys();
    }

    @Override
    public Iterable<Edge<E>> edgeOf(E v) {
        checkNotNull(v);
        LinkedList<Edge<E>> edges = new LinkedList<>();
        for (Edge<E> e: G.get(v))
            edges.add(e);
        return edges;
    }

    @Override
    public String toString() {
        return asString();
    }
}
