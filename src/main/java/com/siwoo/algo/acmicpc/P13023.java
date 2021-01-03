package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.DFS)
public class P13023 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        for (int i=0; i<V; i++)
            G.put(i, new LinkedList<>());
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            Edge edge = new Edge(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()), 1);
            G.get(edge.v).add(edge);
            G.get(edge.w).add(edge.reverse());
        }
        boolean ok = false;
        for (int v: G.keySet()) {
            Set<Integer> visit = new HashSet<>();
            if (dfs(v, 0, visit)) {
                ok = true;
                break;
            }
        }
        System.out.println(ok? 1: 0);
    }

    private static boolean dfs(int v, int depth, Set<Integer> visit) {
        if (depth >= 4) return true;
        visit.add(v);
        for (Edge e: G.get(v)) {
            if (!visit.contains(e.w)) {
                if (dfs(e.w, depth + 1, visit)) 
                    return true;
            }
        }
        visit.remove(v);
        return false;
    }


    private static class Edge {
        final int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        
        public Edge reverse() {
            return new Edge(w, v, weight);
        }

        @Override
        public String toString() {
            return String.format("%d - %d", v, w);
        }
    }
}
