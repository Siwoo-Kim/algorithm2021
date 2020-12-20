package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 *
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 *
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 * 
 * D[n][i] 을 n 자리에 i 의 숫자를 넣었을 때의 경우의 수라면
 *  i 에 관하여 0<=i<=9
 *      D[n][i] += D[n-1][j], 0<=j<=i
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11057 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N = 1000, MOD = 10007;
    private static Integer[][] D = new Integer[N+1][10];

    public static void main(String[] args) {
        for (int i=0; i<=9; i++)
            D[1][i] = 1;
        dp(N);
        int m = scanner.nextInt();
        int answer = 0;
        for (int i=0; i<=9; i++) {
            answer += D[m][i];
            answer %= MOD;
        }
        System.out.println(answer);
    }

    private static void dp(int n) {
        if (n == 1) return;
        dp(n-1);
        for (int i=0; i<=9; i++) {
            D[n][i] = 0;
            for (int j = 0; j <= i; j++)
                D[n][i] += D[n-1][j];
            D[n][i] %= MOD;
        }
    }
}
