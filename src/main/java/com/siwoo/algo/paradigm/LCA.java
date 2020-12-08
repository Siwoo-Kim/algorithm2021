package com.siwoo.algo.paradigm;

import com.siwoo.algo.sedgewick.collection.Edge;
import com.siwoo.algo.util.AppConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * LCA lowest common ancestor
 *  트리 t 에서 정점 v, w 의 가장 가까운 조상 u 은?
 *  
 *  segment 을 이용한 LCA 문제해결.
 *      
 *      lca for segment tree 컴포넌트.
 *          1. dfs 을 통한 모든 정점 v 의 방문 순서 i 와 depth - 이를 postOrder 라고 정의하자.
 *          2. postOrder 의 모든 부분 구간 범위를 segment tree 로 구간을 추상화.
 *          3. 정점 v 을 가장 방문한 순서 i 을 기록. 이를 index[v] = i 라 하자.
 *          4. postOrder 의 i 을 정점 v 로 맵핑.
 *      
 *          정의: 정점 v 와 정점 w 의 lca 은 
 *              index[v], index[w] 사이의 postOrder 중 depth 가 가장 낮은 노드이다.
 *              
 *              => v 와 w 사이의 공통 조상은 u postOrder 의 특성상 부모는 반드시 방문된다.
 *              => 반대로 부모 u 의 조상이면서 v 와 w 의 조상은 절대 v 와 w 사이에 존재할 수 없다.
 *                  postOrder 에서 모든 자식을 돌면서 index++ 을 해주었으므로,
 *                  u 의 dfs 가 끝난 시점에 v, w < u < u.parent
 *              
 *      알고리즘의 특성.
 *          1. postOrder 의 길이는 한 간선을 두번씩 방문하므로 (루트를 제외한)
 *              (N-1)*2+1 = 2N-1
 */
public abstract class LCA<E> {

    /**
     * who is the lca for v and w?
     * @param v
     * @param w
     * @return
     */
    public abstract E query(E v, E w);
    
    private static class SegmentTreeLCA<E> extends LCA<E>{
        private Map<E, List<Edge<E>>> tree;
        private Map<Integer, Node> postOrder;
        private Map<E, Integer> indexing;
        private int[] segmentTree;
        private int index, N;

        private class Node {
            int depth;
            E e;
            public Node(E e, int depth) {
                this.e = e;
                this.depth = depth;
            }
        }
        
        public SegmentTreeLCA(E root, Map<E, List<Edge<E>>> tree) {
            this.tree = tree;
            postOrder = new HashMap<>();
            postOrder(root, root, 0);
            indexing = new HashMap<>();
            N = (tree.size()-1) * 2 + 1;
            for (int i=0; i<N; i++) {
                Node node = postOrder.get(i);
                if (!indexing.containsKey(node.e))
                    indexing.put(node.e, i);
            }
            int h = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
            segmentTree = new int[1<<h];
            build(1, 0, N-1);
        }

        @Override
        public E query(E v, E w) {
            int i = indexing.get(v),
                    j = indexing.get(w);
            if (i > j) {
                int t = i;
                i = j;
                j = t;
            }
            int q = query(1, 0, N-1, i, j);
            return q == -1? null: postOrder.get(segmentTree[q]).e;
        }

        private int query(int node, int left, int right, int from, int to) {
            if (left > to || right < from) return -1;
            if (left >= from && right <= to) return node;
            int mid = (left + right) / 2,
                    child = node << 1;
            int lca1 = query(child, left, mid, from, to),
                    lca2 = query(child+1, mid+1, right, from, to);
            if (lca1 == -1) return lca2;
            if (lca2 == -1) return lca1;
            int d1 = postOrder.get(segmentTree[lca1]).depth,
                    d2 = postOrder.get(segmentTree[lca2]).depth;
            return d1 < d2? lca1: lca2;
        }

        private void build(int node, int left, int right) {
            if (left == right) {
                segmentTree[node] = left;
                return;
            }
            int mid = (left + right) / 2;
            int child = node << 1;
            build(child, left, mid);
            build(child+1, mid+1, right);
            int d1 = postOrder.get(segmentTree[child]).depth,
                    d2 = postOrder.get(segmentTree[child+1]).depth;
            segmentTree[node] = d1 < d2? segmentTree[child]: segmentTree[child+1];
        }

        private void postOrder(E node, E parent, int depth) {
            postOrder.put(index++, new Node(node, depth));
            for (Edge<E> e: tree.get(node)) {
                if (e.w.equals(parent)) continue;    // undirected G
                postOrder(e.w, node, depth+1);
                postOrder.put(index++, new Node(node, depth));
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/lca.txt";
        Map<String, List<Edge<String>>> tree = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            int V = Integer.parseInt(scanner.nextLine()),
                    E = V - 1;
            for (int i=0; i<E; i++) {
                String[] vertexes = scanner.nextLine().split("\\s+");
                String v = vertexes[0],
                        w = vertexes[1];
                tree.putIfAbsent(v, new ArrayList<>());
                tree.putIfAbsent(w, new ArrayList<>());
                Edge<String> edge = new Edge<>(v, w);
                tree.get(v).add(edge);
                tree.get(w).add(edge.reverse());
            }
        }
        LCA<String> lca = new SegmentTreeLCA<>("A", tree);
        System.out.println(lca.query("G", "I"));
        System.out.println(lca.query("C", "D"));
        System.out.println(lca.query("D", "E"));
        System.out.println(lca.query("H", "I"));
        System.out.println(lca.query("E", "F"));
    }
}
