package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 *
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * D[N][c] 을 n 번째 집에 c 색깔로 칠했을 때의 최솟 비용이라면
 *  색깔 집합 cset 에 대해서
 * 
 *  D[N][c] = Math.min(D[N-1][cset.other(c)])
 *  
 *  => D[N][c] 에 무슨 색을 칠하든, 이전 최소 비용은 변경되지 않는다.
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1149 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] COSTS;
    private static Integer[][] D;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        COSTS = new int[N][3];
        D = new Integer[N][3];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int c=0; c<3; c++)
                COSTS[i][c] = Integer.parseInt(token.nextToken());
        }
        dp(N-1, 0);
        dp(N-1, 1);
        dp(N-1, 2);
        System.out.println(Math.min(Math.min(D[N-1][0], D[N-1][1]), D[N-1][2]));
    }

    private static int dp(int i, int c) {
        if (i < 0) return 0;
        if (D[i][c] != null) return D[i][c];
        int min = Integer.MAX_VALUE;
        int bitset = other(c);
        for (int bit=0; bit<3; bit++)
            if ((bitset & (1 << bit)) != 0)
                min = Math.min(min, dp(i-1, bit) + COSTS[i][c]);
        return D[i][c] = min;
    }

    public static int other(int x) {
        int bitset = (1 << 3)-1;
        return bitset ^ (1<<x);
    }
}
