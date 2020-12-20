package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 어떤 동물원에 가로로 두칸 세로로 N칸인 아래와 같은 우리가 있다.
 *
 *
 * 이 동물원에는 사자들이 살고 있는데 사자들을 우리에 가둘 때, 가로로도 세로로도 붙어 있게 배치할 수는 없다. 이 동물원 조련사는 사자들의 배치 문제 때문에 골머리를 앓고 있다.
 *
 * 동물원 조련사의 머리가 아프지 않도록 우리가 2*N 배열에 사자를 배치하는 경우의 수가 몇 가지인지를 알아내는 프로그램을 작성해 주도록 하자. 
 * 사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다고 가정
 * 
 * D[n]{0, 1, 2} = n 칸에 사자를 배치하지 않거나 (0), 첫번째 혹은 두번째 칸에 배치할 때의 경우의 수.
 *  D[n][0] = D[n-1][0] + D[n-1][1] + D[n-1][2]
 *  D[n][1] = D[n-1][0] + D[n-1][2]
 *  D[n][2] = D[n-1][0] + D[n-1][1]
 *  
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1309 {
    private static Scanner scanner = new Scanner(System.in);
    private static Integer[][] D;
    private static int N, MAX = 100000, MOD = 9901;

    public static void main(String[] args) {
        D = new Integer[MAX+1][3];
        D[0][0] = 1;
        D[0][1] = D[0][2] = 0;
        for (int i=1; i<=MAX; i++) {
            D[i][0] = D[i-1][0] + D[i-1][1] + D[i-1][2];
            D[i][1] = D[i-1][0] + D[i-1][2];
            D[i][2] = D[i-1][0] + D[i-1][1];
            D[i][0] %= MOD;
            D[i][1] %= MOD;
            D[i][2] %= MOD;
        }
        N = scanner.nextInt();
        System.out.println((D[N][0] + D[N][1] + D[N][2]) % MOD);
    }
}
