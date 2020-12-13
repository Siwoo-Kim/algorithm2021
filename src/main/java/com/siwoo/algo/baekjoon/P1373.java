package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P1373 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        String octal = go(s, s.length()-1, 1).toString();
        System.out.println(octal);
    }

    private static StringBuilder go(String s, int i, int nth) {
        if (i < 0) return new StringBuilder();
        int value = 0;
        for (int j=0; j<3; j++) {
            int index = i-j;
            if (index < 0) break;
            if (s.charAt(i-j) == '1')
                value += 1 << j;
        }
        StringBuilder sb = go(s, i-3, nth+1);
        sb.append(value);
        return sb;
    }
}
