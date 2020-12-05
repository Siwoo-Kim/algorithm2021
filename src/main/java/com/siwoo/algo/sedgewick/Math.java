package com.siwoo.algo.sedgewick;

public class Math {
    
    //newton-raphson
    //a 의 sqrt(a) 은
    //xn = 1/2(xn-1 + a/xn-1)
    public static double sqrt(double a) {
        if (a < 0) return Double.NaN;
        double err = 1e-15;
        double n = a;
        while (java.lang.Math.abs(n - a/n) > err * n)
            n = (n + a/n) / 2.0;
        return n;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i=2; i*i<=n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(sqrt(12));
        System.out.println(sqrt(2));
        System.out.println(sqrt(256));

        System.out.println(isPrime(17));
        System.out.println(isPrime(111));
        System.out.println(isPrime(37));
        System.out.println(isPrime(1024));
    }
}
