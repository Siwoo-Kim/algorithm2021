package com.siwoo.algo.sedgewick.collection;


public interface OrderedSymbolTable<K extends Comparable<K>, V> 
        extends SymbolTable<K, V> {

    /**
     * the smallest key in the table.
     * 
     * @return
     */
    K min();

    /**
     * the largest key in the table.
     * 
     * @return
     */
    K max();

    /**
     * pop the smallest key in the table.
     * 
     * @return
     */
    default K popMin() {
        K min = min();
        delete(min);
        return min;
    }

    /**
     * pop the largest key in the table.
     * 
     * @return
     */
    default K popMax() {
        K max = max();
        delete(max);
        return max;
    }

    /**
     * the largest key smaller or equal to the given key.
     * 
     * @param key
     * @return
     */
    K floor(K key);

    /**
     * the smallest key larger or equal to the given key.
     * 
     * @param key
     * @return
     */
    K ceiling(K key);

    /**
     * the number of keys smaller than given key.
     * 
     * i == rank(select(i))
     * @param key
     * @return
     */
    int rank(K key);

    /**
     * k'th key (0 based)
     * 
     * K == select(rank(K))
     * @param k
     * @return
     */
    K select(int k);

    /**
     * the number of keys between left..right
     * 
     * @param left
     * @param right
     * @return
     */
    default int size(K left, K right) {
        if (right.compareTo(left) < 0) return 0;
        if (contains(right))
            return rank(right) - rank(left) + 1; //count right itself
        else
            return rank(right) - rank(left);
    }

    /**
     * all the keys between left..right
     * 
     * @param left
     * @param right
     * @return
     */
    Iterable<K> keys(K left, K right);

    @Override
    default Iterable<K> keys() {
        return keys(min(), max());
    }
}
