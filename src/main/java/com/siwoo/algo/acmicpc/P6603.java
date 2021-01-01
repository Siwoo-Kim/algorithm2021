package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P6603 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M = 6;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            A = new int[N];
            for (int i=0; i<N; i++)
                A[i] = Integer.parseInt(token.nextToken());
            combination(0, N, M, sb, new Stack<>());
            sb.append("\n");
            if (N == 0) {
                System.out.println(sb.toString().trim());
                return;
            }
        }
    }
    
    public static void combination(int index, int N, int M, StringBuilder sb, Stack<Integer> stack) {
        if (stack.size() == M) {
            for (int e: stack)
                sb.append(e).append(" ");
            sb.append("\n");
        } else {
            for (int i=index; i<N; i++) {
                stack.push(A[i]);
                combination(i+1, N, M, sb, stack);
                stack.pop();
            }
        }
    }
}
