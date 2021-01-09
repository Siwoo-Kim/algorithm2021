package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.BFS)
public class P13549 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, MAX = 100000;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        Deque<Integer> q = new LinkedList<>();
        q.addFirst(N);
        Map<Integer, Integer> distTo = new HashMap<>();
        distTo.put(N, 0);
        while (!q.isEmpty()) {
            int v = q.poll(),
                    d = distTo.get(v);
            if (v == K) {
                System.out.println(distTo.get(v));
                return;
            }
            int w = v - 1;
            if (w >= 0 && w <= MAX && !distTo.containsKey(w)) {
                distTo.put(w, d + 1);
                q.addLast(w);
            }
            w = v + 1;
            if (w >= 0 && w <= MAX && !distTo.containsKey(w)) {
                distTo.put(w, d + 1);
                q.addLast(w);
            }
            w = v << 1;
            if (w >= 0 && w <= MAX
                    // 드모르간의 법칙. ~a || ~b == ~(a && b)
                    && !(distTo.containsKey(w) && distTo.get(w) <= d)) {    
                distTo.put(w, d);
                q.addFirst(w);
            }
        }
    }
}
