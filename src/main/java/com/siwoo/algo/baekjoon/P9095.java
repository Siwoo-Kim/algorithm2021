package com.siwoo.algo.baekjoon;

import java.util.Scanner;

/**
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 *
 * 1+1+1+1
 * 1+1+2
 * 1+2+1
 * 2+1+1
 * 2+2
 * 1+3
 * 3+1
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * D[n] 을 1, 2, 3 의 합으로 나타내는 경우의 수라고 가정한다면,
 *      어떤 수 x 에 1 을 더해 n 을 만드는 경우의 수 D[n-1]
 *      어떤 수 x 에 2 을 더해 n 을 만드는 경우의 수 D[n-2]
 *      어떤 수 x 에 3 을 더해 n 을 만드는 경우의 수 D[n-3]
 *
 *      D[n] = D[n-1] + D[n-2] + D[n-3]
 *          
 * 1 = 1
 * 2 = 1+1
 *     2
 * 3 = 1+1+1
 *     2+1
 *     1+2
 *     3
 * 4 = 1+1+1+1   //D[4-1]
 *     2+1+1     //D[4-1]
 *     1+2+1    //D[4-1]
 *     3+1      //D[4-1]
 *     1+1+2    //D[4-2]
 *     2+2      //D[4-2]
 *     1+3      //D[4-3]
 */
public class P9095 {
    private static Scanner scanner = new Scanner(System.in);
    private static int T, N, MAX = 11;
    private static Long[] D = new Long[MAX+1];

    public static void main(String[] args) {
        T = scanner.nextInt();
        D[0] = 1L;
        dp(MAX);
        for (int t=0; t<T; t++) {
            N = scanner.nextInt();
            System.out.println(D[N]);
        }
    }

    private static long dp(int n) {
        if (n < 0) return 0;
        if (D[n] != null) return D[n];
        D[n] = dp(n-1) + dp(n-2) + dp(n-3);
        return D[n];
    }
}
