package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.UNION_FIND)
public class P4963 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] B;
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            if (N == 0 && M == 0) return;
            B = new int[N][M];
            for (int i=0; i<N; i++)
                B[i] = Arrays.stream(reader.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            UF<Point> uf = new UF<>();
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++) {
                    Point v = new Point(i, j);
                    if (B[v.x][v.y] == 1) {
                        uf.put(v);
                        for (Point d: Point.D) {
                            Point w = new Point(v.x + d.x, v.y + d.y);
                            if (w.valid() && B[w.x][w.y] == 1) {
                                uf.put(w);
                                uf.union(v, w);
                            }
                        }
                    }
                }
            System.out.println(uf.N);
        }
    }
    
    private static class UF<E> {
        private Map<E, E> components = new HashMap<>();
        private Map<E, Integer> sizes = new HashMap<>();
        private int N;

        public void put(E v) {
            if (!components.containsKey(v)) {
                components.put(v, v);
                sizes.put(v, 1);
                N++;
            }
        }

        public void union(E v, E w) {
            v = get(v);
            w = get(w);
            if (v.equals(w)) return;
            if (sizes.get(v) < sizes.get(w)) {
                E t = v;
                v = w;
                w = t;
            }
            sizes.put(v, sizes.get(v) + sizes.get(w));
            components.put(w, v);
            N--;
        }

        private E get(E v) {
            if (v.equals(components.get(v)))
                return v;
            E root = get(components.get(v));
            components.put(v, root);
            return root;
        }
    }
    
    private static class Point {
        static final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, 1), new Point(1, -1),
                new Point(-1, -1), new Point(1, 1)
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
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}
