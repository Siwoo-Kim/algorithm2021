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
 * 배열 a 에서 부분합 a[i]..a[j] == k 인 i,j 쌍의 갯수.
 * 
 * s[i] = a[0]...a[i] 까지의 합.
 * 
 * a[i]..a[j] = s[j] - s[i-1]
 * 
 * a[i]..a[j] == k 을 만족하는 i,j 쌍의 갯수는
 * s[j]..s[i-1] == k 을 j 에 대해서 만족하는 i 의 갯수와 동일.
 * 
 * s[j] + y == k 
 * 
 * cnt[k] = S[i] == k 인 i 의 갯수를 저장.
 * 
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P2015 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        int[] S = new int[N+1];
        token = new StringTokenizer(reader.readLine());
        for (int i=1; i<S.length; i++)
            S[i] = S[i-1] + Integer.parseInt(token.nextToken());
        Map<Integer, Long> cnt = new HashMap<>();
        long answer = 0;
        for (int i=0; i<S.length; i++) {
            int y = S[i] - K;
            if (cnt.containsKey(y))
                answer += cnt.get(y);
            cnt.putIfAbsent(S[i], 0L);
            cnt.put(S[i], 1 + cnt.get(S[i]));
        }
        System.out.println(answer);
    }
}
