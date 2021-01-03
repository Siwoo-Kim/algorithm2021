package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

//fail
public class P2667 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static char[][] BOARD;
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new char[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();
        UF uf = new UF();
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                if (BOARD[i][j] == '1') {
                    uf.add(i*N+j);
                    for (int[] d : D) {
                        int dx = i + d[0],
                                dy = j + d[1];
                        if (valid(dx, dy) 
                                && BOARD[dx][dy] == '1') {
                            uf.add(dx*N+dy);
                            uf.union(i * N + j, dx * N + dy);
                        }
                    }
                }
            }
        System.out.println(uf.N);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int root: new HashSet<>(uf.components.values()))
            pq.add(uf.sizes.get(root));
        while (!pq.isEmpty())
            System.out.println(pq.poll());
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class UF {
        Map<Integer, Integer> components = new HashMap<>();
        Map<Integer, Integer> sizes = new HashMap<>();
        int N;

        public UF() {}

        public void union(int v, int w) {
            v = get(v);
            w = get(w);
            if (v == w) return;
            if (sizes.get(v) < sizes.get(w)) {
                int t = v;
                v = w;
                w = t;
            }
            sizes.put(v, sizes.get(v) + sizes.get(w));
            components.put(w, v);
            N--;
        }

        private int get(int v) {
            if (components.get(v) == v)
                return v;
            int root = get(components.get(v));
            components.put(v, root);
            return root;
        }

        public void add(int v) {
            if (!components.containsKey(v)) {
                components.put(v, v);
                sizes.put(v, 1);
                N++;
            }
        }
    }
}
