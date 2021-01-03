package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 이분그래프.
 * 
 * 그래프의 모든 정점이 두 그룹으로 나눠지고, 
 * 서로 다른 그룹의 정점이 간선으로 연결된 그래프.
 * 
 */
public class Bipartite<E> {
    private boolean bipartite = true;
    private SymbolTable<E, COLOR> idMap = new HashTable<>();
    private SymbolTable<COLOR, LinkedList<E>> groups = new HashTable<>();
    
    private enum COLOR {
        RED, BLUE;
        
        COLOR reverse() {
            return this == RED? BLUE: RED;
        }
    }
    
    public Bipartite(Graph<E> G) {
        checkNotNull(G);
        for (COLOR c: COLOR.values())
            groups.put(c, new LinkedList<>());
        for (E v: G.vertexes()) {
            if (!idMap.contains(v))
                if (!dfs(v, COLOR.RED, G)) {
                    idMap = null;
                    groups = null;
                    break;
                }
        }
    }

    private boolean dfs(E v, COLOR c, Graph<E> G) {
        idMap.put(v, c);
        groups.get(c).add(v);
        for (Edge<E> e: G.edgeOf(v)) {
            E w = e.other(v);
            if (!idMap.contains(w)) {
                if (!dfs(w, c.reverse(), G))
                    return false;
            }
            else if (idMap.get(w) == c) {
                return bipartite = false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return bipartite;
    }

    public static void main(String[] args) {
        Graph<Integer> G = new UnDirectedGraph<>();
        G.addEdge(new Edge<>(1, 4));
        G.addEdge(new Edge<>(1, 6));
        G.addEdge(new Edge<>(2, 4));
        G.addEdge(new Edge<>(2, 3));
        G.addEdge(new Edge<>(5, 6));
        Bipartite<Integer> bipartite = new Bipartite<>(G);
        System.out.println(bipartite.bipartite);
        System.out.println(bipartite.groups.toString());
        G.addEdge(new Edge<>(1, 2));
        bipartite = new Bipartite<>(G);
        System.out.println(bipartite.bipartite);
    }
}
