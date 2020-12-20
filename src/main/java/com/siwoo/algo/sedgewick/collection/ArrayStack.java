package com.siwoo.algo.sedgewick.collection;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 배열 스택.
 *  배열 a 에 요소를 담고, n 에 요소의 갯수를 기록.
 *  
 *  불변식.
 *      1. 요소가 배열에 저장된 순서는 삽입된 순서와 동일하다.
 *      2. 스택이 비어있으면 n == 0
 *      3. 스택의 가장 위 항목은 스택이 비어있지 않다면 a[n-1]
 *  
 *  배열 크기 변경.
 *      n == a.length 라면 배열 크기를 두 배 증가.
 *      n == a.length/4 라면 배열 크기를 두 배 감소. 단, n != 0
 *   
 *  loitering
 *      객체를 메모리에서 해제하기 위해 가비지 컬렉션 정책 수행할 수 있도록
 *      참조 제거.
 *      
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
    private E[] a;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        a = (E[]) new Object[2];
    }
    
    @Override
    public void push(E e) {
        if (n == a.length) 
            resize(n << 1);
        a[n++] = e;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        E[] copy = (E[]) new Object[newSize];
        for (int i=0; i<n; i++)
            copy[i] = a[i];
        a = copy;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        E e = a[--n];
        a[n] = null; //help GC
        if (n > 0 && a.length>>2 == n)
            resize(a.length>>1);
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return a[n-1];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean contains(E e) {
        for (int i=0; i<n; i++)
            if (a[i].equals(e))
                return true;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }
    
    private class Iter implements Iterator<E> {
        private int end = n-1;
        
        @Override
        public boolean hasNext() {
            return end >= 0;
        }

        @Override
        public E next() {
            return a[end--];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i=n-1; i>=0; i--)
            sb.append(a[i]).append(i == 0? "": ", ");
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<String> stack = new ArrayStack<>();
        In in = new In("src/main/resources/algs4-data/tobe.txt");
        while (!in.isEmpty()) {
            String e = in.readString();
            if (e.equals("-"))
                System.out.print(stack.pop() + " ");
            else if (!e.isEmpty())
                stack.push(e);
        }
        System.out.println("(" + stack.size() +" left on the stack)");
        
        for (String e: stack)
            System.out.println(e);
    }
}
