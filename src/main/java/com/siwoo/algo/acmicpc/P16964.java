package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DFS 와 BFS 의 방문 순서는
 *  G(v) 의 인접 리스트가 어떤 순서로 저장되어 있냐에 따라 정해진다.
 *  
 */
@Using(algorithm = Algorithm.DFS)
public class P16964 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static List<Integer> orders = new ArrayList<>();
    private static int N, k;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w);
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        int[] dfsOrder = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Map<Integer, Integer> index = new HashMap<>();
        if (dfsOrder[0] != 1) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < N; i++)
            index.put(dfsOrder[i], i);
        for (int v : G.keySet())
            G.get(v).sort(Comparator.comparingInt(e -> index.get(e.w)));
        Set<Integer> visit = new HashSet<>();
        for (int v : dfsOrder) {
            if (!visit.contains(v))
                dfs(v, visit);
        }
        boolean ok = true;
        for (int i=0; i<N; i++)
            if (dfsOrder[i] != orders.get(i)) {
                ok = false;
                break;
            }
        System.out.println(ok? 1: 0);
    }

    private static void dfs(int v, Set<Integer> visit) {
        visit.add(v);
        orders.add(v);
        for (Edge e: G.get(v)) {
            if (!visit.contains(e.w)) {
                dfs(e.w, visit);
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
