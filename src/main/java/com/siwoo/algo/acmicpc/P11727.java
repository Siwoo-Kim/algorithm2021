package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 아래 그림은 2×17 직사각형을 채운 한가지 예이다.
 * 
 * D[n] = D[n-1] + D[n-2] + D[n-2]
 *        1x2      2x1 * 2   2x2
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11727 {
    private static Scanner scanner = new Scanner(System.in);
    private static Long[] D;
    private static int MOD = 10007;

    public static void main(String[] args) {
        int N = scanner.nextInt();
        D = new Long[N+1];
        D[0] = 1L;
        long cnt = tile(N);
        System.out.println(cnt % MOD);
    }

    private static long tile(int n) {
        if (n == 0) return 1L;
        if (D[n] != null) return D[n];
        D[n] = tile(n-1);
        if (n - 2 >= 0)
            D[n] += tile(n-2) * 2;
        D[n] %= MOD;
        return D[n];
    }
}
