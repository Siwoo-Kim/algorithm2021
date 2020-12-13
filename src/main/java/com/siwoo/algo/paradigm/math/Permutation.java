package com.siwoo.algo.paradigm.math;

public class Permutation {
    
    public static long factorial(long N) {
        if (N == 0) return 1;
        return N * factorial(N-1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(10));
    }
}
