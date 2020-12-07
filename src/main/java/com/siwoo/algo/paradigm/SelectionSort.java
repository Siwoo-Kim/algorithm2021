package com.siwoo.algo.paradigm;

/**
 * 선택 정렬 selection sort.
 * 
 * 선택 정렬 컴포넌트
 *      - 정렬된 부분 o, 정렬되지 않은 부분 uo.
 *      - uo 의 가장 작은 요소 i
 *
 * 선택 정렬 알고리즘
 *      - uo 에서 i 을 찾은 후 o 에 추가.
 *      - 모든 uo 의 요소가 o 에 추가될때까지 반복.
 *      
 * 선택 정렬 한계.
 *      - n^2/2 의 연산.
 *      - 배열을 순회하면서 그 다음번 작은 항목을 방문하지만, 이러한 추가적인 정보를 사용하지 않는다.
 *      
 * @param <E>
 */
public class SelectionSort<E extends Comparable<E>> implements Sort<E> {
    
    @Override
    public void sort(E[] elements) {
        int N = elements.length;
        for (int o=0; o<N; o++) {
            int min = o;
            for (int i=o+1; i<N; i++) {
                if (less(elements[i], elements[min]))
                    min = i;
            }
            swap(elements, min, o);
        }
    }
}
