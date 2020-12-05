package com.siwoo.algo.sedgewick;

import java.io.*;
import java.util.*;

/**
 * 이진 탐색 binary search
 *  정렬된 배열 a 에 값 k 의 index?
 *      k 가 a 에 있을 경우 k 은 a[left..right] 에 존재.
 *      중간키 mid 에 대해 k 와의 대소 관계를 비교해 범위를 줄여가며 탐색.
 *      단, left<=right.
 */
public class BinarySearch {
    
    private static <E extends Comparable<E>> 
        int binarySearch(E[] values, E key) {
        return binarySearch(values, 0, values.length-1, key);
    }

    private static <E extends Comparable<E>> 
        int binarySearch(E[] values, int l, int r, E key) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        int c = key.compareTo(values[mid]);
        if (c == 0) return mid;
        if (c > 0) return binarySearch(values, mid+1, r, key);
        return binarySearch(values, l, mid-1, key);
    }

    public static void main(String[] args) {
        Queue<Integer> data = new LinkedList<>(),
                query = new LinkedList<>();
        try (Scanner scanner = new Scanner(new BufferedInputStream(
                Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("./algs4-data/tinyW.txt"))))) {
            while (scanner.hasNext())
                data.add(scanner.nextInt());
        }
        Integer[] ints = new Integer[data.size()];
        int i = 0;
        while (!data.isEmpty())
            ints[i++] = data.poll();
        Arrays.sort(ints);
        try (Scanner scanner = new Scanner(new BufferedInputStream(
                Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("./algs4-data/tinyT.txt"))))) {
            while (scanner.hasNext())
                query.add(scanner.nextInt());
        }
        while (!query.isEmpty())
            System.out.println(binarySearch(ints, query.poll()));
    }
}
