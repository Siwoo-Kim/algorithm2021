package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

/**
 * [algo] [cycle in undirected graph]
 *
 * problem
 *  * 무방향 그래프에서 순환 탐지. (self-loop & parallel-edge 제외)
 *
 * components
 *  1. dfs
 *  2. 현재 방문 정점 v 와 바로 이전 정점 u
 *
 *  algorithm.
 *      1. 정점 v 와 이전 정점 u 에 대한 dfs(v, u)
 *      2. 만약 v 의 인접 정점 w 정점을 방문했고 이전 정점이 아니라면 (w != u) 순환 발생.
 *
 *  limitation
 *      1. 방향 그래프에선 다른 방식 (스택) 을 이용해야 한다.
 *
 */
public class UnDirectedGraphCycle<E> implements Cycle<E> { 
    private boolean hasCycle;

    public UnDirectedGraphCycle(Graph<E> G) {
        Set<E> visit = new Set<>();
        for (E v: G.vertexes())
            if (!visit.contains(v))
                dfs(v, v, visit, G);
    }

    private void dfs(E v, E u, Set<E> visit, Graph<E> G) {
        visit.add(v);
        for (E w: G.adj(v))
            if (!visit.contains(w))
                dfs(w, v, visit, G);
            else if (u.equals(w))
                hasCycle = true;
    }

    @Override
    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyG.txt";
        In in = new In(path);
        int V = in.readInt(),
                E = in.readInt();
        Graph<Integer> G = new UnDirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
        }
        Cycle<Integer> cycle = new UnDirectedGraphCycle<>(G);
        System.out.println(cycle.hasCycle());
    }
}
