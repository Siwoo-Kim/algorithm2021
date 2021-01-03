package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [algo] [dfs]
 *  dfs 에는 pre, post order 존재한다.
 * 
 * problem
 *  * dfs 의 방문 순서의 고찰.
 *
 * components
 *  1. pre-order
 *      정점 dfs(v) 와 인접 정점 w 에 대해서,
 *      w 을 방문 전 v 에 대한 연산을 적용한다. (혹은 큐에 넣는다)
 *      
 *      w 의 값이 v 에 의존적일때.
 *      
 *  2. post-order
 *      정점 dfs(v) 와 인접 정점 w 에 대해서,
 *      w 을 방문 후 v 에 대한 연산을 적용한다. (혹은 큐에 넣는다)
 *      
 *      aggregation 연산시.
 *      
 *  3. reversed post-order (topological order)
 *      
 *      위상 정렬시.
 *      정점들을 작업이라 가정한다 앞에 있는 어떤 정점 v 은 뒤에 있는 정점 w 보다 선행으로 실행되어야 한다.
 *      
 *  algorithm.
 *      1. dfs
 *      2. queue 혹은 stack
 *  
 *  무방향 그래프에서의 최장거리 구하기.
 *      dfs(v, depth) 시작시 방문했다고 체크.
 *      v 의 모든 인접 정점 w 에 대해서
 *          dfs(w, depth+1) 을 수행.
 *      dfs(v, depth) 방문 종료시 방문했음을 언체크.
 *      
 */
public class DFS<E> {
    private Queue<E> preOrder = new ArrayQueue<>();
    private Queue<E> postOrder = new ArrayQueue<>();
    private Stack<E> reversedPostOrder = new ArrayStack<>();

    public DFS(Digraph<E> G) {
        Set<E> visit = new Set<>();
        for (E v: G.vertexes())
            if (!visit.contains(v))
                dfs(v, visit, G);
    }

    private void dfs(E v, Set<E> visit, Digraph<E> G) {
        visit.add(v);
        preOrder.enqueue(v);
        for (E w: G.adj(v)) {
            if (!visit.contains(w))
                dfs(w, visit, G);
        }
        postOrder.enqueue(v);
        reversedPostOrder.push(v);
    }

    public Queue<E> preOrder() {
        return preOrder;
    }

    public Queue<E> postOrder() {
        return postOrder;
    }

    public Stack<E> reversedPostOrder() {
        return reversedPostOrder;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyDAG.txt";
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path)));
        int V = scanner.nextInt(),
                E = scanner.nextInt();
        Digraph<Integer> G = new DirectedGraph<>();
        for (int i=0; i<E; i++) {
            Diedge<Integer> edge = new Diedge<>(scanner.nextInt(), scanner.nextInt());
            G.addEdge(edge);
        }
        DFS<Integer> dfs = new DFS<>(G);
        for (Iterable<Integer> itr: Arrays.asList(dfs.preOrder, dfs.postOrder, dfs.reversedPostOrder)) {
            for (int e: itr)
                System.out.print(e + " ");
            System.out.println();
        }
    }
}
