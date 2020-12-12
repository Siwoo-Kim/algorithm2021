package com.siwoo.algo.baekjoon;

import com.sun.jndi.cosnaming.CNCtx;

import java.util.*;

public class P1158 {
    private static int N, K;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++)
            q.add(i);
        StringBuilder sb = new StringBuilder("<");
        int i = 1;
        while (!q.isEmpty()) {
            if (i % K == 0) {
                sb.append(q.poll())
                        .append(q.isEmpty()? "": ", ");
            } else
                q.add(q.poll());
            i++;
        }
        System.out.println(sb.append(">"));
    }
    
}
