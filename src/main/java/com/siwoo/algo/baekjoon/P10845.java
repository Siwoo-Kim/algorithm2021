package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Using(algorithm = Algorithm.QUEUE)
public class P10845 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        Queue<String> q = new Queue<>();
        for (int i=0; i<N; i++) {
            String[] s = reader.readLine().split("\\s+");
            if ("push".equals(s[0])) {
                q.push(s[1]);
                continue;
            }
            if ("front".equals(s[0])) {
                String e = q.front();
                sb.append(e == null? -1: e);
            }
            if ("back".equals(s[0])) {
                String e = q.back();
                sb.append(e == null? -1: e);
            }
            if ("pop".equals(s[0])) {
                String e = q.pop();
                sb.append(e == null? -1: e);
            }
            if ("empty".equals(s[0]))
                sb.append(q.isEmpty()? 1: 0);
            if ("size".equals(s[0]))
                sb.append(q.size());
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static class Queue<E> {
        private class Node {
            Node next;
            E e;

            public Node(E e) {
                this.e = e;
            }
        }
        
        private Node head, tail;
        private int N;
        
        public void push(E e) {
            Node node = head;
            head = new Node(e);
            if (isEmpty())
                tail = head;
            else
                node.next = head;
            N++;
        }
        
        public E pop() {
            if (isEmpty())
                return null;
            E e = tail.e;
            tail = tail.next;
            N--;
            if (isEmpty())
                head = tail;
            return e;
        }
        
        public int size() {
            return N;
        }
        
        public boolean isEmpty() {
            return N == 0;
        }
        
        public E front() {
            if (isEmpty()) return null;
            return tail.e;
        }
        
        public E back() {
            if (isEmpty()) return null;
            return head.e;
        }
    }
}
