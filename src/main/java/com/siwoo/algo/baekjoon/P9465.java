package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다.
 * 
 * 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
 *
 * 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다.
 * 
 * D[n]{0, 1, 2} 을 n 번째 열에서 스티커를 안붙히거나 (0), 각 1,2 행에 해당하는 위치에 붙히는 경우 (1,2) 라면
 * 
 * D[n][0] = max (D[N-1][0], D[N-1][1], D[N-1][2])
 * D[n][1] = max (D[N-1][0], D[N-1][2]) + A[n][1]
 * D[n][2] = max (D[N-1][0], D[N-1][1]) + A[n][2]
 *
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P9465 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, T;
    private static long[][] A, D;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int i=0; i<T; i++) {
            N = Integer.parseInt(reader.readLine());
            A = new long[3][N];
            D = new long[N][3];
            for (int j=1; j<=2; j++) {
                StringTokenizer token = new StringTokenizer(reader.readLine());
                for (int k = 0; k < N; k++)
                    A[j][k] = Integer.parseInt(token.nextToken());
            }
            long answer = dp(N-1);
            System.out.println(answer);
        }
    }

    private static long dp(int n) {
        if (n < 0) return 0;
        dp(n-1);
        D[n][0] = max3(get(n-1, 0), get(n-1, 1), get(n-1, 2));
        D[n][1] = max2(get(n-1, 0), get(n-1, 2)) + A[1][n];
        D[n][2] = max2(get(n-1, 0), get(n-1, 1)) + A[2][n];
        return max3(D[n][0], D[n][1], D[n][2]);
    }

    private static long max3(long a, long b, long c) {
        return max2(Math.max(a, b), c);
    }

    private static long max2(long a, long b) {
        return Math.max(a, b);
    }

    private static long get(int n, int state) {
        if (n < 0) return 0L;
        return D[n][state];
    }
}
