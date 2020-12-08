package com.siwoo.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

/**
 * D[i][j] 에서 출발하여 D[i][j] < D[x][y] 을 만족하는 동안의 최장 거리라 한다면
 *  
 *  D[i][j] = Math.max(D[x][y] + 1)
 */
public class P1937 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] board;
    private static Integer[][] DP;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        DP = new Integer[N][N];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::valueOf)
                    .toArray();
        int answer = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                answer = Math.max(answer, dp(new Point(i, j)));
        System.out.println(answer);
    }

    private static int dp(Point v) {
        if (DP[v.x][v.y] != null) 
            return DP[v.x][v.y];
        DP[v.x][v.y] = 1;
        for (int[] d: Point.D) {
            Point w = new Point(v.x + d[0], v.y + d[1]);
            if (w.valid() && board[w.x][w.y] > board[v.x][v.y]) {
                int day = dp(w) + 1;
                if (DP[v.x][v.y] == null)
                    DP[v.x][v.y] = day;
                else
                    DP[v.x][v.y] = Math.max(DP[v.x][v.y], day);
            }
        }
        return DP[v.x][v.y];
    }

    private static class Point {
        public static final int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }
}
