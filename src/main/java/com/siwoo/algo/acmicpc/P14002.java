package com.siwoo.algo.acmicpc;

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
 * D[n] 을 n 자리 수 가장 긴 lis 라 한다면
 *  D[n] = max(D[i]) + 1, 단 0 <= i < n && A[i] < A[n]
 * 
 * 역추적
 *  - 어떤 수의 정답 이전의 답을 알아내는 방법.
 *  - 만약 v 의 정답을 w 로 의한 것이라면 p[v] = w 에 저장.
 *  
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P14002 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer[] D, P, A;
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        D = new Integer[N];
        P = new Integer[N];
        dp(N-1);
        int k = 0;
        for (int i=1; i<N; i++) {
            if (D[k] < D[i])
                k = i;
        }
        System.out.println(D[k]);
        print(k);
    }

    private static void print(int k) {
        if (k == P[k]) {
            System.out.print(A[k] + " ");
            return;
        }
        print(P[k]);
        System.out.print(A[k] + " ");
    }

    private static int dp(int n) {
        if (n < 0) return 0;
        if (D[n] != null) return D[n];
        int lis = 0, k = n;
        for (int i=0; i<n; i++) {
            int candidate = dp(i);
            if (lis < candidate && A[i] < A[n]) {
                lis = candidate;
                k = i;
            }
        }
        P[n] = k;
        return D[n] = lis + 1;
    }
}
