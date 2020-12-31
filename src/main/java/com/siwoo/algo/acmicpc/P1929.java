package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Using;

import java.util.Arrays;
import java.util.Scanner;

import static com.siwoo.algo.util.Algorithm.MATH;

@Using(algorithm = MATH)
public class P1929 {
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
        int from = scanner.nextInt(),
                to = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i=from; i<=to; i++)
            if (primes[i])
                sb.append(i).append("\n");
        System.out.println(sb);
    }
}
