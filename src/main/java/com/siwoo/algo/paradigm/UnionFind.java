package com.siwoo.algo.paradigm;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * 유니온 파인드 union find
 *  동적 연결성 문제 (dynamic connectivity problem) 을 위한 알고리즘.
 *      
 *      동적 연결성?
 *          복수의 정점의 연결 관계를 유지하면서, 새로운 정점 쌍의 연결 여부를 판단.
 *          
 *          정점 p 와 q 가 연결되었는가? (동치 관계인가?)
 *              반사율, 대칭율,   전이율
 *              p-p    p-q, q-p   p-q, q-z, p-z
 *  
 *  컴포넌트 component
 *      하나의 연결 집합 (set).
 *
 *  union find 중요 특성. 
 *      1. component 을 하나의 tree 로 표현.
 *      2. 모든 요소는 root 이거나 root 의 자식이다.
 *      3. union p and q 은 p 의 루트를 q 의 루트와 연결.
 *
 *  트리 tree 의 정의.
 *      tree 의 size 는 정점의 갯수이다.
 *      tree 에서 어떤 정점 v 의 depth 은 root 에서 v 까지의 edge 의 갯수이다.
 *          (root 의 depth 은 0 으로 정의하자.)
 *      tree 의 height 은 모든 정점 v 의 max(depth(v)) 이다.          
 *  
 *  union find 개선하기.
 *      문제점.
 *          tree 의 최대 높이는 N-1 이므로 최악의 경우 find(v) 은 N-1.
 *          
 *      해결 방안.
 *          가중 퀵-유니온. weighted quick union-find
 *              각 트리의 크기를 기록해두면서, 작은 트리 lt 를 큰 트리 gt 에 연결한다.
 *              => gt 를 lt 에 연결하면 높이는 항상 1 증가한다.
 *              => lt 을 gt 에 연결하면 높이는 증가하지 않는다. height(lt) != height(gt) 
 *          
 *          전체 정점의 갯수가 N 이라 할때, 임의의 정점의 depth 은 logN 을 넘지 않는다.
 *          
 *          경로 압축.
 *              find(v) 에서 v 의 root 을 찾았다는 정보를 버리지 않고 v 의 링크를 루트로 연결.
 *     
 *  union-find 알고리즘 분석.
 *      분할 상쇄 비용.
 *          단위 작업당 평균 비용.
 *          배열 a 의 접근이 기본 작업 단위라면
 *              
 *          
 */
public interface UnionFind<E> {

    /**
     * if p not exists, put p into union-find
     * 
     * @param p
     */
    void put(E p);
    
    /**
     * union p and q
     * 
     * @param p
     * @param q
     */
    void union(E p, E q);

    /**
     * who is the root of p?
     * 
     * @param p
     * @return
     */
    E find(E p);

    /**
     * are p and q are connected?
     * 
     * @param p
     * @param q
     * @return
     */
    boolean connected(E p, E q);

    /**
     * number of components.
     * 
     * @return
     */
    int count();

    static void main(String[] args) throws FileNotFoundException {
        //tinyUF = 2, mediumUF = 66
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/largeUF.txt";
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            int N = scanner.nextInt();
            UnionFind<Integer> uf = new ZippedUnionFind<>();
            for (int i=0; i<N; i++)
                uf.put(i);
            for (int i=0; i<N; i++) {
                int p = scanner.nextInt(),
                        q = scanner.nextInt();
                if (uf.connected(p, q)) continue;
                uf.union(p, q);
//                System.out.println(p + " " + q);
            }
            System.out.println(uf.count());
        }
        System.out.println("== performance testing ==");
        int components = 1000000000;
        BiConsumer<Integer, UnionFind<Integer>> test = (N, uf) -> {
            for (int i=0; i<N; i++) {
                int v = StdRandom.uniform(components),
                        w = StdRandom.uniform(components);
                uf.put(v);
                uf.put(w);
                uf.union(v, w);
            }
        };
        for (int i=250; i<=10000000; i+=i) {
            UnionFind<Integer> uf = new ZippedUnionFind<>();
            Stopwatch timer = new Stopwatch();
            test.accept(i, uf);
            System.out.println(uf.count());
            System.out.printf("9%d %.5f\n", i, timer.elapsedTime());
            System.out.println("==============================");
        }
    }
}
