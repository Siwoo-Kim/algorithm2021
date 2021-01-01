package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P1759 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, L;
    private static String[] S;
    private static Set<String> VOWELS = new HashSet<>();
    
    static {
        VOWELS.add("a");
        VOWELS.add("e");
        VOWELS.add("i");
        VOWELS.add("o");
        VOWELS.add("u");
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        L = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        S = reader.readLine().split("\\s+");
        Arrays.sort(S);
        StringBuilder sb = select(0, new Stack<>(), new StringBuilder());
        System.out.println(sb);
    }

    private static StringBuilder select(int index, Stack<Integer> stack, StringBuilder sb) {
        if (stack.size() == L) {
            int v = 0, c = 0;
            StringBuilder s = new StringBuilder();
            for (int i: stack) {
                s.append(S[i]);
                if (VOWELS.contains(S[i]))
                    v++;
                else 
                    c++;
            }
            if (v >= 1 && c >= 2)
                sb.append(s).append("\n");
            return sb;
        }
        if (index == N) return sb;
        stack.push(index);
        select(index+1, stack, sb);
        stack.pop();
        select(index+1, stack, sb);
        return sb;
    }
}
