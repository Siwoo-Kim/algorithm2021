package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.siwoo.algo.util.Algorithm.BIPARTITE;

@Using(algorithm = BIPARTITE)
public class P1707 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G;
    private static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int V = Integer.parseInt(token.nextToken()),
                    E = Integer.parseInt(token.nextToken());
            G = new HashMap<>();
            for (int i=0; i<E; i++) {
                token = new StringTokenizer(reader.readLine());
                int v = Integer.parseInt(token.nextToken());
                int w = Integer.parseInt(token.nextToken());
                Edge e = new Edge(v, w);
                G.putIfAbsent(v, new LinkedList<>());
                G.putIfAbsent(w, new LinkedList<>());
                G.get(v).add(e);
                G.get(w).add(e.reverse());
            }
            System.out.println(new Bipartite(G).bipartite? "YES": "NO");
        }
    }
    
    private static class Bipartite {
        private boolean bipartite = true;
        private Map<Integer, COLOR> map = new HashMap<>();
        
        private enum COLOR { 
            RED, BLUE; 
            COLOR reverse() {
                return this == RED? BLUE: RED;
            }
        }
        
        public Bipartite(Map<Integer, List<Edge>> G) {
            for (int v: G.keySet())
                if (!map.containsKey(v)) {
                    bipartite = dfs(v, COLOR.RED, G);
                    if (!bipartite) break;
                }
        }

        private boolean dfs(int v, COLOR c, Map<Integer, List<Edge>> G) {
            map.put(v, c);
            for (Edge e: G.get(v)) {
                if (!map.containsKey(e.w)) {
                    boolean ok = dfs(e.w, c.reverse(), G);
                    if (!ok) return false;
                } else if (c == map.get(e.w))
                    return false;
            }
            return true;
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
