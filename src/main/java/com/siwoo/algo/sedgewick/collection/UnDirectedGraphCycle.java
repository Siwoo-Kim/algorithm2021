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
    private Stack<E> cycle;

    public UnDirectedGraphCycle(Graph<E> G) {
        Set<E> visit = new Set<>();
        Stack<E> onStack = new LinkedList<>();
        for (E v: G.vertexes())
            if (!visit.contains(v))
                if (dfs(v, v, visit, onStack, G))
                    break;
    }

    private boolean dfs(E v, E u, Set<E> visit, Stack<E> onStack, Graph<E> G) {
        visit.add(v);
        onStack.push(v);
        for (E w: G.adj(v)) {
            if (!visit.contains(w)) {
                if (dfs(w, v, visit, onStack, G)) return true;
            } else if (!u.equals(w)) {
                cycle = new LinkedList<>();
                cycle.push(w);
                while (!onStack.peek().equals(w))
                    cycle.push(onStack.pop());
                cycle.push(w);
                return true;
            }
        }
        onStack.pop();
        return false;
    }

    @Override
    public boolean hasCycle() {
        return cycle != null;
    }

    @Override
    public Iterable<E> cycle() {
        return cycle;
    }

}
