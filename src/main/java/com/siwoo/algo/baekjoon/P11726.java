package com.siwoo.algo.baekjoon;

import java.util.Scanner;

/**
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이
 * 
 * n 번째 열에서 타워를 세울 수 있는 경우의 수.
 *      1x2 한 개.    - 남은 타일의 크기 2x-n-1
 *      2x1 두 개.    - 남은 타일의 크기 2x-n-2
 *      
 * d[n] 을 2*n 크기의 직사각형에서 타일을 채울 수 있는 방법의 수라면
 *  d[n] = d[n-1] + d[n-2]
 */
public class P11726 {
    private static Scanner scanner = new Scanner(System.in);
    private static Integer[] D = new Integer[1001];
    private static int MOD = 10007;

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int cases = tile(N);
        System.out.println(cases % MOD);
    }

    private static int tile(int n) {
        if (n == 0) return 1;
        if (D[n] != null) return D[n];
        D[n] = tile(n-1);
        if (n - 2 >= 0)
            D[n] += tile(n-2);
        D[n] %= MOD;
        return D[n];
    }
}
