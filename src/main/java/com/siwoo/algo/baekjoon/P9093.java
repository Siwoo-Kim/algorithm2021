package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

import static com.siwoo.algo.util.Algorithm.STACK;

@Using(algorithm = STACK)
public class P9093 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            while (token.hasMoreTokens()) {
                push(token.nextToken(), 0, sb);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void push(String s, int index, StringBuilder sb) {
        if (s.length() == index) return;
        push(s, index+1, sb);
        sb.append(s.charAt(index));
    }

}
