package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.BFS)
public class P2178 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] B;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        B = new char[N][M];
        for (int i=0; i<N; i++)
            B[i] = reader.readLine().toCharArray();
        BFS bfs = new BFS(B, 0);
        System.out.println(bfs.distTo.get(N*M-1));
    }
    
    private static class BFS {
        private static int[][] D = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        private Map<Integer, Integer> distTo = new HashMap<>();
        private Set<Integer> visit = new HashSet<>();

        public BFS(char[][] B, int source) {
            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            distTo.put(source, 1);
            visit.add(source);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int[] d: D) {
                    int dx = toXY(v)[0] + d[0],
                            dy = toXY(v)[1] + d[1];
                    if (!visit.contains(id(dx, dy)) 
                            && valid(dx, dy) 
                            && B[dx][dy] == '1') {
                        visit.add(id(dx, dy));
                        distTo.put(id(dx, dy), distTo.get(v) + 1);
                        q.add(id(dx, dy));
                    }
                }
            }
        }

        private boolean valid(int x, int y) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public int id(int x, int y) {
            return x * M + y;
        }
        
        public int[] toXY(int id) {
            return new int[]{id / M, id % M};
        }
    }
}
