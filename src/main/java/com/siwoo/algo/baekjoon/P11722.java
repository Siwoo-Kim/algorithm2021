package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수열 A가 주어졌을 때, 가장 긴 감소하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {10, 30, 10, 20, 20, 10} 인 경우에 가장 긴 감소하는 부분 수열은 A = {10, 30, 10, 20, 20, 10}  이고, 길이는 3이다.
 * 
 * D[n] 이 n 번째 길이가 가장 킨 감소 부분 수열이라 한다면,
 *  0 <= i < n && A[n] < A[i]
 *  D[n] = max(D[i]) + 1
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11722 {
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
        System.out.println(
                Arrays.stream(D)
                        .mapToInt(Integer::intValue)
                        .max()
                        .getAsInt());
    }

    private static void dp(int i) {
        if (i < 0) return;
        dp(i-1);
        int max = 0;
        for (int j=0; j<i; j++)
            if (A[j] > A[i])
                max = Math.max(D[j], max);
        D[i] = max + 1;
    }
}
