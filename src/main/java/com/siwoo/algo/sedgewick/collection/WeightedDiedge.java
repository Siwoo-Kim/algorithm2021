package com.siwoo.algo.sedgewick.collection;

public class WeightedDiedge<E> extends Diedge<E> implements Comparable<WeightedDiedge<E>> {
    public final double weight;
    
    public WeightedDiedge(E v, E w, double weight) {
        super(v, w);
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightedDiedge<E> o) {
        return Double.compare(weight, o.weight);
    }
    
    @Override
    public String toString() {
        return String.format("%s: %.02f", super.toString(), weight);
    }
}
