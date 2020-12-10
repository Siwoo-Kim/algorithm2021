package com.siwoo.algo.sedgewick.collection;

/*** [algo] [red-black tree]
 * 균형 잡힌 2-3 노드로 이루어진 트리.
 *  
 *  2 노드: one node with two links. (left <= node <= right)
 *  3 노드: two nodes with three links. (left <= node1 <= mid <= node2 <= right)
 *  
 * problem
 *  * 키의 삽입 순서와 상관없이 균형잡힙 bst 을 구현하고 싶다. (logN)
 *  
 * red-black tree 의 불변식.
 *  1. red link 은 왼쪽으로 기운다. (큰 키에서 작은 키로 연결)
 *  2. red link 은 in-degree 간선으로만 정의된다. (부모와 연결된 간선)
 *  3. 어떤 노드도 두 개의 레드 링크에 연결되지 않는다. (4 노드는 허용하지 않는다.)
 *  4. 트리는 complete black balanced 상태이다.
 *  
 * [algo] components
 *  1. red link (왼쪽으로 기울여진 3 노드) 
 *      red link 에서 왼쪽으로 기울여진 노드를 v, 오른쪽 노드를 w 라 하자.
 *      red link 로 연결된 3 노드는 아래를 만족한다.
 *      
 *      v.left <= v <= v.right <= w <= w.right
 *      
 *  2. black link
 *  
 *  rotation. red-black 트리의 속성을 만족하는 작업.
 *      left rotation.
 *          오른쪽으로 기울여진 링크를 왼쪽으로 바꾸는 작업.
 *          
 *          오른쪽으로 기울여진 노드 n1 (parent) 과 n2 (right) 가 있다고 하자.
 *          
 *          n2 의 left 은 n2 >= n2.left 을 만족하면서 n1 <= n2.left 을 만족하므로
 *          n2 을 부모로 옮기고 n1.right = n2.left 로 변경해도 트리의 속성을 망치지 않는다.
 *          
 *          n1 <= n2.left <= n2 
 *          
 *                      n2
 *          n1
 *              n2.left 
 *              n1 과 n2 사이
 */
public class RedBlackTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    
    private class Node {
        K key;
        V value;
        boolean color;
        int size;
        Node left, right;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
    
    private Node leftRotation(Node node) {
        //node -> right to node <- right
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = true;
        right.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }
    
    private int size(Node node) {
        return node == null? 0: node.size;
    }
    
    public boolean isRed(Node node) {
        if (node == null) return false;
        return node.color;
    }
    
    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<V> values() {
        return null;
    }
}
