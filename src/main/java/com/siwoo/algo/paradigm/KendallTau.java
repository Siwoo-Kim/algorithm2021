package com.siwoo.algo.paradigm;


/**
 * [algo] [KendallTau]
 *
 * problem
 *      켄달타우는 두 변수 a, b 의 순위를 비교해 연관성을 계산.
 *      켄달타우의 거리는 두 랭킹 사이에서 서로 다른 순서로 놓여 있는 숫자 쌍의 갯수를 정의.
 *  
 * [algo] components
 *  1. inversions count
 *    => 배열 a 가 정렬된 정도를 수치화.
 *    => 정렬된 배열 a 은 inversion count = 0
 *    => 정렬되지 않은 배열 a 은 inversion count = (i, j) 에 대한 모든 쌍의 갯수.
 *    => i, j 가 inversion 되었다면 a[i] < a[j] && i < j
 *    
 *  [algo] flow.
 *  
 */
public class KendallTau {
    
    public static long distance(int[] a, int[] b) {
        if (a.length != b.length) throw new IllegalArgumentException();
        int N = a.length;
        int[] ainv = new int[N];
        for (int i=0; i<N; i++)
            ainv[a[i]] = i;
        int[] bnew = new int[N];
        for (int i=0; i<N; i++)
            bnew[i] = ainv[b[i]];
        return count(bnew);
    }

    /**
     * returns the number of inversions in a
     * 
     * @param a
     * @return
     */
    private static long count(int[] a) {
        int N = a.length;
        int[] b = new int[N];
        int[] aux = new int[N];
        System.arraycopy(a, 0, b, 0, N);
        return inversions(b, aux, 0, N);
    }

    private static long inversions(int[] a, int[] aux, int left, int right) {
        if (right - left < 2) return 0;
        int mid = (left + right) / 2;
        long inversions = inversions(a, aux, left, mid);
        inversions += inversions(a, aux, mid, right);
        inversions += inversions(a, aux, left, mid, right);
        return inversions;
    }

    private static long inversions(int[] a, int[] aux, int left, int mid, int right) {
        long inversions = 0;
        for (int i=left; i<right; i++)
            aux[i] = a[i];
        int l = left, r = mid;
        for (int i=left; i<right; i++) {
            if (l >= mid) a[i] = aux[r++];
            else if (r >= right) a[i] = aux[l++];
            else if (a[r] < a[l]) { // a[i] > a[j] 라면 i 이후의 요소도 k 도
                                    // a[k] > a[j] 이므로 모두 count
                a[i] = aux[r++];
                inversions += (mid - i + 1);
            } else {
                a[i] = aux[l++];
            }
        }
        return inversions;
    }


    public static void main(String[] args) {
        int[] a = {0, 3, 1, 6, 2, 5, 4},
                b = {1, 0, 3, 6, 4, 2, 5};
        System.out.println(distance(a, b));
    }
}
