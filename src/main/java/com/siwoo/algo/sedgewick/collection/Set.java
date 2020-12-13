package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;

public class Set<E> implements Iterable<E> {
    private HashTable<E, Object> table = new HashTable<>();

    /**
     * add e into the set.
     * 
     * @param e
     */
    public void add(E e) {
        table.put(e, e);
    }

    /**
     * delete e from the set.
     * 
     * @param e
     */
    public void delete(E e) {
        table.delete(e);
    }

    /**
     * is there e in the set?
     * 
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return table.contains(e);
    }

    /**
     * is the set empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return table.isEmpty();
    }

    /**
     * the number of elements in the set.
     * 
     * @return
     */
    public int size() {
        return table.size();
    }

    /**
     * union current set & given set.
     * 
     * @param set
     * @return
     */
    public Set<E> union(Set<E> set) {
        Set<E> union = new Set<>();
        for (E e: set)
            union.add(e);
        for (E e: this)
            union.add(e);
        return union;
    }

    /**
     * intersects current set & given set.
     * 
     * @param set
     * @return
     */
    public Set<E> intersects(Set<E> set) {
        Set<E> intersects = new Set<>();
        for (E e: set)
            if (contains(e))
                intersects.add(e);
        return intersects;
    }
    
    @Override
    public Iterator<E> iterator() {
        return table.keys().iterator();
    }

    public static void main(String[] args) {
        class DeDup {
            Set<String> set = new Set<>();
            
            void dedup(In in) {
                while (!in.isEmpty()) {
                    String key = in.readString();
                    if (!set.contains(key)) {
                        set.add(key);
                        System.out.println(key);
                    }
                }
            }
        }
        String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyTale.txt";
        In in = new In(path);
        new DeDup().dedup(in);
    }
}
