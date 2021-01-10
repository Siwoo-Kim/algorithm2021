package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Using;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static com.siwoo.algo.util.Algorithm.STACK;

@Using(algorithm = STACK)
public class P9093 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String[] strings = new String[N];
        for (int i=0; i<N; i++)
            strings[i] = reader.readLine();
        
        Stack<Character> stack = new Stack<>();
        
        while (!stack.isEmpty())
            System.out.println(stack.pop());
        
        for (String s: strings) {
            // I am happy
            // 0123456789
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                //if c is space then print all characters in the stack.
                //else push the c in the stack
            }
        }
    }
    
    private static void push(String s, int index, StringBuilder sb) {
        if (s.length() == index) return;
        push(s, index+1, sb);
        sb.append(s.charAt(index));
    }

}
