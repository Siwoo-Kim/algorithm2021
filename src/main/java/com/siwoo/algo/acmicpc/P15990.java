package com.siwoo.algo.acmicpc;

import java.util.Scanner;

/**
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 3가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다. 단, 같은 수를 두 번 이상 연속해서 사용하면 안 된다.
 *
 * 1+2+1
 * 1+3
 * 3+1
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * "연속된" 이란 조건 문제에서는 마지막에 사용한 숫자를 저장하여 푼다.
 * 
 * D[N]{one, two, three} => D[n] 을 1, 2, 3 의 합으로 나타내는 경우의 수면서 마지막에 사용한 수라고 한다면
 *      D[N].one = D[N-1].two + D[N-1].three
 *      D[N].two = D[N-2].one + D[N-2].three
 *      D[N].three = D[N-3].one + D[N-1].two
 *  
 */
public class P15990 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, MAX = 100000, MOD = 1000000009;
    private static P[] DP = new P[MAX+1];
    
    private static class P {
        private long one, two, three;
        
        public P(long one, long two, long three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }

    public static void main(String[] args) {
        DP[0] = new P(1L, 1L, 1L);  //중복 가능.
        for (int i=1; i<=MAX; i++) {
            DP[i] = new P(0, 0, 0);
            DP[i].one = DP[i-1].two + DP[i-1].three;
            DP[i].one %= MOD;
            if (i == 1) DP[i].one = 1;
            if (i - 2 >= 0) {
                DP[i].two = DP[i-2].one + DP[i-2].three;
                DP[i].two %= MOD;
                if (i == 2)
                    DP[i].two = 1;
            }
            if (i - 3 >= 0) {
                DP[i].three = DP[i-3].one + DP[i-3].two;
                DP[i].three %= MOD;
                if (i == 3)
                    DP[i].three = 1;
            }
        }
        N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int a = scanner.nextInt();
            long answer = DP[a].one;
            answer %= MOD;
            answer += DP[a].two;
            answer %= MOD;
            answer += DP[a].three;
            answer %= MOD;
            System.out.println(answer);
        }
    }
}
