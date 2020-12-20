package com.siwoo.algo.baekjoon;

import java.util.Scanner;

public class P15988 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N = 1000000, MOD = 1000000009;
    private static Long[] D = new Long[N+1];
    
    public static void main(String[] args) {
        D[0] = 1L;
        for (int i=1; i<=N; i++) {
            D[i] = getD(i-1);
            D[i] += getD(i-2);
            D[i] += getD(i-3);
            D[i] %= MOD;
        }
        int T = scanner.nextInt();
        for (int i=0; i<T; i++)
            System.out.println(getD(scanner.nextInt()));
    }

    private static Long getD(int i) {
        if (i >= 0) return D[i] = (D[i] % MOD);
        else return 0L;
    }
}
