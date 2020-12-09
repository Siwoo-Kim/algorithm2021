package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.checkElementIndex;


/**
 * rank 함수
 *  정렬된 배열 a 에 요소 e 가 존재하면 e 의 인덱스를 리턴.
 *  키가 존재하지 않다면 e 보다 작은 키의 갯수 리턴.
 *  
 *  e 보다 작은 키의 갯수와 바이너리 서치.
 *      binarySearch(e, left, right) 에서 
 *          left 와 right 가 cross 한 순간, 
 *              left 은 e 보다 작은 요소의 갯수.
 *              큰 요소의 갯수는 N-작은 요소의 갯수.
 *              
 *              [3, 5, 7, 9, 11], e = 8
 *                  i  j
 *                  j  i
 *      binarySearch 에서 left 은 절대 0 보다 작은 수로 줄어들지 않는다.
 *      즉 floor == rank (단 rank == N 이라면 keys[N-1], 
 *          rank == 0 이고 첫번째 요소와 e 가 같지 않다면 첫 번째 요소보다 작은 요소는 없으므로 null)
 *      
 *   인덱스의 고찰.
 *       e 의 인덱스 i 은 e 의 앞에있는 요소의 갯수와 같다.
 *       [1, 2, 3, 4]
 *                 3    = 3 은 4 의 index 면서 앞의 요소의 갯수. 
 * @param <K>
 * @param <V>
 */
public class BinarySearchST<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private int capacity = 2, N = 0;
    @SuppressWarnings("unchecked")
    private K[] keys = (K[]) new Comparable[capacity];
    @SuppressWarnings("unchecked")
    private V[] values = (V[]) new Object[capacity];
    
    @Override
    public void put(K key, V value) {
        int rank = rank(key);
        if (rank < N && keys[rank].equals(key))
            values[rank] = value;
        else {
            if (N+1 == capacity)
                resize(capacity << 1);
            for (int i=N; i>rank; i--) {
                keys[i] = keys[i-1];
                values[i] = values[i-1];
            }
            keys[rank] = key;
            values[rank] = value;
            N++;
        }
        assert checkVariant();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        K[] keys = (K[]) new Comparable[newCapacity];
        V[] values = (V[]) new Object[newCapacity];
        for (int i=0; i<N; i++) {
            keys[i] = this.keys[i];
            values[i] = this.values[i];
        }
        this.keys = keys;
        this.values = values;
        capacity = newCapacity;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) return null;
        int rank = rank(key);
        if (rank < N && keys[rank].equals(key))
            return values[rank];
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return keys[N-1];
    }

    @Override
    public void delete(K key) {
        if (!contains(key))
            return;
        int rank = rank(key);
        for (int i=rank;rank<N-1; rank++) {
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        keys[N-1] = null;
        values[N-1] = null;
        N--;
        if (N > 0 && N == (capacity >> 2))
            resize(capacity >> 1);
        assert checkVariant();
    }

    @Override
    public K floor(K key) {
        int rank = rank(key);
        if (rank == 0) return null;
        if (rank < N && keys[rank].equals(key))
            return keys[rank];
        return keys[rank-1];
    }

    @Override
    public K ceiling(K key) {
        int i = rank(key);
        if (i == N) return null;
        return keys[rank(key)];
    }

    @Override
    public int rank(K key) {
        return rank(key, 0, N);
    }

    private int rank(K key, int left, int right) {
        if (left >= right) return left;
        int mid = (left + right) / 2;
        int c = key.compareTo(keys[mid]);
        if (c == 0) return mid;
        if (c < 0) return rank(key, left, mid);
        else return rank(key, mid+1, right);
    }

    @Override
    public K select(int k) {
        checkElementIndex(k, N);
        return keys[k];
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        if (right.compareTo(left) < 0) 
            return new LinkedList<>();
        Queue<K> q = new LinkedList<>();
        int l = rank(left), r = rank(right);
        for (; l<r; l++)
            q.enqueue(keys[l]);
        if (contains(right))
            q.enqueue(keys[r]);
        return q;
    }

    @Override
    public Iterable<V> values() {
        Queue<V> q = new LinkedList<>();
        for (int i=0; i<N; i++)
            q.enqueue(values[i]);
        return q;
    }
    
    private boolean checkVariant() {
        return isSorted() && rangeCheck();
    }

    private boolean rangeCheck() {
        for (int i=0; i<N; i++) {
            if (i != rank(select(i))) return false;
            if (!keys[i].equals(select(rank(keys[i])))) return false;
        }
        return true;
    }

    private boolean isSorted() {
        for (int i=1; i<N; i++)
            if (keys[i].compareTo(keys[i-1]) < 0)
                return false;
        return true;
    }
}
