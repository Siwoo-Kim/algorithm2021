package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.VisualAccumulator;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [hash table]
 *  배열을 이용해 키 & 값 쌍의 값을 저장하며, 이를 위해 키를 해싱하여 배열 인덱스로 변환하는 자료구조.
 * 
 * problem
 *  * 심볼 테이블을 배열 접근 O(1) 의 특성을 이용해 구현하고 싶다.
 *
 * [algo] components
 *  1. hash function
 *     키를 배열 인덱스로 변환한다. 
 *     키는 일관성이 있어야 한다. 같은 키라면 반드시 같은 해시값 가져야 한다.
 *     키를 [0, N-1] 로 변환해야 되며, 균등하게 분포되어야 한다.
 *     
 *     modular hashing.
 *          키 k 에 대해 배열 크기 N 의 나머지 연산을 사용. 0 <= hashing(k) <= N-1
 *          배열 크기 N 은 소수 (prime) 을 사용한다.
 *          소수는 해싱값을 균일하게 분포. 
 *          
 *          
 *  2. collision-resolution
 *      개별 체이닝 (separate chaining)
 *          배열을 buckets 로 사용하여 인덱스의 항목 각각에 연결리스트을 두어 복수의 요소를 관리.
 *          
 *      선형 탐지 (linear probing)
 *          개방형 주소 지정 (open-addressing).
 *              충돌이 발생하면 그 다음 빈 인덱스에 항목을 저장한다.
 *              이렇게 뭉쳐있는 형태를 clustering 이라 하며 길이가 짧을 수록 좋은 성능 효율을 낸다. 
 *          
 *  3. 시간 특성과 공간 특성의 tradeoff
 *      메모리와 실행 시간의 균형점을 찾아야 한다. 
 *      
 *  [algo] limitation
 *      1.
 *
 *  [algo] time complexity
 *      * separate chaining 해시 테이블에서 M 개의 buckets 과 N 개의 키가 사용될 때,
 *      탐색 실패와 삽입시 소요되는 비교 연산은 1~N/M
 */
public class HashTable<K, V> implements SymbolTable<K, V> {

    private static final int DEFAULT_CAPACITY = 17;
    private int N = 0, CAPACITY;
    private VisualAccumulator va = new VisualAccumulator(50, 34350);
    private LinkedList<Node<K, V>>[] buckets;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.CAPACITY = capacity;
        buckets = new LinkedList[capacity];
        for (int i=0; i<buckets.length; i++)
            buckets[i] = new LinkedList<>();
    }

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
    
    private int hash(K k) {
        return (k.hashCode() & 0x7fffffff) % CAPACITY;
    }

    @Override
    public void put(K key, V value) {
        //double buckets if average length >= 10
        // average length = N/CAPACITY
        checkNotNull(key);
        if (N/ CAPACITY >= 10)
            resize(CAPACITY << 1);
        buckets[hash(key)].put(new Node<>(key, value));
        N++;
    }

    private void resize(int newCapacity) {
        assert newCapacity > 0;
        HashTable<K, V> table = new HashTable<>(newCapacity);
        for (int i = 0; i< CAPACITY; i++)
            for (Node<K, V> node: buckets[i])
                table.put(node.key, node.value);
        buckets = table.buckets;
        CAPACITY = newCapacity;
    }

    @Override
    public V get(K key) {
        checkNotNull(key);
        Node<K, V> node = buckets[hash(key)].get(new Node<>(key, null));
        return node == null? null: node.value;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new LinkedList<>();
        return keys(0, q);
    }

    private Iterable<K> keys(int bucket, Queue<K> q) {
        if (bucket == CAPACITY) return q;
        for (Node<K, V> node: buckets[bucket])
            q.enqueue(node.key);
        return keys(bucket+1, q);
    }

    @Override
    public Iterable<V> values() {
        Queue<V> q = new LinkedList<>();
        return values(0, q);
    }

    private Iterable<V> values(int bucket, Queue<V> q) {
        if (bucket == N) return q;
        if (buckets[bucket] != null)
            for (Node<K, V> node: buckets[bucket])
                q.enqueue(node.value);
        return values(bucket+1, q);
    }

    @Override
    public void delete(K key) {
        checkNotNull(key);
        if (!contains(key)) return;
        buckets[hash(key)].delete(new Node<>(key, null));
        N--;
        
        // halve buckets if average length <= 2
        // average length = N/CAPACITY
        if (CAPACITY > DEFAULT_CAPACITY && N/CAPACITY <= 2)
            resize(CAPACITY >> 1);
    }

    public static void main(String[] args) {
        String[] s = "S E A R C H E X A M P L".split("\\s+");
        HashTable<String, Integer> hashTable = new HashTable<>();
        for (int i=0; i<s.length; i++)
            hashTable.put(s[i], i);
        for (String key: hashTable.keys())
            System.out.println(key + " " + hashTable.get(key));
    }
}
