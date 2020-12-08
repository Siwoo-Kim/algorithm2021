package com.siwoo.algo.paradigm;

/**
 * 삽입 정렬 insertion sort
 *  
 * 삽입 정렬 컴포넌트.
 *      - 정렬된 부분 o, 정렬되지 않은 부분 uo
 *      - uo 의 정렬되지 않은 요소 i.
 *      
 * 삽입 정렬 알고리즘.
 *      - uo 의 가장 첫 요소 i 을 i 보다 큰 요소 i-1 을 오른쪽으로 밀어 삽 이동.
 *      - uo 의 모든 요소가 o 에 속한다면 정렬 완료.
 *      
 * @param <E>
 */
public class InsertionSort<E extends Comparable<E>> implements Sort<E> {
    
    @Override
    public void sort(E[] elements) {
        for (int uo=0; uo<elements.length; uo++) {
            int i = uo;
            while (i > 0 && less(elements[i], elements[i-1])) {
                swap(elements, i, i - 1);
                i--;
            }
        }
    }
}