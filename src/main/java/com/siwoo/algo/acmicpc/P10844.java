package com.siwoo.algo.acmicpc;

import java.util.Scanner;

/**
 * 45656이란 수를 보자.
 *
 * 이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
 *
 * 세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
 *
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
 * 
 * 경우의 수.
 *  n 자리 숫자에서 0~9 까지의 숫자를 선택.
 * 
 * D[n][i] 을 길이가 n 이고 숫자 i 로 시작하는 계단수라면 
 *  D[n][i] = D[n][i-1] + D[n][i+1], 단 D[n][0] = D[n-1][1], D[n][9] = D[n-1][8]
 *  
 */
public class P10844 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 100, MOD = 1000000000;
    private static Long[][] D = new Long[MAX+1][10];

    public static void main(String[] args) {
        for (int i=0; i<=9; i++)
            D[1][i] = 1L;
        dp(2);
        int N = scanner.nextInt();
        long answer = 0;
        for (int i=1; i<=9; i++)
            answer = ((D[N][i] % MOD) + answer) % MOD;
        System.out.println(answer);
    }

    private static void dp(int n) {
        if (n > MAX) return;
        for (int i=0; i<=9; i++) {
            if (i == 0)
                D[n][i] = D[n-1][i+1];
            else if (i == 9)
                D[n][i] = D[n-1][i-1];
            else
                D[n][i] = D[n-1][i-1] + D[n-1][i+1];
            D[n][i] %= MOD;
        }
        dp(n+1);
    }
}
