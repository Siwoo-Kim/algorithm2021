package com.siwoo.algo.paradigm.sort;

/**
 * [algo] [Merge Sort]
 *
 * problem
 *  * Sequence 을 논리적 순서에 맞게 정렬하며 N^2 보다 나은 성능을 성취.
 *
 * [algo] components
 *  1. 배열 a 의 분할 정렬된 부분 왼쪽, 오른쪽 배열 left, right.
 *  2. 병합을 위한 보조 배열 aux.
 *
 *  [algo] flow.
 *      1. 배열 a 을 left, right 배열로 분할.
 *      2. 정렬된 left, right 배열을 aux 로 병합.
 *
 *  [algo] limitation
 *      1. 추가 메모리
 *
 *  [algo] time complexity
 *      * NlogN
 */
public class MergeSort<E extends Comparable<E>> implements Sort<E>{
    private E[] copy;
    
    @Override
    public void sort(E[] elements) {
        copy = (E[]) new Comparable[elements.length];
        mergeSort(elements, 0, elements.length);
    }

    private void mergeSort(E[] elements, int left, int right) {
        if (right-left < 2) return; //base case: 요소가 하나인 배열은 이미 정렬된 상태이다.
        int mid = (left + right) / 2;
        mergeSort(elements, left, mid);
        mergeSort(elements, mid, right);
        merge(elements, left, mid, right);
    }

    private void merge(E[] elements, int left, int mid, int right) {
        assert isSorted(elements, left, mid);
        assert isSorted(elements, mid, right);
        if (less(elements[mid-1], elements[mid])) return;
        for (int i=left; i<right; i++)
            copy[i] = elements[i];
        int l = left, r = mid;
        for (int i=left; i<right; i++) {
            if (l == mid) elements[i] = copy[r++];
            else if (r == right) elements[i] = copy[l++];
            else if (less(copy[r], copy[l])) elements[i] = copy[r++];
            else elements[i] = copy[l++];
        }
        assert isSorted(elements, left, right);
    }
    
    public static int divideAndConquer(int N) {
        if (N == 1) return 1;
        return divideAndConquer(N/2) + divideAndConquer(N/2) + 1;
    }

    public static void main(String[] args) {
        System.out.println(divideAndConquer(16));
    }
}
