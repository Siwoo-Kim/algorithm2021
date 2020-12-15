package com.siwoo.algo.sedgewick.collection;

import edu.princeton.cs.algs4.In;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    @SuppressWarnings("unchecked")
    private E[] a = (E[]) new Object[1];
    private int head, tail, n;
    
    @Override
    public void enqueue(E e) {
        if (n == a.length)
            resize(a.length << 1);
        a[tail++] = e;
        n++;
        if (a.length == tail)
            tail = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        assert newSize >= n;
        E[] copy = (E[]) new Object[newSize];
        for (int i=0; i<n; i++)
            copy[i] = a[(head+i)%a.length];
        a = copy;
        head = 0;
        tail = n;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = a[head];
        a[head++] = null;
        n--;
        if (head == a.length)
            head = 0;
        if (n > 0 && a.length << 2 == n)
            resize(a.length << 1);
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return a[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<E> {
        int i = 0;
        
        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public E next() {
            E e = a[(i+head)%a.length];
            i++;
            return e;
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i=0; i<n; i++)
            sb.append(a[(head+i)%a.length])
                    .append(i==n-1?"": ", ");
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<String> q = new ArrayQueue<>();
        In in = new In("src/main/resources/algs4-data/tobe.txt");
        while (!in.isEmpty()) {
            String e = in.readString();
            if (e.equals("-"))
                System.out.print(q.dequeue() + " ");
            else if (!e.isEmpty())
                q.enqueue(e);
        }
        System.out.println("(" + q.size() +" left on the queue)");
        for (String e: q)
            System.out.println(e);
    }
}
