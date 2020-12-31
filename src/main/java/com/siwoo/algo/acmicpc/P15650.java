package com.siwoo.algo.acmicpc;

import java.util.Scanner;
import java.util.Stack;

public class P15650 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        StringBuilder answer = combination(1, N, M, new Stack<>(), new StringBuilder());
        System.out.println(answer);
    }

    private static StringBuilder combination(int index, int N, int M, Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == M) {
            for (int item: stack)
                sb.append(item).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=index; i<=N; i++) {
            stack.push(i);
            combination(i+1, N, M, stack, sb);
            stack.pop();
        }
        return sb;
    }
}
