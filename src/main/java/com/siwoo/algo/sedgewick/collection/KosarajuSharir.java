package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * todo: tail. need to fix
 * 
 * [algo] [코사라쥬-샤리르]
 *  두 정점 v 와 w 가 상호 도달 가능하면 "강하게 연결" 되었다고 한다.
 *  v-w, w-v 경로가 방향 그래프 G 에서 존재.
 *  만약 정점의 집합 v 가 있고, 모두 강하게 연결되어 있다면, 그 방향 그래프를 강하게 연결된 방향 그래프라 한다.
 *  방향 그래프에서 어떤 부분 정점 집합이 강한 연결성을 가지면 이 것을 connected component 라 한다.
 *  
 *  
 * problem
 *  * 방향 그래프에서 강하게 연결된 정점끼리 그룹화 하고 싶다.
 *  강하게 연결이란 v-w == w-v
 *  
 * components
 *  1. 그래프 G 의 간선의 방향이 반전된 그래프 GR
 *  2. GR 의 위상정렬. ( G 의 위상정렬은 output 정점 순이고, GR 은 input 정점 순이다. (반전 되었으므로)
 *  3. 위상정렬을 통한 dfs 방문.
 *
 *  algorithm.
 *      1. 그래프 G 을 간선에 대해서 반전한다. 이 그래프를 GR 이라 하자.
 *      2. GR 에 대한 topological order 을 구한다.
 *      3. topological order 순으로 dfs 을 진행한다.
 *
 *
 *  time complexity
 *      * 
 */
public class KosarajuSharir<E> implements ConnectedComponent<E> {
    private SymbolTable<E, Integer> ids = new HashTable<>();
    private int N;

    public KosarajuSharir(Digraph<E> G) {
        Digraph<E> GR = G.reverse();
        TopologicalOrder<E> top = new TopologicalOrder<>(GR);
        Set<E> visit = new Set<>();
        for (E v: top.getOrder()) {
            if (!visit.contains(v))     
                dfs(v, visit, ++N, G);
        }
    }

    private void dfs(E v, Set<E> visit, int id, Digraph<E> G) {
        visit.add(v);
        ids.put(v, id);
        for (E w: G.adj(v))
            if (!visit.contains(w))
                dfs(w, visit, id, G);
    }

    @Override
    public boolean connected(E v, E w) {
        return id(v) == id(w);
    }

    @Override
    public int id(E v) {
        checkNotNull(v);
        return ids.get(v);
    }

    @Override
    public int count() {
        return N;
    }
    
}
