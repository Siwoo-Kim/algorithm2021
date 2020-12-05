package com.siwoo.algo.sedgewick.collection;

/**
 * FIFO 큐.
 *  공정한 정책 때문에 순서가 보장되는 특성.
 * @param <E>
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * enqueue e in the queue
     * @param e
     */
    void enqueue(E e);

    /**
     * dequeue e from the queue
     * @return
     */
    E dequeue();

    /**
     * peek e from the queue
     */
    E peek();
    
    /**
     * is the queue empty?
     * @return
     */
    boolean isEmpty();

    /**
     * number of items in the queue
     * @return
     */
    int size();
}
