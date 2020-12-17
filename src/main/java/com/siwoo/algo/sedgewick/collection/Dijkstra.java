package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [dijkstra]
 *  다익스트라는 "음수 가중치가 없는" 가중 간선 방향 그래프 G 에서 단일 원점 최단 경로 문제를 해결한다.
 *  
 *      Prim (점진적 mst 키우기) 과 간선 이완 (relaxation) 을 착용해 spt 을 구하는 알고리즘.
 *      
 * problem
 *  * 주어진 가중 방향 그래프 G 에서 SPT (Shortest path tree) 을 구하고 싶다.
 *  
 * components
 *  1. s-v 의 경로와 정점을 위한 우선순위 큐.
 *      => 우선순위 큐에 가장 작은 값의 경로의 가중치는 이미 spt 에 속한 어떤 정점 v 보단 크고, 아직 이완되지 않은 정점 w 보단 작다.
 *      (음수가 없을시에)
 *      => 항상 경로순으로 작은 값을 우선순위에서 꺼내왔고, 그 경로의 w 에 대해서 최소값을 v + path.weight  업데이트 해왔으므로
 *      => 이 이유 때문에 visit 은 필요없다. spt 의 어떤 정점 v 의 최단 거리는 이완하려는 정점 w 을 통한 거리보다 항상 적으므로.
 *      
 *  2. v-w 의 간선을 위한 edgeTo[w] = v.
 *      => s-w 의 마지막 간선.
 *  3. 현재 까지의 최단 경로 가중치를 위한 distTo[w]
 *
 *  algorithm.
 *      1. 모든 정점의 거리는 {@link  Double#POSITIVE_INFINITY} 로 초기화.
 *      2. 원점의 거리는 0 으로 초기화.
 *      3. 원점과 경로 거리 (path) 을 큐에 삽입.
 *      4-1 큐에서 path 을 꺼내 path.v 을 spt 에 추가.
 *      4-2 path.v 에 부속된 모든 간선에 대한 이완작업.
 *      5. 큐가 빌때까지 진행.
 *
 *  limitation
 *      음수 가중치 그래프에선 사용할 수 없다. - bellman ford 을 사용.
 *      
 *  time complexity
 *      ElogV (모든 간선을 확인하며, 우선 순위 큐에 정점은 모두 한번씩 들어가므로)
 */
public class Dijkstra<E> implements ShortestPathTree<E> {
    private SymbolTable<E, Diedge<E>> edgeTo = new HashTable<>(); // (w, v->w) 
    private SymbolTable<E, Double> distTo = new HashTable<>();  // (s-v distance)
    private final E source;

    private class Path implements Comparable<Path> {
        private E v;
        private double distance;

        public Path(E v, double distance) {
            this.v = v;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path that) {
            return Double.compare(distance, that.distance);
        }

        @Override
        public String toString() {
            return String.format("(%s: %4.2f)", v, distance);
        }
    }
    
    public Dijkstra(Digraph<E> G, E source) {
        checkNotNull(G, source);
        this.source = source;
        for (E v: G.vertexes())
            distTo.put(v, Double.POSITIVE_INFINITY);
        PriorityQueue<Path> pq = new PriorityQueue<>((e1, e2) -> e2.compareTo(e1));
        distTo.put(source, 0.);
        edgeTo.put(source, null);
        Set<E> visit = new Set<>();
        pq.enqueue(new Path(source, distTo.get(source)));
        while (!pq.isEmpty()) {
            Path path = pq.dequeue();
//            if (visit.contains(path.v)) continue;
            relax(path.v, pq, visit, G);
        }
    }

    private void relax(E v, PriorityQueue<Path> pq, Set<E> visit, Digraph<E> G) {
        visit.add(v);
        for (Diedge<E> e: G.edgeOf(v)) {
            WeightedDiedge<E> we = (WeightedDiedge<E>) e;
            E w = e.to();
//            if (visit.contains(we.other(v))) 
//                continue;
            if (distTo.get(w) > distTo.get(v) + we.weight) {
                distTo.put(w, distTo.get(v) + we.weight);
                edgeTo.put(w, we);
                pq.enqueue(new Path(w, distTo.get(w)));
            }
        }
    }

    @Override
    public E source() {
        return source;
    }

    /**
     * s-v's distance.
     * if v is not reachable, return {@link Double#POSITIVE_INFINITY}
     * 
     * @param v
     * @return
     */
    @Override
    public double distanceTo(E v) {
        checkNotNull(v);
        return distTo.get(v);
    }

    /**
     * s-v path.
     * 
     * @param v
     * @return
     */
    @Override
    public Iterable<Diedge<E>> edgeTo(E v) {
        checkNotNull(v);
        return pathTo(v, new LinkedList<>());
    }

    private Iterable<Diedge<E>> pathTo(E v, LinkedList<Diedge<E>> edges) {
        if (v.equals(source())) return edges;
        edges.pushFirst(edgeTo.get(v));
        pathTo(edgeTo.get(v).other(v), edges);
        return edges;
    }

    @Override
    public boolean hasPathTo(E v) {
        return distanceTo(v) < Double.POSITIVE_INFINITY;
    }
}
