package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
 *
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.
 * 
 * D[N] 을 연속합의 최대 갯수라고 할때,
 *  경우의 수.
 *      1. N-1 원소와의 연속을 이어간다.
 *      2. N 부터 시작하는  새로운 연속합을 만든다.
 *      
 *  D[N] = Math.max(D[N-1] + A[i], A[i])
 *      // 이전 원소가 음수일 수 있으므로
 *      // 단, 음수의 연속합도 구해줘야 한다. (2, -1, 2) => 3
 *      
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1912 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Long[] D, A;
    private static int N;
    private static long MAX;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Long::parseLong)
                .toArray(Long[]::new);
        D = new Long[N];
        D[0] = A[0];    // 첫 번째 원소의 연속합은 자기 자신.
        MAX = D[0];
        dp(N-1);
        System.out.println(MAX);
    }

    private static long dp(int n) {
        if (D[n] != null) return D[n];
        dp(n-1);
        D[n] = Math.max(D[n-1] + A[n], A[n]);
        MAX = Math.max(MAX, D[n]);
        return D[n];
    }
}
