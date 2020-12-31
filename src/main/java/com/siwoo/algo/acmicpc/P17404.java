package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;
import jdk.jfr.Unsigned;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P17404 {
    private static final int MAX = 1000 * 1000 + 1;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, R = 0, G = 1, B = 2, BITSET = (1<<3)-1;
    private static int[][] D, A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N+1][3];
        D = new int[N+1][3];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int c=0; c<3; c++)
                A[i][c] = Integer.parseInt(token.nextToken());
        }
        int answer = MAX;
        for (int c=0; c<3; c++) {
            int subset = BITSET ^ (1 << c);
            for (int k=0; k<3; k++) {
                if ((subset & (1<<k)) != 0)
                    D[0][k] = MAX;
                else
                    D[0][k] = A[0][k];
            }
            dp(1, c);
            for (int k=0; k<3; k++) {
                if (k == c) continue;
                answer = Math.min(D[N][k], answer);
            }
        }
        System.out.println(answer);
    }

    private static void dp(int i, int color) {
        if (i == N+1) return;
        for (int c=0; c<3; c++) {
            int subset = BITSET ^ (1 << c);
            int min = MAX;
            for (int k=0; k<3; k++) {
                if ((subset & (1<<k)) != 0)
                    min = Math.min(min, D[i-1][k]);
            }
            if (i == N-1 && color == c)
                D[i][c] = MAX;
            else
                D[i][c] = min + A[i][c];
        }
        dp(i+1, color);
    }

    private static int min3(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
