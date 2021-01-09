package com.siwoo.algo.acmicpc;

import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

public class P13913 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, MAX = 100000;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        Map<Integer, Integer> distTo = new HashMap<>();
        Map<Integer, Integer> pathTo = new HashMap<>();
        List<Function<Integer, Integer>> paths = Arrays.asList(
                v -> v+1,
                v -> v-1,
                v -> v << 1);
        distTo.put(N, 0);
        pathTo.put(N, null);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == K) {
                StringBuilder sb = new StringBuilder();
                sb.append(distTo.get(v));
                sb.append("\n");
                append(v, sb, pathTo);
                System.out.println(sb.toString());
                return;
            }
            for (Function<Integer, Integer> p: paths) {
                int w = p.apply(v);
                if (w >= 0 && w <= MAX
                        && !distTo.containsKey(w)) {
                    distTo.put(w, distTo.get(v) + 1);
                    pathTo.put(w, v);
                    q.add(w);
                }
            }
        }
    }

    private static void append(int v, StringBuilder sb, Map<Integer, Integer> pathTo) {
        if (pathTo.get(v) == null) {
            sb.append(v);
            return;
        }
        append(pathTo.get(v), sb, pathTo);
        sb.append(" ").append(v);
    }
}
