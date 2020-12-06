package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Stack<E>, Queue<E>, Bag<E> {
    
    private class Node {
        E e;
        Node next, prev;

        public Node(E e) {
            this.e = e;
        }
    }
    
    private Node root, leaf;
    private int n;
    
    public void pushFirst(E e) {
        Node old = root;
        root = new Node(e);
        root.next = old;
        if (old == null)
            leaf = root;
        else
            old.prev = root;
        n++;
    }

    public E popFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = root.e;
        root = root.next;
        n--;
        if (isEmpty())
            leaf = null;
        else
            root.prev = null;
        return e;
    }

    public void pushLast(E e) {
        Node old = leaf;
        leaf = new Node(e);
        leaf.prev = old;
        if (old == null)
            root = leaf;
        else
            old.next = leaf;
        n++;
    }

    public E popLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = leaf.e;
        leaf = leaf.prev;
        n--;
        if (isEmpty())
            root = null;
        else
            leaf.next = null;
        return e;
    }

    public E peekFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return root.e;
    }
    
    public E peekLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return root.e;
    }
    
    @SuppressWarnings("unchecked")
    private E[] toArray() {
        E[] elements = (E[]) new Object[n];
        return toArray(root, 0, elements);
    }

    private E[] toArray(Node root, int i, E[] elements) {
        if (root == null) {
            assert i == n;
            return elements;
        }
        toArray(root.next, i+1, elements);
        elements[i] = root.e;
        return elements;
    }

    @Override
    public void enqueue(E e) {
        pushFirst(e);
    }

    @Override
    public E dequeue() {
        return popLast();
    }

    @Override
    public void push(E e) {
        pushFirst(e);
    }

    @Override
    public E pop() {
        return popFirst();
    }

    @Override
    public E peek() {   // fail
        return peekLast();
    }

    @Override
    public void add(E e) {
        pushLast(e);
    }

    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public Iterator<E> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<E> {
        private Node c = root;
        
        @Override
        public boolean hasNext() {
            return c != null;
        }

        @Override
        public E next() {
            E e = c.e;
            c = c.next;
            return e;
        }
    }
    
    private class ReversedItr implements Iterator<E> {
        private Node c = leaf;

        @Override
        public boolean hasNext() {
            return c != null;
        }

        @Override
        public E next() {
            E e = c.e;
            c =  c.prev;
            return e;
        }
    }

    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        In in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tobe.txt");
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

        Stack<String> stack = new LinkedList<>();
        in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tobe.txt");
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

        Bag<Double> numbers = new LinkedList<>();
        in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/stats.txt");
        while (!in.isEmpty())
            numbers.add(in.readDouble());
        double sum = 0;
        for (double e: numbers)
            sum += e;
        double mean = sum / numbers.size();

        sum = 0;
        for (double e: numbers)
            sum += (e - mean) * (e - mean);
        double stddev = Math.sqrt(sum / (numbers.size()-1));

        System.out.printf("Mean: %.2f%n", mean);
        System.out.printf("Std dev: %.2f%n", stddev);
    }
}
