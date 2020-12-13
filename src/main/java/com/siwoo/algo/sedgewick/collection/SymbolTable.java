package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * [algo] [SymbolTable]
 *  symbol table 은 key 와 value 에 대한 자료구조로 삽입 put 과 탐색 search 을 지원.
 *  
 * problem
 *  * 키와 값을 연관시켜 저장하고, 키를 통해 연관된 값을 탐색. 
 *
 * [algo] components
 *  1. 중복되지 않은 키와 중복 가능한 값.
 *  2. value 의 널 값 허용에 따른 구현의 차이.
 *  3. 키의 동치성.
 *
 *  [algo] cost model.
 *      키에 대한 비교 연산 (equals or compareTo) 으로 비용 모델을 수립.
 *      비교 연산이 없는 경우 배열의 접근.
 *  
 *  [algo] limitation
 *      1.
 *
 *  [algo] time complexity
 *      * 
 */
public interface SymbolTable<K, V> {

    /**
     * put key-value pair into the table.
     * 
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * get associated value with the given key.
     * 
     * @param key
     * @return
     */
    V get(K key);

    /**
     * delete associated value with given key.
     * 
     * @param key
     */
    default void delete(K key) {
        put(key, null);
    }

    /**
     * is there a value associated with given key?
     * 
     * @param key
     * @return
     */
    default boolean contains(K key) {
        return get(key) != null;
    }
    
    /**
     * is the table empty?
     * 
     * @return
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * number of key-value pairs in the table.
     * 
     * @return
     */
    int size();

    /**
     * all the keys in the table.
     * 
     * @return
     */
    Iterable<K> keys();

    /**
     * all the values in the table.
     * 
     * @return
     */
    Iterable<V> values();

    public static void main(String[] args) throws FileNotFoundException {
        String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tale.txt";
        Scanner scanner = new Scanner(new FileInputStream(path));
        SymbolTable<String, Integer> st = new HashTable<>();
        int min = 8;
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (word.length() < min) continue;
            if (st.contains(word))
                st.put(word, st.get(word) + 1);
            else
                st.put(word, 1);
        }
        String max = "";
        st.put(max, 0);
        for (String word: st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        System.out.println(max + " " + st.get(max));
    }
}
