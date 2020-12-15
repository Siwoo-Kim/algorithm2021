package com.siwoo.algo.sedgewick.collection;

/**
 * [algo] [Digraph 에서 순환 탐지]
 *  방향 그래프에서 순환 경로 탐지.
 *  
 *  dfs 의 스택은 현재 탐색중인 경로가 유지된다.
 *  dfs(v) 에서 v->w 의 w 정점이 스택에 있다면 순환이 존재한다.
 *  왜냐면, 스택 자체가 현재 탐색중인 경로이므로 w->v 로 경로가 존재하며
 *  이번 dfs 을 통해 v->w 로 순환이 완성되므로.
 *  
 * problem
 *  * 방향 그래프에서 순환 경로가 존재하는지 알고 싶다. (DAG?)
 *
 * components
 *  1. dfs
 *  2. 스택
 *
 *  algorithm.
 *      1. dfs 로 정점 v 을 스택에 넣는다.
 *      2. v 의 인접 정점 w 가 스택에 있다면 순환 탐지.
 *      3. dfs 가 끝나면 정점 v 을 스택에서 꺼낸다.
 *
 *  limitation
 *      1.
 *
 *  time complexity
 *      * 
 */
public class DirectedGraphCycle<E> implements Cycle<E> {
    private Stack<E> cycle;
    
    public DirectedGraphCycle(Graph<E> G) {
        Set<E> visit = new Set<>();
        Stack<E> onStack = new LinkedList<>();
        for (E v: G.vertexes())
            if (!visit.contains(v))
                if (dfs(v, visit, onStack, G))
                    break;
    }

    private boolean dfs(E v, Set<E> visit, Stack<E> onStack, Graph<E> G) {
        visit.add(v);
        onStack.push(v);
        for (E w: G.adj(v)) {
            if (!visit.contains(w)) {
                if (dfs(w, visit, onStack, G))
                    return true;
            } else if (onStack.contains(w)) {   //has path w-v
                cycle = new LinkedList<>();
                cycle.push(w);
                while (!onStack.peek().equals(w))
                    cycle.push(onStack.pop());
                cycle.push(w);  //w -> w path
                return true;
            }
        }
        onStack.pop();
        return false;
    }

    @Override
    public boolean hasCycle() {
        return cycle != null;
    }

    @Override
    public Iterable<E> cycle() {
        return cycle;
    }
}
