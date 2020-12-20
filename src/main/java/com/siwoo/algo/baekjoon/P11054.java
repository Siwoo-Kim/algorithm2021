package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
 *
 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,  {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
 *
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
 * 
 * 알고리즘
 *  => increaseLis(k) + decreaseLis(k) - 1 (k 중복)
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11054 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;
    private static Integer[] DD, DI;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        DD = new Integer[N];
        DI = new Integer[N];
        dd(0);
        di(N-1);
        int max = 0;
        for (int k=0; k<N; k++) {
            int left = DI[k];
            int right = DD[k];
            max = Math.max(left+right-1, max);
        }
        System.out.println(max);
    }
    
    private static void dd(int i) {
        if (i == N) return;
        dd(i+1);
        int max = 0;
        for (int j=i+1; j<N; j++)
            if (A[i] > A[j])
                max = Math.max(DD[j], max);
        DD[i] = max + 1;
    }
    
    private static void di(int i) {
        if (i < 0) return;
        di(i-1);
        int max = 0;
        for (int j=0; j<i; j++)
            if (A[i] > A[j])
                max = Math.max(DI[j], max);
        DI[i] = max + 1;
    }
}
