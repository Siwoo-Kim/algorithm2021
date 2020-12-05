package com.siwoo.algo.sedgewick;

/**
 * 정수 p 와 q 의 최대 공약수는 gcd(p, q)
 *     q == 0 이라면 p.
 *     아니라면 p 을 나눈 q 의 나머지를 r 이라 할때,
 *     gcd(q, r)
 */
public class GCD {
    
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }

    public static void main(String[] args) {
        System.out.println(gcd(24, 18));
    }
    
}
