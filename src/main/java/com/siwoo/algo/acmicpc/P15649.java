package com.siwoo.algo.acmicpc;

import java.util.Scanner;
import java.util.Stack;

public class P15649 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        StringBuilder answer = permutation(new Stack<>(), new boolean[N+1], new StringBuilder());
        System.out.println(answer);
    }

    private static StringBuilder permutation(Stack<Integer> stack, boolean[] visit, StringBuilder sb) {
        if (stack.size() == M) {
            for (int item: stack)
                sb.append(item).append(" ");
            sb.append("\n");
            return sb;
        }
        for (int i=1; i<=N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                stack.push(i);
                permutation(stack, visit, sb);
                stack.pop();
                visit[i] = false;
            }
        }
        return sb;
    }
}
