package com.siwoo.algo.algospot;

import com.siwoo.algo.util.AppConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BoardCover {
    private int N, M, ANSWER;

    public BoardCover(int N, int M, boolean[][] BOARD) {
        this.N = N;
        this.M = M;
        ANSWER = numberOfCase(BOARD);
    }
    
    public int answer() {
        return ANSWER;
    }

    public int numberOfCase(boolean[][] BOARD) {
        Point p = null;
        boolean found = false;
        for (int i=0; i<N; i++) {
            for (int j = 0; j < M; j++)
                if (BOARD[i][j]) {
                    p = new Point(i, j);
                    found = true;
                    break;
                }
            if (found) break;
        }
        if (p == null) return 1;
        int answer = 0;
        for (Point[] d: Point.D) {
            boolean ok = true;
            Set<Point> visit = new HashSet<>();
            for (Point q: d) {
                Point pq = new Point(p.x + q.x, p.y + q.y);
                if (pq.valid(N, M) && BOARD[pq.x][pq.y]) {
                    BOARD[pq.x][pq.y] = false;
                    visit.add(pq);
                } else 
                    ok = false;
            }
            if (ok)
                answer += numberOfCase(BOARD);
            for (Point pq: visit)
                BOARD[pq.x][pq.y] = true;
        }
        return answer;
    }
    
    private static class Point {
        public static Point[][] D = {
                {new Point(0, 0), new Point(1, 0), new Point(0, 1)},
                {new Point(0, 0), new Point(0, 1), new Point(1, 1)},
                {new Point(0, 0), new Point(1, 0), new Point(1, 1)},
                {new Point(0, 0), new Point(1, 0), new Point(1, -1)},
        };
        
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int index(int M) {
            return x * M + y;
        }
        
        public boolean valid(int N, int M) {
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

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/boardcover.txt"));
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int N = scanner.nextInt(),
                    M = scanner.nextInt();
            boolean[][] BOARD = new boolean[N][M];
            scanner.nextLine();
            for (int i=0; i<N; i++) {
                String s = scanner.nextLine();
                for (int j = 0; j < M; j++)
                    if (s.charAt(j) == '.')
                        BOARD[i][j] = true;
            }
            BoardCover boardCover = new BoardCover(N, M, BOARD);
            System.out.println(boardCover.answer());
        }
    }
}
