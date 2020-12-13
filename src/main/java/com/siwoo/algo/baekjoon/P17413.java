package com.siwoo.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P17413 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int j=0; j<s.length(); j++) {
            char c = s.charAt(j);
            if (c == '<') {
                while (!stack.isEmpty())
                    sb.append(stack.pop());
                sb.append(c);
                while (c != '>') {
                    c = s.charAt(++j);
                    sb.append(c);
                }
            } else if (c == ' ') {
                while (!stack.isEmpty())
                    sb.append(stack.pop());
                sb.append(c);
            } else 
                stack.push(c);
        }
        while (!stack.isEmpty())
            sb.append(stack.pop());
        System.out.println(sb);
    }

}
