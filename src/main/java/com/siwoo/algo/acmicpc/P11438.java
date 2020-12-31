package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.SEGMENT_TREE)
public class P11438 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> tree = new HashMap<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            tree.putIfAbsent(v, new ArrayList<>());
            tree.putIfAbsent(w, new ArrayList<>());
            Edge edge = new Edge(v, w);
            tree.get(v).add(edge);
            tree.get(w).add(edge.reverse());
        }
        SegmentTree segmentTree = new SegmentTree(1, tree);
        M = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            int u = segmentTree.query(v, w);
            sb.append(u).append("\n");
        }
        System.out.println(sb);
    }
    
    private static class SegmentTree {
        private Map<Integer, List<Edge>> tree;
        private Map<Integer, Node> postOrder = new HashMap<>();
        private Map<Integer, Integer> indexing = new HashMap<>();
        private int[] segmentTree;
        private int N, index;

        static class Node {
            private int e, depth;

            public Node(int e, int depth) {
                this.e = e;
                this.depth = depth;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "e=" + e +
                        ", depth=" + depth +
                        '}';
            }
        }

        public SegmentTree(int root, Map<Integer, List<Edge>> tree) {
            this.tree = tree;
            N = 2*(tree.size()-1)+1;
            postOrder(root, root, 0);
            for (int i=0; i<N; i++) {
                Node node = postOrder.get(i);
                if (!indexing.containsKey(node.e))
                    indexing.put(node.e, i);
            }
            int h = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
            segmentTree = new int[1<<h];
            build(1, 0, N-1);
        }
        
        public int query(int v, int w) {
            int i = indexing.get(v),
                    j = indexing.get(w);
            if (i > j) {
                int t = i;
                i = j;
                j = t;
            }
            int node = query(1, 0, N-1, i, j);
            return postOrder.get(segmentTree[node]).e;
        }

        private int query(int node, int left, int right, int from, int to) {
            if (left > to || right < from) return -1;
            if (left >= from && right <= to) return node;
            int mid = (left + right) / 2,
                    child = node << 1;
            int a1 = query(child, left, mid, from, to),
                    a2 = query(child+1, mid+1, right, from, to);
            if (a1 == -1) return a2;
            if (a2 == -1) return a1;
            return depthOf(a1) < depthOf(a2)? a1: a2;
        }

        private void build(int node, int left, int right) {
            if (left == right) segmentTree[node] = left;
            else {
                int mid = (left + right) / 2,
                        child = node << 1;
                build(child, left, mid);
                build(child+1, mid+1, right);
                segmentTree[node] = depthOf(child) < depthOf(child+1)? 
                        segmentTree[child]: segmentTree[child+1];
            }
        }

        private int depthOf(int node) {
            return postOrder.get(segmentTree[node]).depth;
        }

        private void postOrder(int node, int parent, int depth) {
            postOrder.put(index++, new Node(node, depth));
            for (Edge e: tree.get(node)) {
                if (e.w != parent) {
                    postOrder(e.w, node, depth+1);
                    postOrder.put(index++, new Node(node, depth));
                }
            }
        }
    }
    
    private static class Edge {
        final int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}