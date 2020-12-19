package com.siwoo.algo.sedgewick.collection;

/**
 * [algo] [name]
 *  문자열을 위한 탐색 트리.
 *  
 * problem
 *  * 문자 집합 S 에서 문자 s (혹은 prefix) 을 탐색하고 싶다.
 *
 * components
 *  1. node
 *      각 노드는 radix 만큼의 링크를 가진다.
 *      각 노드는 부모로부터 들어오는 하나의 indegree 링크만을 가진다. (단, 뿌리는 예외)
 *      각 노드는 부모로부터 링크에서 문자 정보를 유지한다.
 *      
 *  algorithm.
 *      1. 트라이에서 문자 탐색.
 *          뿌리에서부터 해당 i 번째 문자에 해당하는 자식을 호출하며 나올 수 있는 경우의 수.
 *              1. 마지막 문자에 해당하는 자식 노드가 null 이 아니라면, 탐색 성공.
 *              2. 마지막 문자에 해당하는 자식 노드의 값 (혹은 flag) null 이라면 탐색 실패.
 *              3. i 번째 문자에 해당하는 자식 노드가 null 이라면 탐색 실패.
 *          
 *      2. 트라이에 문자 삽입.
 *          뿌리에서부터 해당 i 번째 문자에 해당하는 자식을 호출하며 나오는 경우의 수.
 *              1. 해당 문자열의 마지막 문자에 도달하기 전, 자식 노드가 null 이라면, 새롭게 노드를 생성한다.
 *              이때 해당 i == s.length-1 이라면 키에 연관된 값을 설정한다.
 *              2. 키의 마지막 문자에 해당하는 노드를 만느는 경우, 해당 노드의 value 값을 설정한다.
 *            
 *  time complexity
 *      * 
 */
public class Trie<V> implements SymbolTable<String, V> {
    private static final int R = 1<<8;
    private Node<V> root = new Node<>('\0');
    
    private static class Node<V> {
        V value;
        char c;
        @SuppressWarnings("unchecked")
        Node<V>[] children = new Node[R];

        public Node(char c) {
            this.c = c;
        }

        private boolean exists() {
            return value != null;
        }
        
        @Override
        public String toString() {
            return Character.toString(c);
        }
    }
    
    @Override
    public void put(String key, V value) {
        put(root, key, value, 0);
    }

    private void put(Node<V> root, String key, V value, int i) {
        if (key.length() == i) {
            root.value = value;
            return;
        }
        char c = key.charAt(i);
        Node<V> child = root.children[c];
        if (child == null)
            child = root.children[c] = new Node<>(c);
        put(child, key, value, i+1);
    }

    @Override
    public V get(String key) {
        Node<V> node = get(root, key, 0);
        return node == null? null: node.value;
    }

    private Node<V> get(Node<V> root, String key, int i) {
        if (key.length() == i) return root;
        Node<V> child = root.children[i];
        if (child == null) return null;
        return get(child, key, i+1);
    }

    @Override
    public void delete(String key) {
        
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * the longest key which has a prefix as s
     * 
     * @param s
     * @return
     */
    public String longest(String s) {
        int length = longest(root, 0, s, 0);
        return s.substring(0, length);
    }

    private int longest(Node<V> root, int i, String s, int length) {
        if (root == null) return length;
        if (s.length() == i) return i;
        if (root.exists()) length = i;
        int d = s.charAt(i);
        return longest(root.children[d], i+1, s, length);
    }

    /**
     * all keys which have a prefix as s
     * 
     * @param prefix
     * @return
     */
    public Iterable<String> prefixOf(String prefix) {
        Queue<String> q = new LinkedList<>();
        return collect(get(root, prefix, 0), q, prefix);
    }

    private Queue<String> collect(Node<V> node, Queue<String> q, String prefix) {
        if (node == null) return q;
        if (node.exists()) q.enqueue(prefix);
        for (char i=0; i<R; i++)
            collect(node.children[i], q, prefix+ i);
        return q;
    }

    /**
     * all keys matched with given s,
     * where . symbol is treated as a wildcard character.
     * 
     * @param p pattern
     * @return
     */
    public Iterable<String> match(String p) {
        Queue<String> q = new LinkedList<>();
        return match(root, "", p, q);
    }

    private Iterable<String> match(Node<V> root, String prefix, String p, Queue<String> q) {
        if (root == null) return q;
        if (prefix.length() == p.length()) {
            if (root.exists())
                q.enqueue(prefix);
            return q;
        }
        char next = p.charAt(prefix.length());
        for (char i=0; i<R; i++)
            if (i == next || next == '.')
                match(root.children[i], prefix+i, p, q);
        return q;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node<V> root) {
        if (root == null) return 0;
        int cnt = 0;
        if (root.value != null)
            cnt++;
        for (int i=0; i<R; i++)
            cnt += size(root.children[i]);
        return cnt;
    }

    @Override
    public Iterable<String> keys() {
        return prefixOf("");
    }

    @Override
    public Iterable<V> values() {
        Stack<V> stack = new LinkedList<>();
        return values(root, stack);
    }

    private Iterable<V> values(Node<V> root, Stack<V> stack) {
        if (root == null) return stack;
        for (int i=0; i<R; i++)
            values(root.children[i], stack);
        if (root.exists()) stack.push(root.value);
        return stack;
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.put("she", 0);
        trie.put("sells", 1);
        trie.put("sea", 2);
        trie.put("shells", 3);
        trie.put("by", 4);
        trie.put("the", 5);
        trie.put("sea", 6);
        trie.put("shore", 7);
        System.out.println(trie.size());
        System.out.println(trie.keys());
        System.out.println(trie.prefixOf("sh"));
        System.out.println(trie.values());
        System.out.println(trie.match("se."));
        System.out.println(trie.match("s.."));
        System.out.println(trie.match("sh..."));
        System.out.println(trie.longest("shellsort"));
        System.out.println(trie.longest("shelters"));
    }
}
