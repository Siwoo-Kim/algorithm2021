package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

public class Edge<E> {
    public final E v, w;

    public Edge(E v, E w) {
        checkNotNull(v, w);
        this.v = v;
        this.w = w;
    }
    
    public Edge<E> reverse() {
        return new Edge<>(w, v);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", v, w);
    }
}
