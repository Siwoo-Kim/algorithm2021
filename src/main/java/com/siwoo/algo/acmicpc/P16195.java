package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P16195 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 1000, MOD = 1000000009;
    private static long[][] D = new long[MAX+1][MAX+1];

    public static void main(String[] args) {
        D[0][0] = 1;
        for (int i=1; i<=MAX; i++) {
            for (int m=1; m<=i; m++) {
                D[i][m] = D[i-1][m-1];
                if (i-2 >= 0)
                    D[i][m] += D[i-2][m-1];
                if (i-3 >= 0)
                    D[i][m] += D[i-3][m-1];
                D[i][m] %= MOD;
            }
        }
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int n = scanner.nextInt(),
                    m = scanner.nextInt();
            long answer = 0;
            for (int j=1; j<=m; j++) {
                answer += D[n][j];
                answer %= MOD;
            }
            System.out.println(answer);
        }
    }
}
