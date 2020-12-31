package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.SEGMENT_TREE)
public class P10868 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] a;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        a = new int[N];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            a[i] = Integer.parseInt(token.nextToken());
        }
        SegmentTree st = new SegmentTree(a);
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(token.nextToken()) - 1,
                    end = Integer.parseInt(token.nextToken()) - 1;
            System.out.println(st.query(start, end));
        }
    }
    
    private static class SegmentTree {
        private int[] tree, a;

        public SegmentTree(int[] a) {
            this.a = a;
            int height = (int) Math.ceil(Math.log(a.length) / Math.log(2));
            tree = new int[1 << (height+1)];
            build(1, 0, a.length-1);
        }

        private int build(int node, int start, int end) {
            if (start == end) return tree[node] = a[start];   //leaf
            else {
                int mid = (start+end) / 2;
                int m1 = build(node<<1, start, mid); //left
                int m2 = build((node<<1)+1,mid+1, end);  //right
                return tree[node] = Math.min(m1, m2);
            }
        }

        public int query(int start, int end) {
            return query(1, 0, a.length-1, start, end);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (start > right || end < left) return -1;
            if (start >= left && end <= right) return tree[node];
            int mid = (start + end) / 2;
            int m1 = query((node<<1), start, mid, left, right);
            int m2 = query((node<<1)+1, mid+1, end, left, right);
            return min(m1, m2);
        }

        private int min(int m1, int m2) {
            if (m1 == -1) return m2;
            if (m2 == -1) return m1;
            return Math.min(m1, m2);
        }
    }
}
