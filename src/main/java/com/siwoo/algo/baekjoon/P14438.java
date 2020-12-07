package com.siwoo.algo.baekjoon;

import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14438 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        a = Arrays.stream(reader.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        SegmentTree st = new SegmentTree(a);
        M = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int op = Integer.parseInt(token.nextToken()),
                    v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            if (op == 1)
                st.update(v-1, w);
            else {
                sb.append(st.query(v-1, w-1))
                        .append("\n");
            }
        }
        System.out.println(sb);
    }
    
    private static class SegmentTree {
        private int[] a, tree;
        private static int N;

        public SegmentTree(int[] a) {
            this.a = a;
            N = a.length;
            int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
            tree = new int[1<<h];
            build(1, 0, N-1);
        }

        private int build(int node, int left, int right) {
            if (left == right) return tree[node] = a[left];
            int mid = (left + right) / 2,
                    child = node << 1;
            return tree[node] = min(
                    build(child, left, mid), 
                    build(child+1, mid+1, right));
        }
        
        public int min(int m1, int m2) {
            if (m1 == -1) return m2;
            if (m2 == -1) return m1;
            return Math.min(m1, m2);
        }

        public void update(int index, int v) {
            update(1, 0, N-1, index, v);
        }

        private void update(int node, int left, int right, int index, int v) {
            if (left > index || right < index) return;
            if (left == right) {
                tree[node] = a[index] = v;
                return;
            }
            int mid = (left + right) / 2,
                    child = node << 1;
            update(child, left, mid, index, v);
            update(child+1, mid+1, right, index, v);
            tree[node] = min(tree[child], tree[child+1]);
        }

        public int query(int from, int to) {
            return query(1, 0, N-1, from, to);
        }

        private int query(int node, int left, int right, int from, int to) {
            if (left > to || right < from) return -1;
            if (left >= from && right <= to) return tree[node];
            int mid = (left + right) / 2,
                    child = node << 1;
            return min(
                    query(child, left, mid, from, to),
                    query(child+1, mid+1, right, from, to));
        }
    }
}
