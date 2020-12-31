package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.SEGMENT_TREE)
public class P2042 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long[] a;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken()) + Integer.parseInt(token.nextToken());
        a = new long[N];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            a[i] = Long.parseLong(token.nextToken());
        }
        SegmentTree tree = new SegmentTree(a);
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int op = Integer.parseInt(token.nextToken()),
                    v = Integer.parseInt(token.nextToken());
            long w = Long.parseLong(token.nextToken());
            if (op == 1)
                tree.update(v-1, w);
            else
                System.out.println(tree.query(v-1, (int) w-1));
        }
    }
    
    private static class SegmentTree {
        private long[] a, tree;
        private int N;

        public SegmentTree(long[] a) {
            this.a = a;
            this.N = a.length;
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            tree = new long[(1 << (h+1))];
            build(1, 0, N-1);
        }

        private long build(int node, int left, int right) {
            if (left == right) return tree[node] = a[left];
            int mid = (left + right) / 2;
            int child = node<<1;
            long l = build(child, left, mid);
            child++;
            long r = build( child, mid+1, right);
            return tree[node] = l + r;
        }


        public long query(int from, int to) {
            return query(1, 0, N-1, from, to);
        }

        private long query(int node, int left, int right, int from, int to) {
            if (left > to || right < from) return 0;
            if (left >= from && right <= to) return tree[node];
            int mid = (left + right) / 2;
            int child = node << 1;
            long sum = query(child, left, mid, from, to);
            child++;
            sum += query(child, mid+1, right, from, to);
            return sum;
        }

        public void update(int index, long value) {
            update(1, 0, N-1, index, index, a[index], value);
        }

        private long update(int node, int left, int right, int from, int to, long prevValue, long value) {
            if (left > to || right < from) return 0;
            if (left == right) return tree[node] = a[left] = value;
            int mid = (left + right) / 2;
            int child = node << 1;
            update(child, left, mid, from, to, prevValue, value);
            child++;
            update(child, mid+1, right, from, to, prevValue, value);
            return tree[node] += value - prevValue;
        }
    }
}
