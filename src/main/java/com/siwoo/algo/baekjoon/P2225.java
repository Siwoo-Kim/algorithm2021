package com.siwoo.algo.baekjoon;

import java.util.Scanner;

public class P2225 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K, MOD = 1000000000;
    private static Integer[][] D;
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        D = new Integer[N+1][K+1];
        int cnt = go(N, K);
        System.out.println(cnt % MOD);
    }

    private static int go(int n, int k) {
        if (n == 0 && k == 0) return 1;
        if (D[n][k] != null) return D[n][k];
        int cnt = 0;
        for (int i=0; i<=N; i++)
            if (n-i >= 0 && k-1 >= 0) {
                cnt += go(n - i, k - 1);
                cnt %= MOD;
            }
        return D[n][k] = (cnt % MOD);
    }

}
