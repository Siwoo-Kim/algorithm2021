package com.siwoo.algo.sedgewick.collection;

/**
 * LIFO 스택.
 *  저장한 순서를 역순하므로 가장 관심있는 데이터 (latest) 을 가장 빨리 얻는다는 특성. 
 *  
 *  스택의 top 에만 접근한다고 생각하지 말고,
 *      가장 위쪽에 유효한 candidate 을 뭉쳐서 생각해야 스택을 잘 활용할 수 있다.
 *      
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

    boolean contains(E w);
}
