package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Using(algorithm = Algorithm.STACK)
public class P9012 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            String s = reader.readLine();
            int depth = stack(s, 0, 0);
            sb.append(depth == 0 ? "YES": "NO")
                    .append("\n");
        }
        System.out.println(sb);
    }

    private static int stack(String s, int index, int depth) {
        if (index == s.length()) return depth;
        if (depth < 0) return -1;
        char c = s.charAt(index);
        return stack(s, index+1, c == '('? depth+1: depth-1);
    }
}
