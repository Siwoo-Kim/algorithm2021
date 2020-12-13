package com.siwoo.algo.sedgewick.collection;

import static com.google.common.base.Preconditions.*;

/**
 * [algo] [red-black balanced bst]
 *  2-3 트리를 red link 와 black 링크로 표현하는 균형잡힌 정렬된 트리.
 *  
 * problem
 *  * 입력되는 순서에 관계없이 logN 을 보증하는 bst 을 구현하고 싶다.
 *
 * [algo] components
 *  1. 2-node (1 개의 키와 left, right 을 자식으로 가지는)
 *      left >= n >= right
 *  2. 3-node (2 개의 키와 left, mid, right 을 자식으로 가지는)
 *      left >= n1 >= mid >= n2 >= right
 *  3. red link
 *      두 개의 2 노드를 묶어 3 노드를 표현
 *  4. black link
 *      2 노드를 표현.
 *      
 * [algo] in-variant.
 *  red link 은 왼쪽으로만 흐른다.   큰 키 right 에서 작은 키 left 로 
 *  두 개로 연속된 레드 링크는 존재할 수 없다. (4-node 을 허용하지 않는다.)
 *  트리는 perfect black balanced 상태이다.
 *  
 *  
 *  레드 블랙 트리의 in-variant 유지 작업.
 *      rotation, flipColors
 *      
 *      rotation
 *          red link 의 방향을 바꾸는 작업.
 *          
 *      rotateLeft
 *          오른쪽으로 연결된 레드 링크를 교정한다.
 *          
 *          - red link, = black link
 *          
 *          (n) 
 *              -       red link
 *                  (s)
 *                =     black link
 *             (mid)
 *          
 *                 (s)
 *             -        red link
 *        (n)
 *              =       black link
 *               (mid)
 *      
 *      rotateRight
 *          왼쪽으로 연결된 레드 링크를 오른쪽으로 바꾼다. (4노드 분할을 위한 교정 작업)
 *
 *  레드 블랙 트리의 삽입 알고리즘. 
 *      5 가지의 경우의 수.
 *          1. 2-node 의 left insertion  (n > x)
 *          2. 2-node 의 right insertion (n < x) right leaning-red link
 *          3. 3-node 의 left insertion  (n < x1, x2)  two consecutive left leaning red link & creating 4-node
 *          4. 3-node 의 mid insertion   (x1 > n > x2) right leaning-red link & creating 4-node 
 *          5. 3-node 의 right insertion (x1, x2 < n) creating 4-node
 *      
 *      2 노드로의 삽입.
 *          단순히 3-노드를 생성한다.
 *          이후에 오른쪽으로 red link 가 생겼다면 교정한다.
 *      
 *      3 노드로의 삽입. = black link, - red link
 *          x1, x2 < n 일때,
 *          
 *                  =       
 *              - x2 - 
 *          x1        n
 *          
 *          flipColors(x2)
 *               -
 *            = x2 =
 *         x1        n
 *         
 *         n < x1, x2 일때,
 *                      =
 *                  - x2
 *            - x1
 *         n
 *         
 *         rotateRight(x2)
 *              =
 *              x1
 *           -     -
 *         n        x2
 *         
 *         flipColors(x1)
 *             -
 *             x1
 *         =        =
 *       n            x2
 *       
 *       x1 < n < x2 일때,
 *                      =
 *                  - x2
 *             x1 - 
 *                 n
 *      
 *        rotateLeft(x1)
 *                          =
 *                     - x2
 *                  - n
 *              x1
 *              
 *       n < x1, x2 의 경우와 같으므로 같은 과정으로 fix
 *       
 *       정리하자면,
 *       1. left & right 가 red link 라면 flipColors(node) 한 후 중간 키를 부모로 올린다.
 *       2. 두 개의 연속된 red link 가 생성되었다면 rotateRight(node) 하여 1 번 작업을 준비한다.
 *       3. right leaning red-link 가 생성되었다면 rotateLeft(node) 하여 레드 블랙 트리의 불변식을 지켜준다.
 *
 *  레드 블랙 트리의 삭제 알고리즘.
 *      (최소 삭제)
 *      삽입 작업하는 위치가 2 노드에서 끝나지 않는다는 불변 조건을 만족하게 한다. 
 *          (즉, 여유있는 형제의 키를 빌려와 트리의 키를 유지하도록 공간을 마련한다)
 *      트리를 내려가면서 왼쪽 자식 노드가 2-노드가 아니도록 보증.
 *      
 *      루트의 변환.
 *          경우의 수.
 *              1. 루트가 3-노드인 경우
 *                  할 것이 없다.
 *              2. 루트가 2-노드이고 두 자식 모두가 2-노드인 경우.
 *                  세 노드를 4-노드로 변환한다.
 *              3. 루트가 2-노드이고 두 자식 중 하나가 3-노드인 경우.
 *                  오른쪽 형제의 노드에서 키를 빌려와 왼쪽 자식 노드가 2-노드가 아니게 보증한다.
 *      
 *      루트의 변환을 수행하면서 아래를 수행.
 *          경우의 수. 
 *              1. 왼쪽 자식이 2-노드이다.   
 *              2. 왼쪽 자식이 2-노드이고 오른쪽 자식은 3-노드이다.
 *              3. 왼쪽 자식 노드와 오른쪽 자식 노드가 2-노드이다
 *              
 *          만약 현재 노드의 왼쪽 자식 노드가 2-노드가 아니라면 아무것도 할 게 없다.
 *          만약 left 가 2-node 이고, right 가 3-node 라면 right 에서 키를 부모로 올리고 부모에서 작은 키를 left 로 옮긴다.
 *          만약 left, right 가 2-node 라면 부모의 작은 키와 함께 4 노드를 만들고 부모 노드를 한 단계 작은 노드로 만든.
 *          
 *              
 */
