package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.CYCLE)
public class P16929 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] B;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        B = new char[N][M];
        Set<Character> set = new HashSet<>();
        for (int i=0; i<N; i++)
            B[i] = reader.readLine().toCharArray();
        for (char[] row: B)
            for (char c: row)
                set.add(c);
        for (char c: set) {
            Cycle cycle = new Cycle(B, c);
            if (cycle.hasCycle()) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
    
    private static class Cycle {
        private Stack<Point> cycles;
        private Set<Point> visit = new HashSet<>();
        private final char c;
        
        public Cycle(char[][] B, char c) {
            this.c = c;
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++) {
                    Point p = new Point(i, j);
                    if (!visit.contains(p) 
                            && B[p.x][p.y] == c)
                        if (dfs(B, p, null, new Stack<>())) 
                            return;
                }
        }

        private boolean dfs(char[][] B, Point v, Point u, Stack<Point> stack) {
            stack.push(v);
            visit.add(v);
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                if (B[w.x][w.y] != c) continue;
                if (!visit.contains(w)) {
                    if (dfs(B, w, v, stack)) 
                        return true;
                } else if (!w.equals(u)) {
                    cycles = new Stack<>();
                    while (!stack.peek().equals(w))
                        cycles.push(stack.pop());
                    cycles.push(w);
                    return true;
                }
            }
            stack.pop();
            return false;
        }

        public boolean hasCycle() {
            return cycles != null;
        }
    }
    
    private static class Point {
        static final Point[] D = {
                new Point(1, 0), new Point(-1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
