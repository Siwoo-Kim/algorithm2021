package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수열 A가 주어졌을 때, 그 수열의 증가 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
 * 
 * D[n] 이 n 번째 합이 가장 큰 증가 부분 수열이라 한다면,
 *  0 <= i < n && A[n] > A[i]
 *  D[n] = max(D[i]) + A[n]
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11055 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static Integer[] D;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Integer[N];
        dp(N-1);
        System.out.println(Arrays.stream(D).mapToInt(Integer::intValue).max().getAsInt());
    }

    private static void dp(int i) {
        if (i < 0) return;
        dp(i-1);
        int max = 0;
        for (int j=0; j<i; j++)
            if (A[i] > A[j])
                max = Math.max(max, D[j]);
        D[i] = max + A[i]; 
    }
}
