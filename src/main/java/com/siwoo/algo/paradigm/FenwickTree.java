package com.siwoo.algo.paradigm;

/**
 * Fenwick Tree.
 *  0 .. i 구간의 합을 구하기 위한 segment tree 을 개선한 알고리즘.
 *  
 *  Fenwick Tree inspire
 *      s-e 의 구간의 합은 오른쪽 구간 노드 (mid+1 ~ e) 사실 필요없다.
 *      sum = a + b 라면, sum-a = b 이므로.
 * 
 *  Fenwick Tree component
 *      1. l(i) 함수
 *          i 을 2 진수로 표현했을 때, 가장 첫번째 1의 값.
 *          l(6) = 110 = 2, l(12) = 1100 = 4
 *          
 *          l(i) 의 값 구하기.
 *              i = 1100
 *              ~i = 0011
 *              ~i+1 = 0100
 *              ~i+1 = -i (2의 보수)
 *              l(i) = i & -i
 *              
 *      2. tree[i] 은 i 까지 왼쪽으로 l(i) 만큼 구간 합.
 *      
 *      
 */
public class FenwickTree {
}
