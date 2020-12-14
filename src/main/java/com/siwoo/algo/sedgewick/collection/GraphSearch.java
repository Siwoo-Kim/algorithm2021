package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

/**
 * 원점 s 에 연결된 정점들 찾기.
 * 
 */
public interface GraphSearch<E> {

    E source();
    
    /**
     * does source connected with v?
     * 
     * @param 
     * @return
     */
    boolean connected(E v);

    /**
     * the number of vertices that can be reachable.
     * 
     * @return
     */
    int count();

    static void main(String[] args) {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyG.txt";
        In in = new In(path);
        Graph<Integer> G = new UnDirectedGraph<>();
        int V = in.readInt(),
                E = in.readInt();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
        }
        
        int s = 0;
        GraphSearch<Integer> search = new DFSGraphSearch<>(G, s);
        System.out.print(s + ": ");
        for (int v: G.vertexes())
            if (v != s && search.connected(v))
                System.out.print(v + " ");
        System.out.println();
        if (search.count() != G.sizeOfVertexes())
            System.out.print("NOT ");
        System.out.println("connected graph.");
    }
}
