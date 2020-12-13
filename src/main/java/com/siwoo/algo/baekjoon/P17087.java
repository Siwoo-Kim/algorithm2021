package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * gcd of a 배열 = gcd(g, a[i]), g = a[0]
 * 
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P17087 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(reader.readLine());
        int gcd = Math.abs(S - Integer.parseInt(token.nextToken()));
        for (int i=1; i<N; i++) {
            int b = Math.abs(S - Integer.parseInt(token.nextToken()));
            gcd = gcd(gcd, b);
        }
        System.out.println(gcd);
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
