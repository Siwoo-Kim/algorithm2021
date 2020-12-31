package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Using(algorithm = Algorithm.STACK)
public class P1406 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        Stack<String> left = new Stack<>(),
                right = new Stack<>();
        for (char c: reader.readLine().toCharArray())
            left.push(Character.toString(c));
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            String[] data = reader.readLine().split("\\s+");
            if ("P".equals(data[0]))
                left.push(data[1]);
            if ("L".equals(data[0]) && !left.isEmpty())
                right.push(left.pop());
            if ("D".equals(data[0]) && !right.isEmpty())
                left.push(right.pop());
            if ("B".equals(data[0]) && !left.isEmpty())
                left.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty())
            sb.append(left.pop());
        sb.reverse();
        while (!right.isEmpty())
            sb.append(right.pop());
        System.out.println(sb.toString());
    }
}

