package com.siwoo.algo.programmers;

import java.util.*;

//fail 위상 정렬 문제가 아닌듯
// 선수 a 가 b 을 이기면
// 선수 b 가 이긴 모든 c 에 대해서 선수 a 가 이긴다.
public class BoxingRanking {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = solution.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}});
        System.out.println(x);
    }
    
    static class Solution {
        private Map<Integer, List<Edge>> G = new HashMap<>(); 
        private Map<Integer, List<Integer>> depths = new HashMap<>();
        
        public int solution(int n, int[][] results) {
            for (int i=1; i<=n; i++)
                G.put(i, new ArrayList<>());
            for (int i=0; i<results.length; i++) {
                Edge edge = new Edge(results[i][1], results[i][0]);
                G.get(edge.v).add(edge);
            }
            Topological order = new Topological(G);
            Set<Integer> visit = new HashSet<>();
            while (!order.stack.isEmpty()) {
                int v = order.stack.pop();
                if (!visit.contains(v))
                    dfs(v, 0, visit);
            }
            int cnt = 0;
            for (int d: depths.keySet())
                if (this.depths.get(d).size() == 1)
                    cnt++;
            return cnt;
        }

        private void dfs(int v, int depth, Set<Integer> visit) {
            visit.add(v);
            this.depths.putIfAbsent(depth, new ArrayList<>());
            this.depths.get(depth).add(v);
            for (Edge e: G.get(v))
                if (!visit.contains(e.w))
                    dfs(e.w, depth+1, visit);
        }

        class Topological {
            private Stack<Integer> stack = new Stack<>();
            
            public Topological(Map<Integer, List<Edge>> G) {
                Set<Integer> visit = new HashSet<>();
                for (int v: G.keySet())
                    if (!visit.contains(v))
                        dfs(v, visit, G);
            }

            private void dfs(int v, Set<Integer> visit, Map<Integer, List<Edge>> G) {
                visit.add(v);
                for (Edge e: G.get(v))
                    if (!visit.contains(e.w))
                        dfs(e.w, visit, G);
                stack.push(v);
            }
        }
        
        class Edge {
            private int v, w;

            public Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }
    }
}
