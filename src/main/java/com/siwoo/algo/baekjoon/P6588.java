package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Arrays;
import java.util.Scanner;

@Using(algorithm = Algorithm.MATH)
public class P6588 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX+1];

    public static void main(String[] args) {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<=MAX; i++)
            if (primes[i]) 
                for (int j=i+i; j<=MAX; j+=i)
                    primes[j] = false;
                
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = scanner.nextInt();
            if (N == 0) {
                System.out.println(sb);
                return;
            }
            for (int i=2; i<=MAX; i++) {
                if (primes[i] && primes[N-i])  {
                    sb.append(String.format("%d = %d + %d", N, i, N-i)).append("\n");
                    break;
                }
            }
        }
    }
}
