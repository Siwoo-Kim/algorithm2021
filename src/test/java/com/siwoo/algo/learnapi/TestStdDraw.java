package com.siwoo.algo.learnapi;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class TestStdDraw {

    public static void main(String[] args) {
//        int N = 100;
//        StdDraw.setXscale(0, N);
//        StdDraw.setYscale(0, N*N);
//        StdDraw.setPenRadius(.01);
//        for (int i=1; i<=N; i++) {
//            StdDraw.point(i, i);        // O(N)
//            StdDraw.point(i, i*i);  // O(N^2)
//            StdDraw.point(i, i*Math.log(i));    // O(logN)
//        }
//        
        int N = 50;
        double[] a = new double[N];
        for (int i=0; i<N; i++)
            a[i] = StdRandom.uniform();
        for (int i=0; i<N; i++) {
            double x = 1.0 * i/N;
            double y = a[i] / 2.0;
            double rw = 0.5/N;  // width
            double rh = a[i]/2.0;   // height
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
