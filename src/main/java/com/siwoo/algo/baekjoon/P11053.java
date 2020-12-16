package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 * 
 * D[n] 을 n 자리수의 lis 라 한다면,
 * D[n] = max(D[i] + 1), 단 A[i] < A[n] && 0 <= i < n
 * 
 * lis = max(D[i])
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11053 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer[] D;
    private static int[] A;
    private static int N, MAX;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Integer[N];
        dp(N-1);
        System.out.println(MAX);
    }

    private static int dp(int n) {
        if (n < 0) return 0;
        if (D[n] != null) return D[n];
        dp(n-1);
        int lis = 0;
        for (int i=0; i<n; i++)
            if (A[i] < A[n])
                lis = Math.max(lis, dp(i));
        D[n] = lis + 1;
        MAX = Math.max(D[n], MAX);
        return D[n];
    }
}
