package com.siwoo.algo.sedgewick.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;

public class LinkedList<E> implements Stack<E>, Queue<E>, Bag<E> {
    
    private class Node {
        E e;
        Node next, prev;

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return String.format("(%s)", e);
        }
    }
    
    private Node root, leaf;
    private int N;
    
    public void pushFirst(E e) {
        checkNotNull(e);
        Node old = root;
        root = new Node(e);
        root.next = old;
        if (old == null)
            leaf = root;
        else
            old.prev = root;
        N++;
    }

    public E popFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = root.e;
        root = root.next;
        N--;
        if (isEmpty())
            leaf = null;
        else
            root.prev = null;
        return e;
    }

    public void pushLast(E e) {
        checkNotNull(e);
        Node old = leaf;
        leaf = new Node(e);
        leaf.prev = old;
        if (old == null)
            root = leaf;
        else
            old.next = leaf;
        N++;
    }

    public E popLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = leaf.e;
        leaf = leaf.prev;
        N--;
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
        return leaf.e;
    }
    
    @SuppressWarnings("unchecked")
    private E[] toArray() {
        E[] elements = (E[]) new Object[N];
        return toArray(root, 0, elements);
    }

    private E[] toArray(Node root, int i, E[] elements) {
        if (root == null) {
            assert i == N;
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
    
    public void put(E e) {
        root = put(root, e);
    }

    private Node put(Node root, E e) {
        if (root == null) {
            pushLast(e);
            return leaf;
        }
        if (root.e.equals(e)) root.e = e;
        else root.next = put(root.next, e);
        return root;
    }
    
    public E get(E e) {
        checkNotNull(e);
        Node node = get(root, e);
        return node == null? null: node.e;
    }

    private Node get(Node root, E e) {
        if (root == null) return null;
        return root.e.equals(e)? root: get(root.next, e);
    }

    public void delete(E e) {
        checkNotNull(e);
        delete(root, e);
    }

    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        
        ll.delete("b");

        System.out.println(ll.peekFirst());
        System.out.println(ll.peekLast());
        
        ll.delete("c");

        System.out.println(ll.peekFirst());
        System.out.println(ll.peekLast());
        
        ll.delete("a");
        System.out.println(ll.isEmpty());
    }

    private void delete(Node root, E e) {
        if (root == null) return;
        if (root.e.equals(e)) {
            Node left = root.prev;
            Node right = root.next;
            if (left != null)
                left.next = right;
            else
                this.root = right;
            if (right != null)
                right.prev = left;
            else
                this.leaf = left;
            N--;
        }
        delete(root.next, e);
    }

    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
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

//    public static void main(String[] args) {
//        Queue<String> q = new LinkedList<>();
//        In in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tobe.txt");
//        while (!in.isEmpty()) {
//            String e = in.readString();
//            if (e.equals("-"))
//                System.out.print(q.dequeue() + " ");
//            else if (!e.isEmpty())
//                q.enqueue(e);
//        }   
//        System.out.println("(" + q.size() +" left on the queue)");
//        for (String e: q)
//            System.out.println(e);
//
//        Stack<String> stack = new LinkedList<>();
//        in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tobe.txt");
//        while (!in.isEmpty()) {
//            String e = in.readString();
//            if (e.equals("-"))
//                System.out.print(stack.pop() + " ");
//            else if (!e.isEmpty())
//                stack.push(e);
//        }
//        System.out.println("(" + stack.size() +" left on the stack)");
//        for (String e: stack)
//            System.out.println(e);
//
//        Bag<Double> numbers = new LinkedList<>();
//        in = new In(AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/stats.txt");
//        while (!in.isEmpty())
//            numbers.add(in.readDouble());
//        double sum = 0;
//        for (double e: numbers)
//            sum += e;
//        double mean = sum / numbers.size();
//
//        sum = 0;
//        for (double e: numbers)
//            sum += (e - mean) * (e - mean);
//        double stddev = Math.sqrt(sum / (numbers.size()-1));
//
//        System.out.printf("Mean: %.2f%n", mean);
//        System.out.printf("Std dev: %.2f%n", stddev);
//    }
}
