package com.siwoo.algo.paradigm;

/**
 * 쉘 정렬 shell sort
 *   삽입 정렬의 개선 버전.
 *   
 * 쉘 정렬 inspire
 *      가장 작은 요소가 N-1 에 존재한다면 총 N-1 번의 교환을 하는 비효율성을 개선.
 *
 *  쉘 정렬 컴포넌트.
 *      - insertion sort 와 같음.
 *      - 배열 a 을 h 간격의 부분 배열 ha 로 나눔.
 *      - 부분 배열 ha 에 대해서 insertion sort 을 수행. (pre processing)
 *      - h 가 1 이 되었을때 배열 a 에 대한 삽입 정렬을 수행.
 *      - h 을 구하는 방법은 여러가지. 1/2(3^k-1) 순열을 사용.
 */
public class ShellSort<E extends Comparable<E>> implements Sort<E> {
    
    @Override
    public void sort(E[] elements) {
        int N = elements.length;
        int h = 1;
        while (h < N / 3)
            h = h * 3 + 1;      //1, 4, 13, 40, 121, 364..
        while (h >= 1) {
            for (int i=h; i<N; i++) {
                int j = i;
                while (j>=h && less(elements[j], elements[j-h])) {
                    swap(elements, j, j - h);
                    j-=h;
                }
            }
            h /= 3;
        }
    }
}
