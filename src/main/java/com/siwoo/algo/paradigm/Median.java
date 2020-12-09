package com.siwoo.algo.paradigm;


/**
 * [algo] [Median]
 *
 * problem
 *  * 배열 a 에서 k 번째 작은 값을 NlogN 보다 나은 성능으로 찾고 싶다.
 *      0 <= k <= a.length-1
 *      
 * [algo] components
 *  1. 퀵소트의 pivot
 *
 *  [algo] flow.
 *      1. 배열 a 에서 임의의 원소를 선택해 자신의 위치로 이동, 이 위치를 pivot 이라 하자.
 *      2. k == pivot 이라면 k 번째를 찾았으니 리턴.
 *      3-1. k > pivot 이라면 k 번째 원소는 오른쪽에 있으니 오른쪽 배열에 대해서 1 을 반복.
 *      3-2. k < pivot 이라면 k 번째 원소는 왼쪽에 있으니 왼쪽 배열에 대해서 1 을 반복.
 *
 *  [algo] time complexity
 *      * 2N
 */
public class Median {
    
    public static int find(int[] a, int k) {
        find(a, k, 0, a.length);
        return a[k];
    }

    private static void find(int[] a, int k, int left, int right) {
        if (right - left < 2) return;
        int pivot = partition(a, k, left, right);
        if (pivot == k) return;
        if (pivot > k) find(a, k, left, pivot);
        else find(a, k, pivot+1, right);
    }

    private static int partition(int[] a, int k, int left, int right) {
        int e = a[left];
        int i = left, j = right;
        while (true) {
            while (a[--j] > e) ;
            while (a[++i] < e) if (i == right) break;
            if (i >= j) break;
            swap(i, j, a);
        }
        swap(left, j, a);
        return j;
    }

    private static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 1, 9, 8, 7, -1, 0};
        System.out.println(find(a, 3));
    }
}
