package com.siwoo.algo.sedgewick.collection;

import edu.princeton.cs.algs4.StdIn;

public class TestBag {
    
    public static <E> Bag<E> getBags() {
        return null;
    } 
    
    public void stats() {
        Bag<Double> numbers = getBags();
        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());
        
        double sum = 0;
        for (double e: numbers)
            sum += e;
        double mean = sum / numbers.size();
        
        sum = 0;
        for (double e: numbers)
            sum += (e - mean) * (e - mean);
        double stddev = Math.sqrt(sum / (numbers.size()-1));

        System.out.printf("Mean: %.2f%n", mean);
        System.out.printf("Std dev: %.2f%n", stddev);
    }
}
