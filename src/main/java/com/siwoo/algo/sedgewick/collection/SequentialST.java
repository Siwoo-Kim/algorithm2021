package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.VisualAccumulator;

/**
 * linkedlist 의 symbol table 은 키를 삽입 혹은 탐색은 N 번의 비교 연산이 소요.
 * 
 * @param <K>
 * @param <V>
 */
public class SequentialST<K, V> implements SymbolTable<K, V> {
    
    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Node root;
    private int N;
    private int cnt;
    private int i;
    private boolean check = true;
    private VisualAccumulator visualAccumulator = new VisualAccumulator(2000, 10000);
    
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
        N++;
        if (check)
            visualize(cnt);
        cnt=0;
    }

    private Node put(K key, V value, Node root) {
        cnt++;
        if (root == null) return new Node(key, value);
        if (root.key.equals(key)) {
            root.value = value;
            return root;
        }
        root.next = put(key, value, root.next);
        return root;
    }

    @Override
    public V get(K key) {
        V v = get(key, root);
        if (check)
            visualize(cnt);
        cnt = 0;
        return v;
    }

    private void visualize(int y) {
        visualAccumulator.addDataValue(y);
    }

    private V get(K key, Node root) {
        cnt++;
        if (root == null) return null;
        if (root.key.equals(key))
            return root.value;
        else
            return get(key, root.next);
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new LinkedList<>();
        for (Node node=root; node!=null; node=node.next)
            q.enqueue(node.key);
        return q;
    }

    @Override
    public Iterable<V> values() {
        Queue<V> q = new LinkedList<>();
        for (Node node=root; node!=null; node=node.next)
            q.enqueue(node.value);
        return q; 
    }
}
