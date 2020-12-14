package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.paradigm.graph.ZippedUnionFind;

/**
 * union-find 을 이용한 연결된 정정들 찾기.
 * 
 * @param <E>
 */
public class UFGraphSearch<E> implements GraphSearch<E> {
    private final ZippedUnionFind<E> uf = new ZippedUnionFind<>();
    private final E source;

    public UFGraphSearch(Graph<E> G, E source) {
        this.source = source;
        for (E v: G.vertexes())
            uf.put(v);
        for (E v: G.vertexes())
            for (E w: G.adj(v))
                uf.union(v, w);
    }

    @Override
    public E source() {
        return source;
    }

    @Override
    public boolean connected(E v) {
        return uf.connected(source, v);
    }

    @Override
    public int count() {
        return uf.size(source);
    }
}
