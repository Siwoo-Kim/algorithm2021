package com.siwoo.algo.sedgewick.collection;

/**
 * LIFO 스택.
 *  저장한 순서를 역순하므로 가장 흥미로운 데이터을 가장 빨리 얻는다는 특성. 
 * @param <E>
 */
public interface Stack<E> extends Iterable<E> {
    /**
     * push e in the stack.
     * @param e
     */
    void push(E e);

    /**
     * pop e from the stack.
     * @return
     */
    E pop();

    /**
     * peek e from the stack.
     * @return
     */
    E peek();

    /**
     * is the stack empty?
     * @return
     */
    boolean isEmpty();

    /**
     * number of items in the stack
     * @return
     */
    int size();
}
