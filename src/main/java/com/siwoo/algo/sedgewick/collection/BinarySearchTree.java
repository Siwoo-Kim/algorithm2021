package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.VisualAccumulator;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [Binary Search Tree]
 * binary search tree 은 각 노드의 키가 비교 가능하고, 그 키가 왼쪽의 노드보다 크고
 * 반대쪽의 노드보다 작다는 제약을 가지는 이진 트리이다.
 * 
 * problem
 *  * 비교 가능한 키를 이용해 삽입과 검색이 효율적인 자료구조가 필요.
 *
 * [algo] components
 *  1. node 
 *      in-degree 가 1 이며 out-degree 가 최대 2개.
 *      
 *  2. link (edge)
 *      노드의 갯수가 n 일때, n-1 의 간선의 갯수.
 *      
 *  3. dfs 와 방문 순회.
 *      pre-order - 연산에 부모의 값이 먼저 사용될 때. 
 *      in-order  - 키의 순서대로 방문이 필요할 때.
 *      post-order - 자식의 값이 먼저 사용될 때.
 *      level-order (bfs) - 레벨 단위로 방문할 때.
 *  
 *  4. 근후행 혹은 근선행 노드 successor, predecessor
 *      순위상 뒤에 있는 노드들 중 가장 가까운 노드.
 *      오른쪽 부분에서 가장 왼쪽의 노드 혹은 왼쪽 부분에서 가장 오른쪽의 노드.
 *      
 *      노드 삭제.  delete(K key)
 *          경우의 수.
 *              1. 자식이 없는 경우.  단순히 해당 노드를 삭제.
 *              2. 자식이 하나 있는 경우. 둘 중 하나의 노드를 해당 노드로 대체.
 *              3. 자식이 둘이 있는 경우.
 *                  근후행 노드로 해당 노드를 대체해야 트리의 불변식을 유지할 수 있음.
 *                  
 *                  1. 해당 노드 n 를 t 로 백업
 *                  2. 현재 노드 n 를 min(n.right) 으로 대체.
 *                  3. 해당 노드의 n.left 을 t.left 에 대입.
 *                  4. 대체한 근후행 노드를 삭제 후 deleteMin(t.right)
 *                      현재 노드의 right 로 대입.
 *                      근후행 노드는 이전 노드의 오른쪽 트리에서 가장 왼쪽편 (가장 작은 키였으므로)
 *                      불변식이 유지
 *                      
 *  [algo] limitation
 *      최적 조건 (완전히 균형잡힌 경우 - 트리의 높이가 logN) 을 현실적으로 맞추기 어렵다.
 *      최악의 경우엔 탐색이 N (트리의 높이 N-1) 큼의 시간 복잡도를 가진다.
 *          -> 이진 트리를 사용하려면 퀵 소트와 마찬가지로 삽입 전 시퀀스를 shuffle 하라.
 *
 *  [algo] floor(key) 와 ceiling(key) 연산  - 주어진 key 보다 작거나 같은 키 중 가장 큰 키.
 *      주어진 키가 root.key 보다 작다면 반드시 왼쪽 트리. (root key 은 정답이 될 수 없다)
 *      주어진 키가 root.key 보다 크다면 
 *          floor(root.right) 가 null 이라면 root.key 가 정답키. (오른쪽의 모든 노드가 key 보다 크다)
 *          아니라면 floor(root.right) 가 정답.
 *      
 *      마찬가지로 ceiling 시 root.key < k 라면 반드시 오른쪽에 존재. (root 키는 정답이 될 수 없다.)
 *          root.key > k 라면 가장 큰 키 중 작은 키를 찾아야 하므로 
 *          ceiling(root.key) == null 이라면 root.key 가 정답, 아니라면 ceiling(root.key) 가 정답.
 *      
 *  [algo] rank(key) 와 select(k) 연산 - 주어진 키보다 작은 키가 몇개나 되는가?
 *      size(root.left) == k 라면 탐색 성공.
 *      size(root.left) > k 라면 왼쪽 트리를 탐색.
 *      size(root.left) < k 라면 오른쪽 트리에 해당 k-size(root.left)-1 번째 노드가 정답.
 *            eg) 랭크 5 위는 랭크 3 위의 두번째 이후의 랭크이다.
 *  
 *  [algo] time complexity
 *      * 최악의 경우 트리의 높이 만큼 순회하므로 N
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    
    private class Node {
        private Node left, right;
        private int size;
        private V value;
        private K key;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        public List<Node> getChildren() {
            return Arrays.asList(left, right);
        }
    }
    
    private Node root;
    private VisualAccumulator va = new VisualAccumulator(20000, 50);
    private int cnt;
    
    @Override
    public K min() {
        if (isEmpty())
            return null;
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null) return root;
        return min(root.left);
    }

    @Override
    public K max() {
        if (isEmpty())
            return null;
        return max(root).key;
    }

    private Node max(Node root) {
        if (root.right == null) return root;
        return max(root.right);
    }

    @Override
    public K floor(K key) {
        if (isEmpty()) return null;
        Node node = floor(root, key);
        return node == null? null: node.key;
    }

    private Node floor(Node root, K key) {
        if (root == null) return null;
        if (root.key.compareTo(key) == 0)
            return root;
        else if (less(key, root.key)) {
            return floor(root.left, key);
        } else {
            Node node = floor(root.right, key);
            return node == null? root: node;
        }
    }

    @Override
    public K ceiling(K key) {
        if (isEmpty()) return null;
        Node node = ceiling(root, key);
        return node == null? null: node.key;
    }

    private Node ceiling(Node root, K key) {
        if (root == null) return null;
        if (root.key.compareTo(key) == 0) return root;
        else if (less(root.key, key)) return ceiling(root.right, key);
        else {
            Node node = ceiling(root.left, key);
            return node == null? root: node;
        }
    }

    @Override
    public int rank(K key) {
        if (isEmpty())
            return 0;
        return rank(root, key);
    }

    private int rank(Node root, K key) {
        if (root == null) return 0;
        if (root.key.compareTo(key) == 0)
            return size(root.left);
        else if (less(key, root.key))
            return rank(root.left, key);
        else 
            return size(root.left) + 1 + rank(root.right, key);
    }

    @Override
    public K select(int k) {
        if (isEmpty() || k >= size()) throw new IllegalArgumentException();
        return select(root, k).key;
    }

    private Node select(Node root, int k) {
        if (root == null) return null;
        int size = size(root.left);
        if (size == k) return root;
        else if (size < k) return select(root.right, k-size(root.left)-1);
        return select(root.left, k);
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        if (less(right, left)) return new LinkedList<>();
        Stack<K> stack = new LinkedList<>();
        keys(root, left, right, stack);
        return stack;
    }

    private void keys(Node root, K left, K right, Stack<K> stack) {
        if (root == null) return;
        keys(root.left, left, right, stack);
        if (between(root.key, left, right))
            stack.push(root.key);
        keys(root.right, left, right, stack);
    }

    private boolean between(K key, K left, K right) {
        if (key.compareTo(left) == 0 || key.compareTo(right) == 0)
            return true;
        return less(left, key) && less(key, right); // left < key < right
    }

    @Override
    public void put(K key, V value) {
        cnt = 0;
        checkNotNull(key);
        root = put(root, key, value);
        va.addDataValue(cnt);
        assert checkVariant();
    }

    private Node put(Node root, K key, V value) {
        if (root == null) return new Node(key, value, 1);
        if (root.key.compareTo(key) == 0)
            root.value = value;
        else if (less(key, root.key)) 
            root.left = put(root.left, key, value);
        else 
            root.right = put(root.right, key, value);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public V get(K key) {
        cnt = 0;
        checkNotNull(key);
        Node node = get(root, key);
        va.addDataValue(cnt);
        return node == null? null: node.value;
    }

    private Node get(Node root, K key) {
        if (root == null) return null;
        if (root.key.compareTo(key) == 0) return root;
        if (less(key, root.key)) return get(root.left, key);
        return get(root.right, key);
    }
    
    private boolean less(K key1, K key2) {
        checkNotNull(key1, key2);
        cnt++;
        return key1.compareTo(key2) < 0;
    }

    @Override
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    @Override
    public K popMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        K k = min();
        root = popMin(root);
        return k;
    }

    private Node popMin(Node root) {
        if (root.left == null) return root.right;
        root.left = popMin(root.left);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public K popMax() {
        if (isEmpty())
            throw new NoSuchElementException();
        K k = max();
        root = popMax(root);
        return k;
    }

    private Node popMax(Node root) {
        if (root.right == null) return root.left;
        root.right = popMax(root.right);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node root, K key) {
        if (root == null) return null;
        if (less(key, root.key)) 
            root.left = delete(root.left, key);
        else if (less(root.key, key)) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                //there is two children.
                Node v = root;
                root = min(v.right);
                root.left = v.left;
                root.right = delete(v.right, key);
            }
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Iterable<V> values() {
        Stack<V> stack = new LinkedList<>();
        dfs(root, stack);
        return stack;
    }

    private void dfs(Node root, Stack<V> stack) {
        if (root == null) return;
        stack.push(root.value);
        dfs(root.left, stack);
        dfs(root.right, stack);
    }

    private boolean checkVariant() {
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isRankConsistent() {
        int N = size();
        for (int i=0; i<N; i++) {
            if (rank(select(i)) != i)
                return false;
        }
        for (K key: keys()) {
            if (select(rank(key)).compareTo(key) != 0)
                return false;
        }
        return true;
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node root) {
        if (root == null) return true;
        if (root.size != (size(root.left) + size(root.right) + 1))
            return false;
        return isSizeConsistent(root.left) && isSizeConsistent(root.right);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node root, K min, K max) {
        if (root == null) return true;
        if (min != null && less(root.key, min)) return false;
        if (max != null && less(max, root.key)) return false;
        return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
    }

    public static void main(String[] args) {
        String[] data = "S E X A R C H M".split("\\s+");
        OrderedSymbolTable<String, Integer> table = new BinarySearchTree<>();
        for (int i=0; i<data.length; i++)
            table.put(data[i], i);
        System.out.println(table.floor("G"));   //E
        System.out.println(table.select(3));    //H
        
        table.delete("X");
        System.out.println(table.get("X"));
        table.put("X", 2);
        System.out.println(table.get("X"));
    }
}
