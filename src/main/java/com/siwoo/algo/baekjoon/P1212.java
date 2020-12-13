package com.siwoo.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1212 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        StringBuilder sb = go(s, new StringBuilder(), 0);
        System.out.println(sb.length() == 0? 0: sb);
    }

    private static StringBuilder go(String s, StringBuilder sb, int i) {
        if (i == s.length()) return sb;
        int octal = s.charAt(i) - '0';
        for (int bit=2; bit>=0; bit--)
            if ((octal & (1 << bit)) != 0)
                sb.append(1);
            else if (!(i == 0 && sb.length() == 0))
                sb.append(0);
        go(s, sb, i+1);
        return sb;
    }
}
