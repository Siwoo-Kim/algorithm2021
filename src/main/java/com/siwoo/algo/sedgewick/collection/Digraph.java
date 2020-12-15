package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * [algo] [directed graph]
 *  방향 그래프(digraph) 은 정점과 방향을 가진 간선의 집합.
 *   v-w != w-v 
 *   
 * problem
 *  * 한 방향으로 연결된 그래프를 구현하고 싶다.
 *
 * components 
 *  대부분의 구성요소는 {@link Graph} 와 유사하다.
 *  1. out-degree 출력 차수.
 *      나가는 방향으로 연결된 간선의 갯수.
 *  2. in-degree 입력 차수
 *      들오는 방향으로 연결된 간선의 갯수.
 *  3. 경로.
 *      간선의 방향을 따라 순서대로 이어지는 정점들의 나열.
 *  4. 순환.
 *       경로 중 시작 정점과 끝 정점이 같은 경로.
 *  5. 단순 순환.
 *      중복되는 간선이나 정점이 없는 순환 경로. (단, 시작과 끝 정점 제외)
 *  6. 도달성.
 *      어떤 정점 v 에서 w 에 방향 경로가 존재하면 "v-w 은 도달 가능하다" 한다.
 *      "v-w 도달 가능하다" 해서 "w-v 도달 가능하다" 은 참이 아니다.
 *  7. DAG (비순환 방향 그래프)
 *      스케줄링, 작업들의 선행 관계 문제  
 *      방향 그래프 G 의 위상 정렬을 위한 "선행 조건"
 *      
 *  8. 위상 정렬. Topological order
 *      어떤 비순향 방향 그래프 DAG  주어졌을 때, 모든 정점들을 간선이 가리키는 방향으로만 나열.
 *      중요 속성. 가장 뒤에 있는 정점의 in-degree 은 0이여야 한다.
 *      앞에 있는 정점 v 은 뒤에 있는 정점 w 을 가리키는 간선 v->w 이 없어야 한다.가
 *      
 *      v->w 라면 w 을 수행하기 위해 v 은 선 작업이라는 가정하에.
 *      
 *      위상 정렬을 위해선 순환이 존재하면 안된다.
 *          v->w, w->z, z->v 라면 작업을 스케줄링을 할 수 없다.
 *          v 을 하기 위해선 z 가 선행 작업이고, z 을 하귀 위해선 v 가 선행 작업이므로 모순.
 *          
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
public interface Digraph<E> extends Graph<E> {

    /**
     * add v->w edge into the graph.
     * 
     * @param edge
     */
    void addEdge(DiEdge<E> edge);
    
    /**
     * add v->w edge into the graph.
     *
     * @param edge
     */
    @Override
    default void addEdge(Edge<E> edge) {
        addEdge(new DiEdge<>(edge.v, edge.w));
    }

    /**
     * reverse this digraph.
     * 
     * @return
     */
    Digraph<E> reverse();


    static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyDG.txt";
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path)));
        final int V = scanner.nextInt(), 
                E = scanner.nextInt();
        Digraph<Integer> G = new DirectedGraph<>();
        for (int i=0; i<E; i++) {
            DiEdge<Integer> edge = new DiEdge<>(scanner.nextInt(), scanner.nextInt());
            G.addEdge(edge);
        }
        System.out.println(G.toString());
        
        GraphSearch<Integer> dfs = new DFSGraphSearch<>(G, 0);
        for (int v: G.vertexes())
            if (dfs.connected(v))
                System.out.print(v + " ");
    }
}
























