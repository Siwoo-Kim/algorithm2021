package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.siwoo.algo.util.Algorithm.STACK;

@Using(algorithm = STACK)
public class P10828 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<String> stack  = new Stack<>();
        for (int i=0; i<N; i++) {
            String[] s = reader.readLine().split("\\s+");
            if ("push".equals(s[0])) {
                stack.push(s[1]);
                continue;
            }
            if ("top".equals(s[0])) {
                String e = stack.peek();
                sb.append(e == null? -1: e);
            }
            if ("pop".equals(s[0])) {
                String e = stack.pop();
                sb.append(e == null? -1: e);
            }
            if ("empty".equals(s[0]))
                sb.append(stack.isEmpty()? 1: 0);
            if ("size".equals(s[0]))
                sb.append(stack.size());
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static class Stack<E> {
        
        private class Node {
            private E e;
            private Node next;

            public Node(E e) {
                this.e = e;
            }
        }
        
        private Node root;
        private int N;
        
        public void push(E data) {
            Node node = root;
            root = new Node(data);
            root.next = node;
            N++;
        }
        
        public E pop() {
            if (isEmpty()) 
                return null;
            E e = root.e;
            root = root.next;
            N--;
            return e;
        }

        public E peek() {
            if (isEmpty())
                return null;
            return root.e;
        }

        public int size() {
            return N;
        }
        
        public boolean isEmpty() {
            return size() == 0;
        }
    }
}
