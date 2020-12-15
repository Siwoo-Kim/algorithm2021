package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectedGraph<E> implements Digraph<E> {
    private SymbolTable<E, LinkedList<DiEdge<E>>> G = new HashTable<>();
    private int E;
    
    @Override
    public Iterable<E> adj(E v) {
        checkNotNull(v);
        LinkedList<E> adjs = new LinkedList<>();
        for (DiEdge<E> e: G.get(v))
            adjs.add(e.to());            
        return adjs;
    }

    @Override
    public Iterable<E> vertexes() {
        return G.keys();
    }

    @Override
    public void addEdge(DiEdge<E> edge) {
        checkNotNull(edge);
        if (!G.contains(edge.from()))
            G.put(edge.from(), new LinkedList<>());
        if (!G.contains(edge.to()))
            G.put(edge.to(), new LinkedList<>());
        G.get(edge.from()).add(edge);
        E++;
    }

    @Override
    public Digraph<E> reverse() {
        Digraph<E> digraph = new DirectedGraph<>();
        for (E v: G.keys())
            for (Edge<E> e: G.get(v))
                digraph.addEdge(e.reverse());
        return digraph;
    }

    @Override
    public int sizeOfVertexes() {
        return G.size();
    }

    @Override
    public int sizeOfEdges() {
        return E;
    }

    @Override
    public String toString() {
        return asString();
    }
}
