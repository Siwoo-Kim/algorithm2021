package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static com.google.common.base.Preconditions.checkNotNull;

public class TopologicalOrder<E> {
    private Iterable<E> order;

    public TopologicalOrder(Digraph<E> G) {
        checkNotNull(G);
//        DirectedGraphCycle<E> cycle = new DirectedGraphCycle<>(G);
//        if (cycle.hasCycle())
//            throw new IllegalArgumentException();
        DFS<E> dfs = new DFS<>(G);
        LinkedList<E> order = new LinkedList<>();
        for (E e : dfs.reversedPostOrder())
            order.enqueue(e);
        this.order = order;
    }

    public Iterable<E> getOrder() {
        return order;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/jobs.txt";
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path)));
        DirectedGraph<String> G = new DirectedGraph<>();
        while (scanner.hasNextLine()) {
            StringTokenizer token = new StringTokenizer(scanner.nextLine(), "/");
            String v = token.nextToken();
            while (token.hasMoreTokens()) {
                String next = token.nextToken();
                Diedge<String> edge = new Diedge<>(v, next);
                G.addEdge(edge);
            }
        }
        TopologicalOrder<String> top = new TopologicalOrder<>(G);
        for (String e: top.getOrder())
            System.out.println(e + " ");
    }
}
