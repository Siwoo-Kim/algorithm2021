package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1932 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] A;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N][N];
        D = new Integer[N][N];
        for (int i=0; i<N; i++)
            A[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        D[0][0] = A[0][0];
        dp(N);
        int max = 0;
        for (int i=0; i<N; i++)
            max = Math.max(D[N-1][i], max);
        System.out.println(max);
    }

    private static void dp(int n) {
        if (n == 1) return;
        dp(n-1);
        for (int i=0; i<n; i++) {
            D[n-1][i] = Math.max(getD(n-1, i-1), getD(n-1, i));
            D[n-1][i] += A[n-1][i];
        }
    }

    private static int getD(int n, int j) {
        if (j < 0) return 0;
        if (j == n) return 0;
        return D[n-1][j];
    }
}
