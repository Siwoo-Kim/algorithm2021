package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D[i][j] 을 (i,j) 경로에 올 수 있는 경우의 수라 한다면,
 *  D[i][j] 로 올 수 있는 경로는 B[i][j] 보단 커야 하므로, 
 *      D[i][j] 로 중복 방문할 수 없다.
 *      
 * D[i][j] += D[x][y]. (i-1,j), (i+1,j), (i,j-1), (i,j+1)
 *  단, D[i][j] < D[x][y]
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P1520 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] B;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        B = new int[N][M];
        D = new Integer[N][M];
        for (int i=0; i<N; i++)
            B[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int x = dp(new Point(0, 0));
        System.out.println(x);
    }

    private static int dp(Point v) {
        if (v.x == N-1 && v.y == M-1) return 1;
        if (D[v.x][v.y] != null) return D[v.x][v.y];
        D[v.x][v.y] = 0;
        for (int[] d: Point.D) {
            Point w = new Point(v.x + d[0], v.y + d[1]);
            if (w.valid() && B[v.x][v.y] > B[w.x][w.y]) 
                D[v.x][v.y] += dp(w);
        }
        return D[v.x][v.y];
    }
    
    private static class Point {
        private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
