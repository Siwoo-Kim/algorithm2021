package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//fail
@Using(algorithm = Algorithm.BFS)
public class P16940 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w);
            G.putIfAbsent(v , new ArrayList<>());
            G.putIfAbsent(w , new ArrayList<>());
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        int[] bfsOrder = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        BFS bfs = new BFS(G, bfsOrder[0]);
        int dist = 0;
        Queue<PriorityQueue<Integer>> orderQ = new LinkedList<>();
        for (int i=0; i<N; i++) {
            int v = bfsOrder[i];
            PriorityQueue<Integer> pq = new PriorityQueue<>(); 
            if (bfs.distTo.get(v) != dist) {
                System.out.println(0);
                return;
            }
            while (bfs.distTo.get(v) == dist) {
                pq.add(v);
                if (i == N-1 || bfs.distTo.get(bfsOrder[i+1]) != dist)
                    break;
                v = bfsOrder[++i];
            }
            orderQ.add(pq);
            dist++;
        }
        Level[] levels = new Level[bfs.max+1];
        
        for (int v: G.keySet()) {
            if (levels[bfs.distTo.get(v)] == null)
                levels[bfs.distTo.get(v)] = new Level();
            levels[bfs.distTo.get(v)].agg.add(v);
        }
        int d = 0;
        boolean ok = true;
        while (!orderQ.isEmpty()) {
            PriorityQueue<Integer> q1 = orderQ.poll(),
                    q2 = levels[d++].agg;
            while (!q1.isEmpty()) {
                int v = q1.poll();
                if (q2.isEmpty()) {
                    ok = false;
                    break;
                }
                if (v != q2.poll()) {
                    ok = false;
                    break;
                }
            }
        }
        System.out.println(ok ? 1: 0);
    }
    
    private static class Level {
        private PriorityQueue<Integer> agg = new PriorityQueue<>();
    }
    
    private static class BFS {
        Map<Integer, Integer> distTo = new HashMap<>();
        int max = 0;

        public BFS(Map<Integer, List<Edge>> G, int source) {
            distTo.put(source, 0);
            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e: G.get(v))
                    if (!distTo.containsKey(e.w)) {
                        distTo.put(e.w, distTo.get(v) + 1);
                        max = Math.max(distTo.get(e.w), max);
                        q.add(e.w);
                    }
            }
        }
    }
    
    private static class Edge {
        final int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
