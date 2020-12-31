package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Arrays;
import java.util.Scanner;

@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P16184 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static int[] P;
    private static Long[] D;

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        P = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Long[N+1];
        D[0] = 0L;
        long max = dp(N);
        System.out.println(max);
    }

    private static long dp(int n) {
        if (D[n] != null) return D[n];
        long max = Long.MAX_VALUE;
        for (int i=1; i<=n; i++)
            max = Long.min(max, dp(n-i) + P[i-1]);
        return D[n] = max;
    }
}
