package com.siwoo.algo.sedgewick.collection;

/**
 * 항목을 삭제할 수 없고 순회가능한 컬렉션.
 */
public interface Bag<E> extends Iterable<E> {

    /**
     * add e in the bag.
     * @param e
     */
    void add(E e);

    /**
     * is the bag empty?
     * @return
     */
    boolean isEmpty();

    /**
     * number of items in the bag.
     * @return
     */
    int size();
    
}
