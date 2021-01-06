package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;
import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 올바른 bfs 순서.
 *  고민할 점: 같은 거리상의 정점 v, u 가 있을 때 
 *      v 가 먼저 방문되었다면 v 의 인접 정점 w 들이 순서에서 먼저 들어와야 한다.
 *  
 *  주어진 order 에 대해서
 *  
 *  1. 큐엔 첫 정점 s 을 넣는다.
 *  2. 현재 큐의 첫 정점 v 에 따라 아래로 순서를 검증한다.
 *      1. v 의 모든 인접 정점 w 들을 저장.
 *      2. 현재 v 의 검증해야할 순서 k 라 한다면 k+cnt 만큼
 *      순회하며 order[k+i] 가 1 에서 기록됬는지 확인한다.
 *     
 */
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
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        Set<Integer> visit = new HashSet<>();
        visit.add(1);
        int k = 1;
        for (int i=0; i<N; i++) {
            if (q.isEmpty()) {
                System.out.println(0);
                return;
            }
            Set<Integer> adj = new HashSet<>();
            int v = q.poll();
            for (Edge e: G.get(v))
                if (!visit.contains(e.w))
                    adj.add(e.w);
            for (int j=0; j<visit.size(); j++) {
                int w = bfsOrder[k+j];
                if (k+j >= N || 
                    !adj.contains(w)) {
                    System.out.println("0");
                    return;
                }
                visit.add(w);
                q.add(w);
            }
            k += adj.size();
        }
        System.out.println(1);
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
