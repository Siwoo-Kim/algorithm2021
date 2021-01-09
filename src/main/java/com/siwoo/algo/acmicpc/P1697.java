package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

@Using(algorithm = Algorithm.BFS)
public class P1697 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, MAX = 100000;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        Map<Integer, Integer> distTo = new HashMap<>();
        List<Function<Integer, Integer>> paths = Arrays.asList(
                v -> v+1,
                v -> v-1,
                v -> v << 1);
        distTo.putIfAbsent(N, 0);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == K) {
                System.out.println(distTo.get(v));
                return;
            }
            for (Function<Integer, Integer> p: paths) {
                int w = p.apply(v);
                if (w >= 0 && w <= MAX 
                        && !distTo.containsKey(w)) {
                    distTo.put(w, distTo.get(v) + 1);
                    q.add(w);
                }
            }
        }
    }
}
