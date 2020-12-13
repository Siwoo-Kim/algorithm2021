package com.siwoo.algo.paradigm.math;

/**
 * 최대 공약수. greatest common divisor
 *  어떤 수 a, b 의 공통된 약수 중 가장 큰 수. 
 *      a % m == 0 && b % m == 0, 1 <= m <= min(a, b)
 *  a, b 의 gcd 이 1 이라면 서로소 (comprime).
 * 
 * 최소 공배수. least common multiple
 *      a * b = gcd * lcm
 *      lcm = gcd * (a/gcd) * (b/gcd)
 */
public class GCD {
    
    //유클리드 호제법. euclidean algorithm
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        int g = gcd(a, b);
        return g * (a/g) * (b/g);
    }
    public static void main(String[] args) {
        System.out.println(gcd(24, 12));
        System.out.println(lcm(24, 12));
    }
}
