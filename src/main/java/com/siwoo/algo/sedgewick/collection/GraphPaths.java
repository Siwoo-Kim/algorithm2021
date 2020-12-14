package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

/**
 * all the paths from {@link #source()} in the graph.
 * 
 * @param <E>
 */
public interface GraphPaths<E> {

    /**
     * start vertex.
     * 
     * @return
     */
    E source();

    /**
     * is there any path for s-v?
     * 
     * @param v
     * @return
     */
    boolean hasPathTo(E v);

    /**
     * path s-v
     * 
     * @param v
     * @return
     */
    Iterable<E> pathTo(E v);

    public static void main(String[] args) {
        final String file = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyCG.txt";
        In in = new In(file);
        int V = in.readInt(),
                E = in.readInt();
        Graph<Integer> G = new UnDirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
        }
        int source = 0;
        GraphPaths<Integer> paths = new BFSGraphPaths<>(G, source);
        for (int v: G.vertexes()) {
            if (v == source) continue;
            if (paths.hasPathTo(v)) {
                System.out.print(source + " to " + v + ": ");
                for (int w : paths.pathTo(v))
                    System.out.print(w + " ");
            }
            System.out.println();
        }
    }
    
}
