package com.siwoo.algo.sedgewick.collection;

public class DiEdge<E> extends Edge<E> {

    public DiEdge(E v, E w) {
        super(v, w);
    }
    
    public E from() {
        return v;
    }
    
    public E to() {
        return w;
    }
}
