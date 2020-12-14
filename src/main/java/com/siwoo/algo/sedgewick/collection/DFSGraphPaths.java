package com.siwoo.algo.sedgewick.collection;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class DFSGraphPaths<E> implements GraphPaths<E> {
    private final E source;
    private final Map<E, E> paths = new HashMap<>();

    public DFSGraphPaths(Graph<E> G, E source) {
        checkNotNull(G, source);
        this.source = source;
        dfs(G, source, source);
    }

    private void dfs(Graph<E> G, E v, E p) {
        paths.put(v, p);
        for (E w: G.adj(v))
            if (!paths.containsKey(w))
                dfs(G, w, v);
    }

    @Override
    public E source() {
        return source;
    }

    @Override
    public boolean hasPathTo(E v) {
        checkNotNull(v);
        return paths.containsKey(v);
    }

    @Override
    public Iterable<E> pathTo(E v) {
        if (!hasPathTo(v)) return new LinkedList<>();
        LinkedList<E> result = new LinkedList<>();
        while (!v.equals(source)) {
            result.add(v);
            v = paths.get(v);
        }
        result.add(source);
        return result;
    }
}
