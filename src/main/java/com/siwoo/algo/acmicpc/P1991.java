package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.TREE)
public class P1991 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Tree tree = new Tree();
        tree.root = new Tree.Node("A");
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            String p = token.nextToken(),
                    left = token.nextToken(),
                    right = token.nextToken();
            Tree.Node parent = tree.get(p);
            if (!".".equals(left))
                parent.left = new Tree.Node(left);
            if (!".".equals(right))
                parent.right = new Tree.Node(right);
        }
        String order = tree.preOrder();
        System.out.println(order);
        order = tree.inOrder();
        System.out.println(order);
        order = tree.postOrder();
        System.out.println(order);
    }
    
    private static class Tree {
        private static class Node {
            String s;
            Node left, right;
            public Node(String s) {
                this.s = s;
            }
        }
        
        private Node root;

        public Node get(String s) {
            return get(root, s);
        }

        private Node get(Node root, String s) {
            if (root == null) return null;
            if (root.s.equals(s)) return root;
            Node node = get(root.left, s);
            if (node == null)
                node = get(root.right, s);
            return node;
        }
        
        public String preOrder() {
            return preOrder(root, new StringBuilder()).toString();
        }

        private StringBuilder preOrder(Node root, StringBuilder sb) {
            if (root == null) return null;
            sb.append(root.s);
            preOrder(root.left, sb);
            preOrder(root.right, sb);
            return sb;
        }
        
        public String inOrder() {
            return inOrder(root, new StringBuilder()).toString();
        }

        private Object inOrder(Node root, StringBuilder sb) {
            if (root == null) return null;
            inOrder(root.left, sb);
            sb.append(root.s);
            inOrder(root.right, sb);
            return sb;
        }

        public String postOrder() {
            return postOrder(root, new StringBuilder()).toString();
        }

        private String postOrder(Node root, StringBuilder sb) {
            if (root == null) return null;
            postOrder(root.left, sb);
            postOrder(root.right, sb);
            sb.append(root.s);
            return sb.toString();
        }
    }
}
