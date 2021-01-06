package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.CYCLE)
public class P16947 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V;

    public static void main(String[] args) throws IOException {
        V =  Integer.parseInt(reader.readLine());
        for (int i=1; i<=V; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                  w = Integer.parseInt(token.nextToken());  
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            Edge edge = new Edge(v, w);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        Cycle cycle = new Cycle(G);
        assert cycle.hasCycle();
        BFS bfs = new BFS(G, cycle.cycle);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=V; i++)
            sb.append(bfs.distTo(i)).append(" ");
        System.out.println(sb);
    }
    
    private static class BFS {
        private Map<Integer, Integer> distTo = new HashMap<>();
        
        public BFS(Map<Integer, List<Edge>> G, Set<Integer> sources) {
            Queue<Integer> q = new LinkedList<>();
            for (int e: sources) {
                q.add(e);
                distTo.putIfAbsent(e, 0);
            }
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e: G.get(v)) {
                    if (!distTo.containsKey(e.w)) {
                        distTo.put(e.w, distTo.get(e.v) + 1);
                        q.add(e.w);
                    }
                }
            }
        }

        public int distTo(int v) {
            return distTo.get(v);
        }
    }
    
    private static class Cycle {
        private Set<Integer> cycle;
        private Set<Integer> visit = new HashSet<>();
        
        public Cycle(Map<Integer, List<Edge>> G) {
            for (int v: G.keySet())
                if (!visit.contains(v) 
                        && check(v, v, new Stack<>(), G))
                    return;
        }

        private boolean check(int v, int u,
                              Stack<Integer> stack,
                              Map<Integer, List<Edge>> G) {
            visit.add(v);
            stack.push(v);
            for (Edge e: G.get(v)) {
                assert v != e.w;
                if (!visit.contains(e.w)) {
                    if (check(e.w, v, stack, G)) 
                        return true;
                } else if (e.w != u) {
                    cycle = new HashSet<>();
                    while (stack.peek() != e.w)
                        cycle.add(stack.pop());
                    cycle.add(stack.pop());
                    return true;
                }
            }
            stack.pop();
            return false;
        }

        public boolean hasCycle() {
            return cycle != null;
        }
    }
    
    private static class Edge {
        final int v, w;

        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
