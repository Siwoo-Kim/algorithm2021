package com.siwoo.algo.acmicpc;

import java.util.Scanner;
import java.util.Stack;

public class P15651 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        StringBuilder sb = permutation(new Stack<>(), new StringBuilder());
        System.out.println(sb);
    }

    private static StringBuilder permutation(Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == M) {
            for (int e: stack)
                sb.append(e).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=1; i<=N; i++) {
            stack.push(i);
            permutation(stack, sb);
            stack.pop();
        }
        return sb;
    }
}
