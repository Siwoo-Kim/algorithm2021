package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [bfs]
 *
 * problem
 *  * 가중치가 없는 그래프에서 최단 경로를 구하고 싶다.
 *  
 *  bfs 은 정점 v 에 연결된 정점 w 에 대한 최단 경로를 구한다.
 *  => 큐에는 반드시 간선 수가 적은 순대로 정점들이 enqueue 되고 dequeue 된다.
 *     다시말해, 큐에는 원점 v 에서 k 만큼 떨어진 정점 뒤에, 원점에서 k+1 만큼 떨어진 정점이 뒤따른다.
 *     k 만큼 떨어진 원점들이 처리되지 않은 한 k+2 의 정점들은 큐에 들어올 수 없다. (중요한 속성)
 *  
 * components
 *  1. Queue
 *  2. 방문한 정점에 대한 visit
 *
 *  algorithm.
 *      1. v 을 큐에서 꺼낸다.
 *      2. v 의 인접 정점 w 에 대해서 방문하지 않았으면 방문했다 표시하고 큐에 넣는다. 
 *
 *  limitation
 *      1. 가중치가 1 혹은 없는 그래프에서만의 최단거리.
 *
 *  time complexity
 *      * V+E
 */
public class BFSGraphPaths<E> implements GraphPaths<E> {
    private final E source;
    private final SymbolTable<E, Integer> distances = new HashTable<>();
    private final SymbolTable<E, E> parents = new HashTable<>();

    public BFSGraphPaths(Graph<E> G, E source) {
        checkNotNull(G, source);
        this.source = source;
        Queue<E> q = new LinkedList<>();
        q.enqueue(source);
        distances.put(source, 0);
        parents.put(source, source);
        while (!q.isEmpty()) {
            E v = q.dequeue();
            for (E w: G.adj(v)) {
                if (!distances.contains(w)) {
                    distances.put(w, distances.get(v) + 1);
                    parents.put(w, v);
                    q.enqueue(w);
                }
            }
        }
    }

    @Override
    public E source() {
        return source;
    }

    @Override
    public boolean hasPathTo(E v) {
        checkNotNull(v);
        return distances.contains(v);
    }

    @Override
    public Iterable<E> pathTo(E v) {
        if (!hasPathTo(v)) return new LinkedList<>();
        Stack<E> stack = new LinkedList<>();
        while (!v.equals(source)) {
            stack.push(v);
            v = parents.get(v);
        }
        stack.push(source);
        return stack;
    }
}