public class RedBlackTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    
    enum COLOR {
        RED, BLACK;
        
        COLOR flip() {
            return this == RED? BLACK: RED;
        }
    }
    
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left, right;
        private COLOR color;

        public Node(K key, V value, int size, COLOR color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }

        public void flipColor() {
            color = color.flip();
        }

        @Override
        public String toString() {
            return String.format("(%s, %d, %s)", key, size, color);
        }
    }
    
    private Node root;

    /**
     * flip colors of node and it's children
     * = separate 4-node into three 2-nodes.
     * 
     * @param node
     */
    public void flipColors(Node node) {
        node.flipColor();
        node.left.flipColor();
        node.right.flipColor();
    }
    
    /**
     * 
     * @param root
     * @return
     */
    public Node rotateRight(Node root) {
        Node left = root.left;
        root.left = left.right;    //left <= mid <= root
        left.right = root;
        left.color = root.color;
        root.color = COLOR.RED;
        left.size = root.size;
        root.size = size(root.left) + size(root.right) + 1;
        return left;
    }

    /**
     * fixing right-leaning red link
     * 
     * @param root
     * @return
     */
    public Node rotateLeft(Node root) {
        Node right = root.right;
        root.right = right.left;    //root <= mid <= right
        right.left = root;
        right.color = root.color;   //reserve root's link color
        root.color = COLOR.RED;
        right.size = root.size;
        root.size = size(root.left) + size(root.right) + 1;
        return right;
    }

    /**
     * size of the given tree
     * 
     * @param node
     * @return
     */
    private int size(Node node) {
        if (node == null) return 0; // null node has no children
        return node.size;
    }

    /**
     * is the node binding with parent node?
     * null nodes are always black.
     * 
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == COLOR.RED;
    }
    
    @Override
    public K min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null) return root;
        return min(root.left);
    }

    @Override
    public K max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node root) {
        if (root.right == null) return root;
        return max(root.right);
    }

    /**
     * largest a key equal or less than given key
     * 
     * @param key
     * @return
     */
    @Override
    public K floor(K key) {
        checkNotNull(key);
        Node node = floor(root, key);
        return node == null? null: node.key;
    }

    private Node floor(Node root, K key) {
        if (root == null) return null;
        if (less(key, root.key)) return floor(root.left, key);
        if (less(root.key, key)) {
            Node right = floor(root.right, key);
            return right == null? root: right;
        }
        return root;
    }

    /**
     * smallest key equal or larger than given key
     * 
     * @param key
     * @return
     */
    @Override
    public K ceiling(K key) {
        checkNotNull(key);
        Node node = ceiling(root, key);
        return node == null? null: node.key;
    }

    private Node ceiling(Node root, K key) {
        if (root == null) return null;
        if (less(key, root.key)) {
            Node left = ceiling(root.left, key);
            return left == null? root: left;
        }
        if (less(root.key, key)) return ceiling(root.right, key);
        return root;
    }

    /**
     * the number of keys less then given key
     * 
     * @param key
     * @return
     */
    @Override
    public int rank(K key) {
        checkNotNull(key);
        return rank(root, key);
    }

    private int rank(Node root, K key) {
        if (root == null) return 0;
        if (less(key, root.key)) 
            return rank(root.left, key);
        if (less(root.key, key))
            return size(root.left) + rank(root.right, key) + 1;
        else
            return size(root.left);
    }

    /**
     * a key of given rank
     * 
     * @param k
     * @return
     */
    @Override
    public K select(int k) {
        checkElementIndex(k, size());
        return select(root, k);
    }

    private K select(Node root, int k) {
        if (root == null) return null;
        if (size(root.left) == k) return root.key;
        if (size(root.left) < k) return select(root.right, k - size(root.left) -1);
        return select(root.left, k);
        
    }

    @Override
    public Iterable<K> keys(K left, K right) {
        Queue<K> q = new LinkedList<>(); //descending
        dfs(root, q, left, right);
        return q;
    }

    private void dfs(Node root, Queue<K> q, K left, K right) {
        if (root == null) return;
        dfs(root.left, q, left, right);
        if (between(root.key, left, right))
            q.enqueue(root.key);
        dfs(root.right, q, left, right);
    }

    @Override
    public Iterable<V> values() {
        Queue<V> q = new LinkedList<>();
        dfsValues(root, q);
        return q;
    }

    private void dfsValues(Node root, Queue<V> q) {
        if (root == null) return;
        dfsValues(root.left, q);
        q.enqueue(root.value);
        dfsValues(root.right, q);
    }

    private boolean between(K key, K left, K right) {
        if (equals(key, left) || equals(key, right)) return true;
        return less(left, key) && less(key, right);
    }

    private boolean equals(K key1, K key2) {
        return key1.compareTo(key2) == 0;
    }

    @Override
    public void put(K key, V value) {
        checkNotNull(key);
        if (value == null)
            delete(key);
        else {
            root = put(root, key, value);
            root.color = COLOR.BLACK; //root has no parent
        }
    }

    private Node put(Node root, K key, V value) {
        if (root == null) return new Node(key, value, 1, COLOR.RED); //new node always be red
        if (less(key, root.key)) root.left = put(root.left, key, value);
        else if (less(root.key, key)) root.right = put(root.right, key, value);
        else {
            root.value = value;
        }
        //right-leaning red link
        if (isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
        //two consecutive red links
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        //separate 4-node into 2 nodes
        if (isRed(root.left) && isRed(root.right)) flipColors(root);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public V get(K key) {
        checkNotNull(key);
        Node node = get(root, key);
        return node == null? null: node.value;
    }

    private Node get(Node root, K key) {
        if (root == null) return null;
        if (less(key, root.key)) return get(root.left, key);
        if (less(root.key, key)) return get(root.right, key);
        return root;
    }

    private boolean less(K key1, K key2) {
        checkNotNull(key1, key2);
        return key1.compareTo(key2) < 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public K popMin() {
        if (isEmpty())
            return null;
        if (!isRed(root.left) && !isRed(root.right))
            root.flipColor();
        K key = min(root).key;
        root = popMin(root);
        if (!isEmpty()) root.flipColor();
        return key;
    }

    private Node popMin(Node root) {
        if (root.left == null) return null;
        if (!isRed(root.left) && !isRed(root.left.left))
            root = moveRedLeft(root);
        root.left = popMin(root.left);
        return balance(root);
    }

    private Node balance(Node root) {
        if (isRed(root.right)) root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left)) root = rotateRight(root);
        if (isRed(root.left) && isRed(root.right)) flipColors(root);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private Node moveRedLeft(Node root) {
        flipColors(root);
        if (isRed(root.right.left)) { // if root.left and root.left.left are black
                                      // root.left or one of its children red.
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
            flipColors(root);
        }
        return root;
    }

    @Override
    public K popMax() {
        if (isEmpty()) return null;
        if (isRed(root.left) && isRed(root.right))
            root.flipColor();
        K key = max();
        root = popMax(root);
        if (!isEmpty())
            root.flipColor();
        return key;
    }

    private Node popMax(Node root) {
        if (isRed(root.left))
            root = rotateRight(root);
        if (root.right == null) return null;
        if (!isRed(root.right) && !isRed(root.right.left))
            root = moveRedRight(root);
        root = popMax(root.right);
        return balance(root);
    }

    private Node moveRedRight(Node root) {
        flipColors(root);
        if (isRed(root.left.left)) {
            root = rotateRight(root);
            flipColors(root);
        }
        return root;
    }

    @Override
    public int size(K left, K right) {
        checkNotNull(left, right);
        if (less(right, left)) return 0;
        int size = rank(right) - rank(left);
        if (contains(right)) size++;
        return size;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException();
    }

    private Node delete(Node root, K key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        RedBlackTree<String, Integer> tree = new RedBlackTree<>();
        String[] seq = {"A", "C", "E", "H", "L", "M", "P", "R", "S", "X"};
        for (int i=0; i<seq.length; i++)
            tree.put(seq[i], i);
        System.out.println(tree.get("A"));

        System.out.println(tree.floor("N"));
        System.out.println(tree.floor("F"));
        
        System.out.println(tree.ceiling("N"));
        System.out.println(tree.ceiling("F"));

        System.out.println(tree.rank("H"));
        System.out.println(tree.rank("M"));
        System.out.println(tree.rank("B"));

        System.out.println(tree.select(0));
        System.out.println(tree.select(5));
        System.out.println(tree.select(8));

        System.out.println(tree.size("E", "L"));
        System.out.println(tree.size("A", "M"));
        System.out.println(tree.size("D", "N"));
    }
}
