package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class P15654 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, MAX = 10000;
    private static int[] A;
    private static boolean[] visit = new boolean[MAX+1];

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(A);
        StringBuilder sb = permutation(new Stack<>(), new StringBuilder());
        System.out.println(sb);
    }

    private static StringBuilder permutation(Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == M) {
            for (int e: stack)
                sb.append(A[e]).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=0; i<N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                stack.push(i);
                permutation(stack, sb);
                stack.pop();
                visit[i] = false;
            }
        }
        return sb;
    }
}
