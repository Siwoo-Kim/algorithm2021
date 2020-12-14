package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [connected component]
 *  그래프 G 에서 연결 요소(서로 연결된 정점들의 그룹)을 분류.
 *  
 * problem
 *  * 그래프 G 에서 정점 v 에 연결된 모든 정점 w 을 동치 관계로 묶고, 
 *  그러한 컴포넌트가 몇개인지 알고 싶다. 
 *
 * components
 *  1. 연결 요소의 id
 *
 *  algorithm.
 *      1. 
 *      2. 
 *
 *  limitation
 *      1.
 *
 *  time complexity
 *      * 
 */
public class ConnectedComponent<E> {
    private final Map<E, Integer> ids = new HashMap<>();
    private int count;

    public ConnectedComponent(Graph<E> G) {
        checkNotNull(G);
        for (E v: G.vertexes()) {
            if (!ids.containsKey(v))
                dfs(v, ++count, G);
        }
    }

    private void dfs(E v, int id, Graph<E> G) {
        ids.put(v, id);
        for (E w: G.adj(v))
            if (!ids.containsKey(w))
                dfs(w, id, G);
    }
    
    public boolean connected(E v, E w) {
        checkNotNull(v, w);
        int vid = ids.get(v),
                wid = ids.get(w);
        return vid != -1 && (vid == wid);
    }
    
    public int id(E v) {
        checkNotNull(v);
        return ids.getOrDefault(v, -1);
    }
    
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        final String file = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyG.txt";
        In in = new In(file);
        int V = in.readInt(),
                E = in.readInt();
        Graph<Integer> G = new UnDirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
        }
        ConnectedComponent<Integer> CC = new ConnectedComponent<>(G);
        int M = CC.count;
        @SuppressWarnings("unchecked")
        Queue<Integer>[] components = new Queue[M+1];
        for (int i=1; i<=M; i++)
            components[i] = new LinkedList<>();
        for (int v: G.vertexes())
            components[CC.id(v)].enqueue(v);
        for (int i=1; i<=M; i++) {
            System.out.print("id " + i +": ");
            for (int v: components[i])
                System.out.print(v + " ");
            System.out.println();
        }
    }
}
