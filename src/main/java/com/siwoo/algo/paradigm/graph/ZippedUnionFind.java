package com.siwoo.algo.paradigm.graph;

import java.util.HashMap;
import java.util.Map;


public class ZippedUnionFind<E> implements UnionFind<E> {
    private Map<E, E> components = new HashMap<>(10000000);
    private Map<E, Integer> sizes = new HashMap<>(10000000);
    private int size;

    @Override
    public void put(E p) {
        if (!components.containsKey(p)) {
            size++;
            components.put(p, p);
            sizes.put(p, 1);
        }
    }

    /**
     * link p's root and q's root
     * 
     * @param p
     * @param q
     */
    @Override
    public void union(E p, E q) {
        p = find(p);
        q = find(q);
        if (p.equals(q)) return;
        if (sizes.get(p) < sizes.get(q)) {
            E t = p;
            p = q;
            q = t;
        }
        sizes.put(p, sizes.get(p) + sizes.get(q));
        components.put(q, p);
        size--;
    }

    /**
     * in-variant:
     *  if p and q is connected, should be find(p) == find(q)
     *  
     * @param p
     * @return
     */
    @Override
    public E find(E p) {
        if (!components.containsKey(p))
            throw new IllegalArgumentException();
        // is the p root?
        E parent = components.get(p);
        if (parent.equals(p)) return p;
        E root = find(parent);
        components.put(p, root);
        return root;
    }

    @Override
    public boolean connected(E p, E q) {
        p = find(p);
        q = find(q);
        return p.equals(q);
    }

    @Override
    public int count() {
        return size;
    }
}
