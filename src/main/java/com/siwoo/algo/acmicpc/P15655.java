package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class P15655 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(A);
        StringBuilder sb = combination(0, new Stack<>(), new StringBuilder());
        System.out.println(sb);
    }

    private static StringBuilder combination(int index, Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == M) {
            for (int e: stack)
                sb.append(A[e]).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=index; i<N; i++) {
            stack.push(i);
            combination(i+1, stack, sb);
            stack.pop();
        }
        return sb;
    }
}
