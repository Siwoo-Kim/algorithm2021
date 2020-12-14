package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;
import edu.princeton.cs.algs4.In;

import java.util.Scanner;

/**
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 *
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * 
 * 어떤 정수 x 에 대한 경우의 수.
 *      x / 3
 *      x / 2
 *      x - 1
 * 
 *
 * d[x] 을 x 을 최소한의 연산을 사용해 1 로 만든 횟수라 한다면
 *  d[x] = Math.min(d[x/3], d[x/2], d[x-1])
 *  
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1463 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 1000000;
    private static Integer[] D = new Integer[MAX+1];

    public static void main(String[] args) {
        int n = scanner.nextInt();
        D[1] = 0;
        for (int i=2; i<=n; i++) {
            int min = D[i-1] + 1;
            if (i % 3 == 0)
                min = Math.min(min, D[i/3]+1);
            if (i % 2 == 0)
                min = Math.min(min, D[i/2]+1);
            D[i] = min;
        }
        System.out.println(D[n]);
    }
}
