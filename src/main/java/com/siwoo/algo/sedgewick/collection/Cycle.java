package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

/**
 * is there any cycle in the graph?
 */
public interface Cycle<E> {
    
    boolean hasCycle();
    
    Iterable<E> cycle();

    public static void main(String[] args) {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyDAG.txt";
        In in = new In(path);
        int V = in.readInt(),
                E = in.readInt();
        Graph<Integer> G = new DirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
            
        }
        System.out.println(G);
        Cycle<Integer> cycle = new DirectedGraphCycle<>(G);
        System.out.println(cycle.hasCycle());
        
        if (cycle.hasCycle())
            for (int e: cycle.cycle())
                System.out.print(e + " ");
    }
}
