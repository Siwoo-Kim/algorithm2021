package com.siwoo.algo.sedgewick.collection;

/**
 * [algo] [dfs]
 *
 * problem
 *  * 정점 v 에서 연결된 모든 정점을 순회하고 싶다.
 *
 * components
 *  1. 방문 기록한 정점들, visit
 *  2. 재귀 (stack)
 *
 * flow.
 *      1. 정점 v 을 방문했다 표시한다. 
 *      2. v 에서 방문하지 않은 인접한 정점 w 을 방문한다.
 *
 * time-complexity
 *      DFS 은 정점 s 에서 연결된 모든 정점을 방문하는데, 
 *      그 정점들의 degree 만큼의 시간 복잡도를 가진다.
 *
 */
public class DFSGraphSearch<E> implements GraphSearch<E> {
    private final E source;
    private final Set<E> visit = new Set<>();
    
    public DFSGraphSearch(Graph<E> G, E source) {
        this.source = source;
        for (E v: G.vertexes())
            if (!visit.contains(v))
                dfs(G, v);
    }

    private void dfs(Graph<E> G, E v) {
        visit.add(v);
        for (E w: G.adj(v))
            if (!visit.contains(w))
                dfs(G, w);
    }

    @Override
    public E source() {
        return source;
    }

    @Override
    public boolean connected(E v) {
        return visit.contains(v);
    }

    @Override
    public int count() {
        return visit.size();
    }
}
