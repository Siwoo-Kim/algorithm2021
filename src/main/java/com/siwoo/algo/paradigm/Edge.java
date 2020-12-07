package com.siwoo.algo.paradigm;

public class Edge<E> {
    final E v, w;

    public Edge(E v, E w) {
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
