package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/**
 * [algo] [mst]
 *  spanning tree 은 순환 경로가 없으면서 모든 정점이 연결된 서브그래프이다. (tree = E = V-1 & all v is connected) 
 *  minimum spanning tree 은 이러한 그래프의 spanning tree 중 가중치의 합이 최소인 spanning tree 이다.
 *  
 * problem
 *  * (무방향) 가중 그래프 G 에서 모든 정점을 포함하는 최소 비용을 알고 싶다. 
 *
 * mst 문제해결 알고리즘.
 *  1. prim     (priority queue)
 *  2. kruskal  (union find)
 * 
 * component
 *  트리의 속성. tree property
 *      1. 트리에 간선을 하나 추가하면 "한 개의 순환" 이 만들어진다  
 *      2. 트리의 간선을 하나 제거하면 "두 개의 트리" 로 분리된다.
 *      
 *  자르기 속성. cut property
 *      정점들을 두 개의 집합으로 나누어 둘 사이를 횡단하는 간선을 찾는다.
 *      
 *  횡단 간선. crossing edge.
 *      그래프의 자르기로 나누어진 두 집합을 잇는 간선들.
 *  
 *   가중 간선 그래프 G 에서, 어떤 임의의 자르기에서 최소 가중치를 가지는 횡단 간선은 그래프의 MST 에 반드시 포함된다.
 *
 */

/**
 * A spanning tree of G is a subgraph T that is both a tree and spanning.
 * MST is a sa min weight spanning tree.
 * @param <E>
 */
public interface MinimumSpanningTree<E> extends Graph<E> {

    /**
     * the mst's weight
     * 
     * @return
     */
    double weight();
    
    default boolean isConnected(Graph<E> G) {
        ConnectedComponent<E> cc = new CC<>(G);
        return cc.count() == 1;
    }

    static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/mediumEWG.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        Graph<Integer> G = new UnDirectedGraph<>();
        int V = scanner.nextInt(),
                W = scanner.nextInt();
        for (int i=0; i<W; i++) {
            int v = scanner.nextInt(),
                    w = scanner.nextInt() ;
            double weight = scanner.nextDouble();
            WeightedEdge<Integer> edge = new WeightedEdge<>(v, w, weight);
            G.addEdge(edge);
        }
        MinimumSpanningTree<Integer> mst = new Kruskal<>(G);
        System.out.printf("%.2f%n", mst.weight());

        for (int i=0; i<100; i++)
            System.out.println((new Random().nextInt(90000000) + 10000000));
    }
}
