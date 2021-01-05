package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.BFS)
public class P7562 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer token = new StringTokenizer(reader.readLine());
            Point from = new Point(
                    Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
            token = new StringTokenizer(reader.readLine());
            Point to = new Point(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
            BFS bfs = new BFS(from);
            System.out.println(bfs.distTo.get(to));
        }
    }
    
    private static class BFS {
        Map<Point, Integer> distTo = new HashMap<>();

        public BFS(Point source) {
            distTo.put(source, 0);
            Queue<Point> q = new LinkedList<>();
            q.add(source);
            while (!q.isEmpty()) {
                Point v = q.poll();
                for (Point d: Point.D) {
                    Point w = new Point(v.x + d.x, v.y + d.y);
                    if (w.valid() 
                            && !distTo.containsKey(w)) {
                        distTo.put(w, distTo.get(v) + 1);
                        q.add(w);
                    }
                }
            }
        }
    }
    
    private static class Point {
        final static Point[] D = {
                new Point(-1, -2), new Point(-2, -1),
                new Point(-2, 1), new Point(-1, 2),
                new Point(1, 2), new Point(2, 1),
                new Point(2, -1), new Point(1, -2)
        };
        final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
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
    }
}
