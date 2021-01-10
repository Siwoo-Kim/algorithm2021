package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1261 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] B;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        B = new int[N][M];
        for (int i=0; i<N; i++)
            B[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        BFS bfs = new BFS(B, new Point(0, 0), new Point(N-1, M-1));
        System.out.println(bfs.answer);
    }
    
    private static class BFS {
        final int answer;
        
        public BFS(int[][] B, Point s, Point e) {
            Deque<Point> q = new LinkedList<>();
            q.addFirst(s);
            Map<Point, Integer> distTo = new HashMap<>();
            distTo.put(s, 0);
            while (!q.isEmpty()) {
                Point p1 = q.poll();
                if (p1.equals(e)) {
                    answer = distTo.get(e);
                    return;
                }
                int dist = distTo.get(p1);
                for (Point d: Point.D) {
                    Point p2 = new Point(p1.x + d.x, p1.y + d.y);
                    if (!p2.valid()) continue;
                    if (distTo.containsKey(p2) 
                            && B[p2.x][p2.y] == 0
                            && distTo.get(p2) != dist) {
                        distTo.put(p2, dist);
                        q.addFirst(p2);
                    } else if (!distTo.containsKey(p2)) {
                        distTo.put(p2, dist + B[p2.x][p2.y]);
                        if (B[p2.x][p2.y] == 0)
                            q.addFirst(p2);
                        else
                            q.addLast(p2);
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }
    
    static class Point {
        private static Point[] D = {
                new Point(1, 0), new Point(-1, 0), 
                new Point(0, -1), new Point(0, 1)
        };
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
            return this.x >= 0 && this.x < N && this.y >= 0 && this.y < M;
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
