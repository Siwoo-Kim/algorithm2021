package com.siwoo.algo.paradigm.math;

import java.util.Arrays;

/**
 * [algo] [permutation]
 *
 * problem
 *  * 수열 A 의 순열을 구하고 싶다.
 *
 * components
 *  1. 첫 순열.
 *      어떤 순열의 첫 순열은 오름차순.
 *  2. 마지막 순열.
 *      어떤 순열의 마지막 순열은 내림차순.
 *  3. 재귀적 속성.
 *      앞에 어떠한 원소로 고정된 순열 P 에 대해서 속성 1, 2 은 만족한다.
 *      
 *      [2, 3, 1, 4, 5]
 *      2, 3 로 시작하는 순열의 첫 순열.
 *      [2, 3, 5, 4, 1]
 *      2, 3 로 시작하는 순열의 마지막 순열.
 *      
 *      2, 3 로 시작하는 마지막 순열의 다음은 
 *      2, 4 로 시작하는 첫 순열이다.
 *      
 *      [2, 4, 1, 3, 5] 
 *      2, 4 로 시작하는 순열의 첫 순열.
 *      
 *  algorithm.
 *      1. 순열 A 에 대해서 a[i-1] < a[i] 을 만족하는 가장 뒤에 있는 i 을 찾는다.
 *      2. j > i-1 을 만족하는 가장 뒤에 있는 j 을 찾는다.
 *      3. j 와 i-1 을 교환.
 *      4. A[i..N-1] 까지 순열을 뒤집는다. 
 *
 *  순열로 조합 문제 풀기.
 *      N 개의 수열 A 에서 M 개를 선택할 때의 경우의 수를 모두 만들어야 한다고 하자.
 *      A 을 M 개의 1, 나머지 N-M 을 0 으로 채워준 뒤 정렬하여 순열을 만들어주면 된다.
 *      
 *      -> 00111
 *      -> 01011
 *      -> 01101
 *      
 *      N(N! / (N-M)!)
 *  time complexity
 *      * N*N!
 */
public class Permutation {
    private final int[] a;

    public Permutation(int[] a) {
        this.a = a;
    }

    public static long factorial(long N) {
        if (N == 0) return 1;
        return N * factorial(N-1);
    }
    
    public boolean next() {
        return nextPermutation(a);
    }
    
    private boolean nextPermutation(int[] a) {
        int i = a.length-1;
        while (i != 0 && a[i-1] >= a[i])
            i--;
        if (i == 0) return false;
        int j = a.length-1;
        while (a[i-1] >= a[j])
            j--;
        swap(i-1, j, a);
        j = a.length-1;
        while (i < j)
            swap(i++, j--, a);
        return true;
    }

    private void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        System.out.println(factorial(10));
        int[] a = {1, 2, 3, 4};
        Permutation permutation = new Permutation(a);
        do {
            System.out.println(Arrays.toString(a));
        } while (permutation.next());
    }
}
