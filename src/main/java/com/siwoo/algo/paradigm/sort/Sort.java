package com.siwoo.algo.paradigm.sort;

import com.siwoo.algo.sedgewick.collection.PriorityQueue;
import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 정렬 sort.
 * 
 *  elements 의 나열된 순서를 어떤 논리적 순서에 맞도록 바꾸는 작업.
 *  다시말해, 어떤 잘 정의된 규칙에 따라 elements 의 key 사이에 순서를 정하고, 순서대로 elements 을 재배열.
 *  
 */
public interface Sort<E extends Comparable<E>> {

    /**
     * is e1 less than e2?
     * 
     * @param e1
     * @param e2
     * @return
     */
    default boolean less(E e1, E e2) {
        return e1.compareTo(e2) < 0;
    }

    /**
     * swap index i and j
     * 
     * @param elements
     * @param i
     * @param j
     */
    default void swap(E[] elements, int i, int j) {
        if (i == j) return;
        E e = elements[i];
        elements[i] = elements[j];
        elements[j] = e;
    }
    
    default void show(E[] elements) {
        int N = elements.length;
        for (int i=0; i<N; i++)
            System.out.print(elements[i] + " ");
        System.out.println();
    }

    /**
     * is the elements sorted?
     * 
     * @param elements
     * @return
     */
    default boolean isSorted(E[] elements) {
        return isSorted(elements, 0, elements.length);
    }
    
    default boolean isSorted(E[] elements, int start, int end) {
        checkElementIndex(start, elements.length);
        checkElementIndex(end-1, elements.length);
        int N = end;
        for (int i=start+1; i<N; i++)
            if (less(elements[i], elements[i-1]))
                return false;
        return true;
    }
    
    void sort(E[] elements);

    static void show(Double[] a, int i, int min) {
        StdDraw.setYscale(-a.length + i + 0.8, i + 0.8);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.filledRectangle(k, a[k] * 0.3, 0.25, a[k] * 0.3);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.filledRectangle(k, a[k] * 0.3, 0.25, a[k] * 0.3);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(min, a[min] * 0.3, 0.25, a[min] * 0.3);
    }
    
    static void main(String[] args) throws FileNotFoundException {
        String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tiny.txt";
        Scanner scanner = new Scanner(new FileInputStream(path));
        String[] data = scanner.nextLine().split("\\s+");
        Sort<String> sort = new PriorityQueue<>();
        sort.sort(data);
        assert sort.isSorted(data);
        sort.show(data);

        Sort<Integer> sort2 = new MergeSort<>();
        Integer[] data2 = new Integer[50];
        for (int i=0; i<50; i++)
            data2[i] = StdRandom.uniform(100);
        sort2.sort(data2);
        assert sort2.isSorted(data2);
        sort2.show(data2);
        
//        int n = 50;
//        StdDraw.setCanvasSize(450, 640);
//        StdDraw.setXscale(-1, n+1);
//        StdDraw.setPenRadius(0.015);
//        Double[] a = new Double[n];
//        for (int i = 0; i < n; i++)
//            a[i] = StdRandom.uniform(0.0, 1.0);
//        InsertionSort<Double> sort = new InsertionSort<>();
//        sort.sort(a);
    }
}
