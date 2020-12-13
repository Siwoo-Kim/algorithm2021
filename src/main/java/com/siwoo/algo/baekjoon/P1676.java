package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * !n 의 0 의 갯수.
 * 
 * n 의 약수 중 (2,5) 쌍의 갯수
 * 
 * 5! = 120         2, 5
 * 10! = 3628800    2, 5, 10 - 2 * 5
 * 15! = 1,307,674,368,000  2, 5, 10, 15 - 3*5
 * 20! = 2,432,902,008,176,640,000  2, 5, 10, 15, 20
 * 25! =  15,511,210,043,330,985,984,000,000    2, 5, 15, 15, 20, 25 - 5*5
 * 
 * 항상 2 의 갯수는 5보다 많으므로 5만 세어주면 된다.
 */
@Using(algorithm = Algorithm.MATH)
public class P1676 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        int zeros = zerosInFac(N);
        System.out.println(zeros);
    }

    private static int zerosInFac(int n) {
        if (n == 0) return 0;
        int t = n, cnt = 0;
        while (t % 5 == 0) {
            t /= 5;
            cnt++;
        }
        return zerosInFac(n-1) + cnt;
    }
}
