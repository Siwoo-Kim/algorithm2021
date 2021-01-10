package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.TREE)
public class P2250 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Set<Integer> rootSet = new HashSet<>();
        for (int i=1; i<=N; i++)
            rootSet.add(i);
        int[][] nodes = new int[N+1][2];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(token.nextToken()),
                    left = Integer.parseInt(token.nextToken()),
                    right = Integer.parseInt(token.nextToken());
            nodes[p][0] = left != -1? left: 0;
            nodes[p][1] = right != -1? right: 0;
            rootSet.remove(left);
            rootSet.remove(right);
        }
        int root = rootSet.stream().findFirst().get();
        int[] indices = go(root, nodes, new int[N]);
        Tree tree = new Tree(root);
        for (int i=0; i<indices.length; i++) {
            int index = indices[i];
            Tree.Node node = tree.get(index);
            if (node == null) throw new IllegalArgumentException();
            if (nodes[index][0] != 0)
                node.left = new Tree.Node(nodes[index][0]);
            if (nodes[index][1] != 0)
                node.right = new Tree.Node(nodes[index][1]);
        }
        int[] a = tree.maxArea();
        System.out.println(a[0] + " " + a[1]);
    }

    static int index;
    
    private static int[] go(int root, int[][] nodes, int[] indices) {
        if (root == 0) return indices;
        indices[index++] = root;
        go(nodes[root][0], nodes, indices);
        go(nodes[root][1], nodes, indices);
        return indices;
    }

    private static class Tree {

        private static class Node {
            int index;
            Node left, right;

            public Node(int index) {
                this.index = index;
            }
        }
        
        private Map<Integer, Integer> columns = new HashMap<>();
        private Map<Integer, List<Integer>> depths = new HashMap<>();
        private Node root;
        private int cnt, maxDepth;

        public Tree(int root) {
            this.root = new Node(root);
        }
        
        public int[] maxArea() {
            inOrder(root, 0);
            int maxArea = 0, depth = 0;
            for (int i=0; i<=maxDepth; i++) {
                int min = Integer.MAX_VALUE, max = 0;
                for (int node: depths.get(i)) {
                    min = Math.min(min, columns.get(node));
                    max = Math.max(max, columns.get(node));
                }
                int area = max-min+1;
                if (maxArea < area) {
                    maxArea = area;
                    depth = i;
                }
            }
            return new int[]{depth+1, maxArea};
        }

        private void inOrder(Node root, int depth) {
            if (root == null) return;
            depths.putIfAbsent(depth, new ArrayList<>());
            depths.get(depth).add(root.index);
            maxDepth = Math.max(depth, maxDepth);
            inOrder(root.left, depth+1);
            columns.put(root.index, ++cnt);
            inOrder(root.right, depth+1);
        }

        public Node get(int i) {
            return get(root, i);
        }

        private Node get(Node root, int i) {
            if (root == null) return null;
            if (root.index == i) return root;
            Node node = get(root.left, i);
            if (node == null)
                node = get(root.right, i);
            return node;
        }
    }
}
