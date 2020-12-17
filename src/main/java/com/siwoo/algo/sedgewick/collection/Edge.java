package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

public class Edge<E> {
    public final E v, w;

    public Edge(E v, E w) {
        checkNotNull(v, w);
        this.v = v;
        this.w = w;
    }

    public E either() {
        return v;
    }
    
    public E other(E u) {
        checkNotNull(u);
        if (v.equals(u)) return w;
        if (w.equals(u)) return v;
        throw new IllegalArgumentException();
    }

    public Edge<E> reverse() {
        return new Edge<>(w, v);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", v, w);
    }
}
