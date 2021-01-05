package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.BFS)
public class P7576 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[][] B;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        B = new int[N][M];
        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<M; j++) {
                B[i][j] = Integer.parseInt(token.nextToken());
                if (B[i][j] != -1)
                    K++;
                if (B[i][j] == 1)
                    q.add(i * M + j);
            }
        }
        BFS bfs = new BFS(B, q);
        if (bfs.distTo.size() == K) { 
            System.out.println(bfs.max);
        } else {
            System.out.println(-1);
        }
    }
    
    private static class BFS {
        private Map<Integer, Integer> distTo = new HashMap<>();
        private static int[][] D = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        private int max;

        public BFS(int[][] B, Queue<Integer> q) {
            for (Integer p: q)
                distTo.put(p, 0);
            while (!q.isEmpty()) {
                int v = q.poll(),
                        x = v / M,
                        y = v % M;
                for (int[] d: D) {
                    int dx = x + d[0],
                            dy = y + d[1],
                            w = dx * M + dy;
                    if ((dx >= 0 && dx < N && dy >= 0 && dy < M)
                            && B[dx][dy] == 0
                            && !distTo.containsKey(w)) {
                        distTo.put(w, distTo.get(v) + 1);
                        max = Math.max(distTo.get(w), max);
                        q.add(w);
                    }
                }
            }
        }
    }
}
