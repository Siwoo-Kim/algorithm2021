package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [graph]
 *  그래프는 정점 vertex 의 집합과 집합에 속하는 정정의 쌍을 연결하는 간선 edge 의 모음이다.
 *  
 *
 * problem
 *  * 요소 e 를 관리하고 요소간의 관계 connection 을 표현하는 자료구조를 구현하고 싶다.
 *
 * [algo] components
 *  1. vertex   정점
 *  2. edge 간선
 *  3. self-loop    자기 순환
 *      자기 자신으로 되돌아와 순환되는 순환 간선. v-v
 *  4. parallel-edge    다중 간선
 *      같은 쌍의 정점을 연결하는 복수의 간선. v-w more than one
 *  5. simple graph    단순 그래프
 *      self-loop 와 parallel-edge 가 없는 그래프.
 *  6. adjacent      인접
 *      두 정점이 간선으로 연결되어 있는 상태. v-w (adjacent)
 *  7. incident     부속
 *      정점 v 에 연결된 간선 edge 을 정점 v 에 부속이라 말한다.
 *  8. degree 차수
 *      정점 v 에 부속된 간선의 갯수.
 *  9. sub-graph 
 *      그래프 G 의 부분 집합.
 *  10. path 경로
 *      간선으로 연결된 정점의 나열.
 *      simple path - 순환이 없는 path (반복되는 정점이 없는)
 *  11. cycle 순환
 *      시작 정점과 종점 정점이 같은 edge 가 하나라도 존재하는 경로. 
 *      a-b-c-a (순환이면서 단순 순환)
 *      
 *  12. connected acyclic graph 연결된 비순환 그래프. = tree
 *      순환이 없는 연결된 그래프 G 
 *      G 의 간선의 수는 정점의 수 - 1
 *      
 *  13. spanning tree. 신장 트리.
 *      그래프 G 에 대해서 모든 정점을 포함하는 트리. (하나 이상)
 *      
 *      tree 트리의 조건.
 *          G 는 연결 그래프이다.
 *          E = V - 1
 *          간선을 하나 끊으면 두 개의 트리가 생성.
 *  
 *  14. bi-part-ite graph 이분 그래프
 *      모든 간선이 두 그릅으로 나눠진 정점의 집합 양쪽을 연결하는 그래프.
 *      
 *      eg) G(student & subject)
 * 
 * Graph 에 대한 문제와 알고리즘.
 *  1. v 에 연결된 정점들. - dfs, bfs, union-find
 *  2. v-w 의 경로. - dfs, bfs. (최단 경로 아님)
 *  3. 그래프의 연결 요소. - dfs, union-find
 *  4. 순환 탐지.
 *  5. 그래프 G 가 이분 그래프 bipartite 인가?
 */
public interface Graph<E> {

    /**
     * the number of vertexes 
     * 
     * @return
     */
    int sizeOfVertexes();

    /**
     * the number of edges
     * 
     * @return
     */
    int sizeOfEdges();

    /**
     * add given edge to the graph
     * 
     * @param edge
     */
    void addEdge(Edge<E> edge);

    /**
     * adjacent vertexes of given v
     * 
     * @param v
     * @return
     */
    Iterable<E> adj(E v);

    /**
     * all the vertexes belongs to the graph
     * 
     * @return
     */
    Iterable<E> vertexes();
    
    /**
     * degree of given vertex
     * 
     * @param v
     * @return
     */
    default int degree(E v) {
        checkNotNull(v);
        int degree = 0;
        for (E w: adj(v))
            degree++;
        return degree;
    }

    /**
     * max degree in the graph
     * 
     * @return
     */
    default int maxDegree() {
        int maxDegree = 0;
        for (E v: vertexes())
            maxDegree = Math.max(maxDegree, degree(v));
        return maxDegree;
    }

    /**
     * the number of v-v
     * 
     * @return
     */
    default int sizeOfSelfLoops() {
        int loops = 0;
        for (E v: vertexes())
            for (E w: adj(v))
                if (v.equals(w))
                    loops++;
        return loops / 2;   //count twice
    }
    
    default String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sizeOfVertexes()).append(" vertices, ")
                .append(sizeOfEdges()).append(" edges\n");
        for (E v: vertexes()) {
            sb.append(v).append(": ");
            for (E w: adj(v))
                sb.append(w).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }

    static void main(String[] args) {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyG.txt";
        In in = new In(path);
        int V = in.readInt(),
                E = in.readInt();
        Graph<Integer> G = new UnDirectedGraph<>();
        for (int i=0; i<E; i++) {
            Edge<Integer> edge = new Edge<>(in.readInt(), in.readInt());
            G.addEdge(edge);
        }
        System.out.println(G.asString());
    }
}
