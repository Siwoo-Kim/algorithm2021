package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * s[i] 가 0..i 까지의 누적합이라면
 * a[i]...a[j] = s[j] - s[i-1]
 * 
 * (a - b) % m  ==  0 이 되려면
 *  a % m == b % m 
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P10986 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static long[] S;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        S = new long[N+1];
        token = new StringTokenizer(reader.readLine());
        for (int i=1; i<N; i++)
            S[i] = S[i-1] + Integer.parseInt(token.nextToken());
        Map<Long, Long> cnt = new HashMap<>();
        cnt.put(0L, 1L);
        long answer = 0;
        for (int i=1; i<=N; i++) {
            long x = S[i] % M;
            if (cnt.containsKey(x))
                answer += cnt.get(x);
            cnt.putIfAbsent(x, 0L);
            cnt.put(x, cnt.get(x) + 1);
        }
        System.out.println(answer);
    }
}

