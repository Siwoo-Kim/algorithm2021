package com.siwoo.algo.acmicpc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D[i][j] 을 i, j 을 시작점으로 하여 가장 큰 정사각형의 크기라 하자.
 *
 *  D[i][j] = min(D[i+1][j], D[i][j+1], D[i+1][j+1]) + 1
 *  
 *  단 B[i][j] == 0, D[i][j] = 1
 */
public class P1915 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] board;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new int[N][M];
        D = new Integer[N][M];
        for (int i=0; i<N; i++)
            board[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int answer = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                int area = dp(new Point(i, j));
                answer = Math.max(area * area, answer);
            }
        System.out.println(answer);
    }

    private static int dp(Point p) {
        if (board[p.x][p.y] != 1) return 0;
        if (D[p.x][p.y] != null) return D[p.x][p.y];
        int min = Integer.MAX_VALUE;
        for (int[] d: Point.D) {
            Point w = new Point(p.x + d[0], p.y + d[1]);
            if (!w.valid()) return 1;
            min = Math.min(dp(w)+1, min);
        }
        return D[p.x][p.y] = min;
    }

    private static class Point {
        private static int[][] D = {{1, 0}, {0, 1}, {1, 1}};
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
