package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.
 * 오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
 * 백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
 * 각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.
 * 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시요.
 * 
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P14501 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] T, P;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        T = new int[N];
        P = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            T[i] = Integer.parseInt(token.nextToken());
            P[i] = Integer.parseInt(token.nextToken());
        }
        long max = schedule(0);
        System.out.println(max);
    }

    private static long schedule(int i) {
        if (i == N) return 0;
        if (i > N) return Integer.MIN_VALUE;
        long a = schedule(i+T[i]) + P[i];
        a = Math.max(schedule(i+1), a);
        return a;
    }
}
