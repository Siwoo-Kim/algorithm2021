package com.siwoo.algo.baekjoon;

import java.util.Scanner;

/**
 * nCr = n! / n-r! * r!
 * 
 * pair(n!) - 2 와 5 의 약수의 쌍이라 할때
 * 
 * pair(n!) - pair(n-r!) - pair(r!)
 * 
 */
public class P2004 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        long N = scanner.nextLong(), R = scanner.nextLong();
        long twos = 0, fives = 0;
        for (long i=2; i<=N; i*=2)
            twos += N/i;
        for (long i=2; i<=(N-R); i*=2)
            twos -= (N-R)/i;
        for (long i=2; i<=R; i*=2)
            twos -= R/i;
        for (long i=5; i<=N; i*=5)
            fives += N/i;
        for (long i=5; i<=(N-R); i*=5)
            fives -= (N-R)/i;
        for (long i=5; i<=R; i*=5)
            fives -= R/i;
        System.out.println(Math.min(twos, fives));
    }
}
