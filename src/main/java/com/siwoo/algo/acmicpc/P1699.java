package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 예를 들어 11=32+12+12(3개 항)이다. 
 * 이런 표현방법은 여러 가지가 될 수 있는데, 11의 경우 11=22+22+12+12+12(5개 항)도 가능하다.
 * 이 경우, 수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다. 
 * 또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 1
 * 1을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.
 *
 * 주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.
 * 
 * D[n] 을 제곱수들의 합으로 표현할 때, 항의 최소갯수라면
 *  경우의 수.
 *      제곱이 n 보다 작은 모든 수.
 *      
 *  D[n] = D[n-(i^2)] + 1,  1<=i^2<=n
 *       앞의 어떠한 제곱수로 더할때의 최소항 + 현재 제곱수 i^2 을 사용하므로 하나를 증가.
 *  
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1699 {
    private static Scanner scanner = new Scanner(System.in);
    private static Integer[] D; 
    private static int N, MAX = 100000;
    
    public static void main(String[] args) {
        D = new Integer[MAX+1];
        D[0] = 0;
        for (int i=1; i<=MAX; i++) {
            D[i] = Integer.MAX_VALUE;
            for (int j=1; j*j<=i; j++)
                D[i] = Math.min(D[i], D[i-(j*j)] + 1);
        }
        N = scanner.nextInt();
        System.out.println(D[N]);
    }

}
