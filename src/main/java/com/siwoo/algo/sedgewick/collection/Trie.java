package com.siwoo.algo.sedgewick.collection;


import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * [algo] [trie]
 *  문자열 집합 S 의 각 문자열 s 의 문자들로 이루어진 자료 구조.
 *  
 * problem
 *  * 문자열 집합 S 에서 문자열 s 을 찾고 싶다.
 *
 * components
 *  1. node
 *      단 하나의 부모 노드. (단, root 은 부모가 없으므로 예외)
 *      각 노드는 radix 크기의 자식을 가진다.
 *      각 노드는 특정 문자을 의미한다. (단, root 은 예외)
 *      각 저장된 문자열의 마지막 문자에 해당하는 노드엔 값이 저장된다.
 *      
 *  2. prefix
 *      tire 에서 노드의 시퀀스는 어떤 문자열의 prefix 을 의미한다.
 *      
 *  algorithm.
 *      탐색.
 *          경우의 수.
 *          
 *          1. s 의 마지막 문자에 해당하는 노드의 값이 존재하는 경우. => 탐색 성공
 *          2. s 의 마지막 문자에 해당하는 노드의 값이 null 인 경우. => 탐색 실패.
 *              어떤 문자열의 prefix 이지만 존재하지 않은 키.
 *          3. null 노드를 만나는 경우. => 탐색 실패.
 *          
 *      삽입.
 *          경우의 수.
 *          
 *          1. s 의 마지막 문자에 도달하기 전 null 노드를 만나는 경우.
 *              => 새로운 노드를 생성.
 *          2. s 의 마지막 문자에 도달했고 null 노드를 만나는 경우.
 *              => 새로운 노드를 생성하고 값을 설정.
 *          3. s 의 마지막 문자에 도달했고 null 노드가 아닌 경우.
 *              => 값을 설정.
 *      
 *      노드의 문자 모으기. (collect)
 *          트라이는 각 노드가 암시적 (prefix) 으로 특정 문자만 의미하므로
 *          명시적인 키를 만들어야 한다.
 *          이때 pre order 순서를 이용.
 *          
 *          이때 자식 노드 자신은 어떤 문자를 의미하는 지 모르므로, 부모 레벨에서 문자을 이어줘야 한다.
 *          
 *      삭제.
 *          경우의 수 (삭제할 노드 n 이 존재하는 경우)
 *          
 *          1. 삭제할 노드의 자식이 모두 null.
 *              해당 노드를 삭제해야 된다.
 *              또한 그 부모또한 해당 노드를 삭제함으로써 조건 1 이 만족하면 재귀적으로 삭제.
 *              
 *          2. 삭제할 노드의 자식 중 하나 이상이 null 이 아닌 경우.
 *              자식 노드만 삭제한다.
 *              
 *  limitation
 *      1.
 *
 *  time complexity
 *      * O(key*R)
 */
public class Trie<E> implements SymbolTable<String, E> {
    private static final int RADIX = 1<<8;
    private Node<E> root = new Node<>();
    
    private static class Node<E> {
        E value;
        char c;
        @SuppressWarnings("unchecked")
        Node<E>[] children = new Node[RADIX];
        
        private boolean exists() {
            return value != null;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", c, value);
        }
    }

    @Override
    public void put(String key, E value) {
        checkNotNull(key, value);
        root = put(root, key, value, 0);
    }

    private Node<E> put(Node<E> root, String key, E value, int index) {
        if (key.length() == index) {
            root.value = value;
            return root;
        }
        char u = key.charAt(index);
        Node<E> child = root.children[u];
        if (child == null) {
            child = root.children[u] = new Node<>();
            child.c = u;
        }
        root.children[u] = put(child, key, value, index+1);
        return root;
    }

    @Override
    public E get(String key) {
        Node<E> node = get(root, key, 0);
        return node == null? null: node.value;
    }

    /**
     * node that represents s[index].
     * note that the node might exists but isn't key.
     * 
     * @param root
     * @param key
     * @param index
     * @return
     */
    private Node<E> get(Node<E> root, String key, int index) {
        if (root == null) return null;
        if (key.length() == index) return root;
        char u = key.charAt(index);
        return get(root.children[u], key, index+1);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node<E> root) {
        if (root == null) return 0;
        int size = 0;
        if (root.exists())
            size++;
        for (char c=0; c<RADIX; c++)
            size += size(root.children[c]);
        return size;
    }

