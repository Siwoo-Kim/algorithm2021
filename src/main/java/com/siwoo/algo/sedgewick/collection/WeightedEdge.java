package com.siwoo.algo.sedgewick.collection;

public class WeightedEdge<E> extends Edge<E> implements Comparable<WeightedEdge<E>> {
    public final double weight;
    
    public WeightedEdge(E v, E w, double weight) {
        super(v, w);
        this.weight = weight;
    }

    @Override
    public WeightedEdge<E> reverse() {
        return new WeightedEdge<>(w, v, weight);
    }

    @Override
    public int compareTo(WeightedEdge<E> e) {
        return Double.compare(weight, e.weight);
    }

    @Override
    public String toString() {
        return String.format("%s - %s : %.02f", v, w, weight);
    }
}
