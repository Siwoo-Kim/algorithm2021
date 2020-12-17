package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [critical path]
 *  크리티컬 경로 알고리즘은 작업과 비용 작업간의 선행 관계가 주어질 때, 스케줄링 문제를 해결한다.
 *  
 * problem
 *  * 복수의 작업과 비용, 그리고 작업간의 선행 관계가 주어질 때,
 *  병렬로 실행하여 모든 작업을 끝낼 수 있는 "가장 짧은 시간" 과 스케줄링 순서를 알고 싶다.
 *
 * components
 *  1. 작업을 정점으로 표현. 
 *      작업 j 에 대해서 v1-v2 그리고 간선의 비용은 작업을 마치는 데 필요한 시간.
 *  2. 가짜 원점과 종점
 *      모든 작업의 시작 종점 s 와 마지막 종점 t.
 *      크리티컬 경로을 실행한 이후 distTo[t] 가 병렬 작업의 최소 실행 시간.
 *
 *  algorithm.
 *      1. 원점을 s, 종점을 t 로 하는 DAG 을 만든다.
 *      2. 각 작업을 시작점과 끝점 두 개의 정점으로 표현하고 해당 작업의 소요 시간을 가중치로 하는 간선을 만든다.
 *      3. 작업 i, j 간의 선행 조건을 표현하기 위해 무 가중치의 간선 i->j 을 만든다.
 *  
 *  DAG 의 모든 경로는 어떤 작업들의 시작과 끝에 대한 시퀀스다.
 *  개발 작업은 무 가중치 간선 (i, j) 으로 구분된다.
 *      i -> i1 ->  j -> j2
 *       (무가중치 간선)
 *  원점 s 에서 i 의 작업은 작업 시간의 하한이다.
 *  원점 s 에서 t 의 작업은 전체 작업 시간의 하한이다.
 *  
 *  time complexity
 *      * 
 */
public class CriticalPath<E> {
    private SymbolTable<E, Double> distTo = new HashTable<>();
    private SymbolTable<E, Diedge<E>> edgeTo = new HashTable<>();

    public CriticalPath(Digraph<E> G, E source) {
        checkNotNull(G, source);
        for (E v: G.vertexes())
            distTo.put(v, Double.NEGATIVE_INFINITY);
        distTo.put(source, 0.);
        Iterable<E> top = topologicalOrder(G);
        for (E v: top)
            relax(v, G);
    }

    private void relax(E v, Digraph<E> G) {
        for (Diedge<E> e: G.edgeOf(v)) {
            WeightedDiedge<E> we = (WeightedDiedge<E>) e;
            if (distTo.get(we.to()) < distTo.get(v) + we.weight) {
                distTo.put(we.to(), distTo.get(v) + we.weight);
                edgeTo.put(we.to(), we);
            }
        }
    }

    private Iterable<E> topologicalOrder(Digraph<E> G) {
        Set<E> visit = new Set<>();
        Stack<E> stack = new LinkedList<>();
        for (E v: G.vertexes())
            if (!visit.contains(v))
                dfs(v, visit, stack, G);
        return stack;
    }

    private void dfs(E v, Set<E> visit, Stack<E> stack, Digraph<E> G) {
        visit.add(v);
        for (E w: G.adj(v))
            if (!visit.contains(w))
                dfs(w, visit, stack, G);
        stack.push(v);
    }

    private double cost(E v) {
        checkNotNull(v);
        return distTo.get(v);
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/jobsPC.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        int N = scanner.nextInt();
        Digraph<Integer> G = new DirectedGraph<>();
        int s = Integer.MIN_VALUE, t = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            double duration = scanner.nextDouble();
            G.addEdge(new WeightedDiedge<>(i, -i, duration));
            G.addEdge(new WeightedDiedge<>(s, i, 0.));
            G.addEdge(new WeightedDiedge<>(-i, t, 0.));
            int J = scanner.nextInt();
            for (int j=0; j<J; j++)
                G.addEdge(new WeightedDiedge<>(-i, scanner.nextInt(), 0.));
        }
        CriticalPath<Integer> cp = new CriticalPath<>(G, s);
        for (int i=0; i<N; i++) 
            System.out.printf("%d takes %5.2f%n", i, cp.cost(i));
        System.out.printf("scheduling takes %5.2f%n", cp.cost(t));
    }
}