    @Override
    public Iterable<String> keys() {
        return prefixWith("");
    }

    @Override
    public Iterable<E> values() {
        Queue<E> q = new LinkedList<>();
        return values(root, q);
    }

    private Iterable<E> values(Node<E> root, Queue<E> q) {
        if (root == null) return q;
        if (root.exists()) q.enqueue(root.value);
        for (char c=0; c<RADIX; c++)
            values(root.children[c], q);
        return q;
    }

    /**
     * the longest prefix of the given string
     *
     * @param s
     * @return
     */
    private String longestPrefixOf(String s) {
        checkNotNull(s);
        int length = longestPrefixOf(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int longestPrefixOf(Node<E> root, String s, int index, int length) {
        if (root == null) return length;
        if (root.exists())
            length = Math.max(index, length);
        // s's prefix cannot be longer than s's length
        if (s.length() == index) return length;
        char u = s.charAt(index);
        return longestPrefixOf(root.children[u], s, index+1, length);
    }

    /**
     * the keys matched with the given pattern
     *  '.' is used as wildcard.
     *  
     * @param pattern
     * @return
     */
    private Iterable<String> match(String pattern) {
        checkNotNull(pattern);
        Queue<String> q = new LinkedList<>();
        return match(root, pattern, 0, "", q);
    }

    private Iterable<String> match(Node<E> root, String pattern, int index, String prefix, Queue<String> q) {
        if (root == null) return q;
        if (root.exists() 
                && pattern.length() == prefix.length())
            q.enqueue(prefix);
        if (pattern.length() == prefix.length())
            return q;
        char u = pattern.charAt(index);
        for (char c=0; c<RADIX; c++)
            if (c == u || u == '.') // match with u or u is wildcard
                match(root.children[c], pattern, index+1, prefix+c, q);
        return q;
    }

    /**
     * the keys that has a given string as a prefix
     *
     * @param prefix
     * @return
     */
    public Iterable<String> prefixWith(String prefix) {
        checkNotNull(prefix);
        Queue<String> q = new LinkedList<>();
        Node<E> node = get(root, prefix, 0);
        return collect(node, prefix, q);
    }

    private Iterable<String> collect(Node<E> node, String prefix, Queue<String> q) {
        if (node == null) return q;
        if (node.exists())
            q.enqueue(prefix);
        for (char c=0; c<RADIX; c++)
            collect(node.children[c], prefix+c, q);
        return q;
    }

    @Override
    public void delete(String key) {
        checkNotNull(key);
        if (!contains(key)) return;
        root = delete(root, key, 0);
    }

    private Node<E> delete(Node<E> root, String key, int index) {
        if (root == null) return null;
        if (key.length() == index)  // found
            root.value = null;
        else {
            char u = key.charAt(index);
            root.children[u] = delete(root.children[u], key, index+1);
        }
        if (root.exists()) return root;
        // if all children are null then delete the node
        for (char c=0; c<RADIX; c++)    
            if (root.children[c] != null)
                return root;
        return null;
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        String s = "she sells sea shells by the sea shore sh";
        int i = 0;
        for (String w: s.split("\\s+"))
            trie.put(w, i++);
        System.out.println(trie.get("shells"));
        System.out.println(trie.get("shell"));
        System.out.println(trie.get("tesla"));

        System.out.println(trie.size());

        System.out.println(trie.keys());
        System.out.println(trie.prefixWith("sh"));

        System.out.println(trie.match(".he"));

        System.out.println(trie.longestPrefixOf("she"));
        System.out.println(trie.longestPrefixOf("shell"));
        System.out.println(trie.longestPrefixOf("shellsort"));
        System.out.println(trie.longestPrefixOf("sellstree"));
        
        trie.delete("shells");
        System.out.println(trie.get("shells"));
        System.out.println(trie.get("she"));

        for (String w: s.split("\\s+"))
            System.out.println(w + ": " + trie.get(w));
        System.out.println(trie.values());
    }
}
