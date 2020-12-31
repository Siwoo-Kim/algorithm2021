package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11659 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        S = new int[N];
        token = new StringTokenizer(reader.readLine());
        S[0] = Integer.parseInt(token.nextToken());
        for (int i=1; i<N; i++) {
            int v = Integer.parseInt(token.nextToken());
            S[i] = S[i-1] + v;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(token.nextToken()) - 1,
                    to = Integer.parseInt(token.nextToken()) - 1;
            sb.append(S[to] - (from == 0? 0: S[from-1]))
                    .append("\n");
        }
        System.out.println(sb);
    }
    
}
