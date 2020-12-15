package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 그래프 G 의 모든 정점의 연결성을 이용하여 "연결 요소" 집합으로 분류.
 */
public interface ConnectedComponent<E> {

    /**
     * is v-w connected?
     * 
     * @param v
     * @param w
     * @return
     */
    boolean connected(E v, E w);

    /**
     * an id of the given v
     * 
     * @param v
     * @return
     */
    int id(E v);

    /**
     * the number of connected components
     * 
     * @return
     */
    int count();

    static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyDG.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        int V = scanner.nextInt(),
                E = scanner.nextInt();
        Digraph<Integer> G = new DirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new DiEdge<>(scanner.nextInt(), scanner.nextInt());
            G.addEdge(edge);
        }
        ConnectedComponent<Integer> cc = new KosarajuSharir<>(G);
        System.out.println(cc.count() + " strong components.");
        Queue<Integer>[] connectedComponent = new LinkedList[cc.count()+1];
        for (int i=1; i<=cc.count(); i++)
            connectedComponent[i] = new LinkedList<>();
        for (int v: G.vertexes())
            connectedComponent[cc.id(v)].enqueue(v);
        for (int i=1; i<=cc.count(); i++) {
            StringBuilder sb = new StringBuilder(i).append(": ");
            for (int w: connectedComponent[i])
                sb.append(w).append(" ");
            System.out.println(sb);
        }
    }
}
