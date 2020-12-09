package com.siwoo.algo.util;

import edu.princeton.cs.algs4.StdDraw;

public class VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }
    
    public void addDataValue(double value) {
        N++;
        total += value;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, value);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, mean());
    }
    
    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "VisualAccumulator{" +
                "total=" + total +
                ", N=" + N +
                '}';
    }
}
