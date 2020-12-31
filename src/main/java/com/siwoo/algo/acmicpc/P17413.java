package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P17413 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char SEPARATOR = ' ', OPEN_BRACKET = '<', CLOSE_BRACKET = '>';
    
    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == OPEN_BRACKET) {
                while (!stack.isEmpty())
                    sb.append(stack.pop());
                while (s.charAt(i) != CLOSE_BRACKET)
                    sb.append(s.charAt(i++));
                sb.append(s.charAt(i));
            } else if (c == SEPARATOR) {
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
