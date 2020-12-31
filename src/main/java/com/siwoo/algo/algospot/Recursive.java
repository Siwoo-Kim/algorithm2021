package com.siwoo.algo.algospot;

import java.util.Stack;

public class Recursive {
    
    public static int sum(int N) {
        if (N == 1) return N;
        return N + sum(N-1);
    }

    public static void pick(int N, int M, Stack<Integer> picked) {
        if (picked.size() == M) {
            StringBuilder sb = new StringBuilder();
            for (int i: picked)
                sb.append(i).append(" ");
            System.out.println(sb.append("\n"));
            return;
        }
        int smallest = picked.isEmpty()? 0: picked.peek()+1;
        for (; smallest<N; smallest++) {
            picked.push(smallest);
            pick(N, M, picked);
            picked.pop();
        }
    }


    public static void main(String[] args) {
        System.out.println(sum(10));
        pick(7, 4, new Stack<>());
    }
}
