package com.siwoo.algo.paradigm;

import edu.princeton.cs.algs4.StdRandom;

/**
 * [algo] [quick sort]
 *
 * problem
 *  * Sequence 을 논리적 규칙에 따라 정렬하기 위해 추가 메모리 없이 NlogN 의 성능을 성취.
 *
 * [algo] components
 *  1. 최종 요소의 위치 pivot. a[0..pivot-1] <= a[pivot] <= a[pivot+1...N-1] 
 *  2. pivot 의 위치를 정한 이후, 분할 정복 (post order)
 *
 *  [algo] flow.
 *      1. 배열 a 에서 임의의 요소를 선택해 pivot 의 위치로 이동.
 *      2. a[left..pivot-1] 와 a[pivot+1..right] 요소에 대해서 재귀적으로 1 을 수행.
 *      3. 배열 a 의 모든 요소가 pivot 이 되었다면 정렬 완료.
 *
 *      pivot 의 위치찾기.
 *          1. a[left] 을 피봇으로 선택.
 *          2-1. 왼쪽에서 피봇보다 큰 요소 i 를 검색.
 *          2-2. 오른쪽에서 피봇보다 작은 요소 j 을 검색.
 *          2-3. i 와 j 은 pivot 의 최종 위치를 벗어나는 요소이므로 swap. 이 과정을 i >= j 동안 반복
 *          3. 위 작업이 끝나면 i 의 왼쪽편에는 pivot 보다 작고, j의 오른쪽편에는 pivot 보다 큰 요소만 남게됨.
 *          4. 작업을 마무리 하기 위해, 왼쪽 요소 중 가장 오른쪽에 있는 요소 j을 pivot 과 교환하고 j 을 리턴.
 *          
 *  [algo] limitation
 *      1. 분할이 균형 있게 이루어지지 않으면 극단적으로 비효율일 수 있다.
 *          => 분할 과정 중 트리의 높이가 N-1 이라면 극단적으로 비효율.
 *          이를 위해, 배열을 shuffle 한다.
 *      2. not stable
 *      
 *  [algo] time complexity
 *      * NlogN
 */
public class QuickSort<E extends Comparable<E>> implements Sort<E> {
    
    @Override
    public void sort(E[] elements) {
        StdRandom.shuffle(elements);
        quickSort(elements, 0, elements.length);
    }

    private void quickSort(E[] elements, int left, int right) {
        if (right - left < 2) return;
        int pivot = partition(elements, left, right);
        quickSort(elements, left, pivot);
        quickSort(elements, pivot+1, right);
    }

    private int partition(E[] elements, int left, int right) {
        E e = elements[left];
        int i = left, j = right;
        while (true) {
            while (less(e, elements[--j])) ;    //e cannot be less than itself
            while (less(elements[++i], e)) if (i == right-1) break;
            if (i >= j) break;
            swap(elements, i, j);
        }
        swap(elements, left, j);
        return j;
    }
}
