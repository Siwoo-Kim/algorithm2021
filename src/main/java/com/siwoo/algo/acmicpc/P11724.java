package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P11724 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        UF uf = new UF();
        for (int v=1; v<=V; v++)
            uf.add(v);
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            uf.union(v, w);
        }
        System.out.println(uf.size());
    }
    
    private static class UF {
        private Map<Integer, Integer> components = new HashMap<>();
        private Map<Integer, Integer> sizes = new HashMap<>();
        private int N;

        public boolean contains(int v) {
            return components.containsKey(v);
        }

        public void add(int v) {
            if (!contains(v)) {
                components.put(v, v);
                sizes.put(v, 1);
                N++;
            }
        }

        public void union(int v, int w) {
            v = get(v);
            w = get(w);
            if (v == w) return;
            if (sizes.get(v) < sizes.get(w)) {
                int t = v;
                v = w;
                w = t;
            }
            sizes.put(v, sizes.get(w) + sizes.get(v));
            components.put(w, v);
            N--;
        }

        private int get(int v) {
            if (components.get(v) == v)
                return v;
            int root = get(components.get(v));
            components.put(v, root);
            return root;
        }

        public int size() {
            return N;
        }
    }
}
