package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 1, 2, 3 을 이용해 N 을 만드는 경우의 수.
 * 
 * D[n] = 
 *  N-1 을 1을 이용해 만드는 경우의 수 +  
 *  N-2 을 2을 이용해 만드는 경우의 수 +
 *  N-3 을 3을 이용해 만드는 경우의 수
 *  
 *  = D[n] = D[n-1] + D[n-2] + D[n-3]
 *  
 *  D[n][m] = m 개의 숫자를 이용해 n 을 만드는 경우의 수.
 *  D[n][m] = D[n-1][m-1] + D[n-2][m-1] + D[n-3][m-3]
 *            각 경우의 수당 숫자 하나만을 추가하므로 이전 경우의 수는 [m-1]
 *            이때 m 의 범위는 1 <= m <= n (어떤 수 n 을 만들 수 있는 최소 갯수는 자기 자신.)
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P15992 {
    private static int MAX = 1000, MOD= 1000000009;
    private static long[][] D = new long[MAX+1][MAX+1];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        D[0][0] = 1;
        for (int i=1; i<=MAX; i++) {
            for (int j=1; j<=i; j++) {
                D[i][j] += D[i-1][j-1];
                if (i-2 >= 0)
                    D[i][j] += D[i-2][j-1];
                if (i-3 >= 0)
                    D[i][j] += D[i-3][j-1];
                D[i][j] %= MOD;
            }
        }
        int M = scanner.nextInt();
        for (int i=0; i<M; i++) {
            int n = scanner.nextInt(),
                    m = scanner.nextInt();
            System.out.println(D[n][m]);
        }
    }
}
