package com.siwoo.algo.sedgewick.collection;

public class Diedge<E> extends Edge<E> {

    public Diedge(E v, E w) {
        super(v, w);
    }
    
    public E from() {
        return v;
    }
    
    public E to() {
        return w;
    }

    @Override
    public Diedge<E> reverse() {
        return new Diedge<>(to(), from());
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", v, w);
    }
}
