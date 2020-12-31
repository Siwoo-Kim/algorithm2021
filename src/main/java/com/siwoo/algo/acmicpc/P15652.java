package com.siwoo.algo.acmicpc;

import java.util.Scanner;
import java.util.Stack;

public class P15652 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        StringBuilder sb = combination(1, new Stack<>(), new StringBuilder());
        System.out.println(sb);
    }

    private static StringBuilder combination(int index, Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == M) {
            for (int e: stack)
                sb.append(e).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=index; i<=N; i++) {
            stack.push(i);
            combination(i, stack, sb);
            stack.pop();
        }
        return sb;
    }
}
