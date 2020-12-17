package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * [algo] [spt]
 *  가중 간선 그래프 G 와 원점 s 가 주어져 있을때,
 *  s 에 대한 최단 경로 트리는 s 와 s 에서 도달 가능한 모든 정점 w 을 포함하는 서브그래프 SG.
 *  이 SG 은 s 을 root 로 하는 tree 가 된다.
 *  
 * problem
 *  * 가중 간선 방향 그래프 G 와 원점 s 가 주어졌을 때, s 에서 t 의 최단 경로를 찾고 싶다.
 *  
 * components
 *  1. 최단 경로
 *      어떤 경로의 가중치는 그 경로에 속하는 모든 간선의 가중치의 합이다.
 *      
 *  2. 가중치 음수 값 허용 여부에 따라 알고리즘이 달라진다.
 *  
 *  3. 최단 경로의 속성.
 *      s-t 에서 도달 가능하지 않다면, 최단 경로도 존재하지 않는다.
 *      최단 경로는 단순하다. (순환은 절대 최단 경로가 될 수 없으며, 가중치가 0 인 순환은 무시한다)
 *      최단 경로는 여러 개일 수도 있다.
 *  
 *  4. 원점과 도달 가능하지 않은 정점.
 *      원점과의 최단 경로는 당연히 0이다. 도달 가능하지 않은 정점은 {@link Double#POSITIVE_INFINITY}
 *   
 *  5. 간선 이완하기 (relaxation)
 *      각 간선을 v->w 만날 때마다 distTo[w] 에 최단 경로의 거리를 업데이트 한다.
 *      간선 이완 작업은 어떤 간선 v->w 에 대해 distTo[w] 보다 v + edge.weight 을 비교하여 
 *      v->w 가 비용이 더 적다면 distTo[w] 을 업데이트 해준다. 
 *      
 *  algorithm.
 *      1. dijkstra 
 *          단일 원점 v 에 대한 음수 가중치를 허용치 않는 그래프 G 에서의 spt.
 *      2. bellman-ford
 *          단일 원점 v 에 대한 음수 가중치를 허용한 그래프 G 에서의 spt.
 *      3. floyd
 *          모든 정점 v 에 대한 음수 가중치를 허용한 그래프 G 에서의 spt 집합.
 *
 */
public interface ShortestPathTree<E> extends GraphPaths<E> {

    /**
     * s-v's distance. 
     * if there is no distance returns {@link Double#POSITIVE_INFINITY}
     * 
     * @param v
     * @return
     */
    double distanceTo(E v);

    /**
     * shortest path from s to v
     * 
     * @param v
     * @return
     */
    Iterable<Diedge<E>> edgeTo(E v);

    @Override
    default Iterable<E> pathTo(E v) {
        Queue<E> paths = new LinkedList<>();
        paths.enqueue(v);
        for (Diedge<E> e: edgeTo(v)) {
            paths.enqueue(e.other(v));
            v = e.other(v);
        }
        return paths;
    }

    static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyEWDAG.txt";
        Digraph<Integer> G = new DirectedGraph<>();
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        int V = scanner.nextInt(),
                E = scanner.nextInt();
        for (int i=0; i<E; i++) {
            Diedge<Integer> edge = new WeightedDiedge<>(
                    scanner.nextInt(), 
                    scanner.nextInt(), 
                    scanner.nextDouble()); 
            G.addEdge(edge);
        }
        
        int source = 5;
        ShortestPathTree<Integer> spt = new Dijkstra<>(G, source);
        for (int v: G.vertexes()) {
            System.out.print(source + " to " + v);
            System.out.printf(" (%4.2f): ", spt.distanceTo(v));
            if (spt.hasPathTo(v))
                for (Diedge<Integer> e: spt.edgeTo( v))
                    System.out.print(e + "  ");
            System.out.println();
        }
    }
}
