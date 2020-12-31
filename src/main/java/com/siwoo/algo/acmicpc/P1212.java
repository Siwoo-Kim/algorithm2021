package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 8 진수를 2진수로
 * 8 진수의 n 자리의 수는 2 진수를 3 자리수로 나타낸 것과 같다.
 * 
 * 341
 * 3    4   1
 * 011 100 001
 * 
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
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
