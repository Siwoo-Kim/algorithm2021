package com.siwoo.algo.algospot;

import java.util.Stack;

/**
 * 재귀와 완전 탐색.
 *  
 *  재귀 recur
 *      유사한 형태를 띄는 작업을 조각으로 쪼갠 뒤,
 *      하나만의 작업을 수행하고, 나머지는 자기 자신을 수행해 실행하는 함수.
 *  
 *  base case
 *      더이상 쪼개지지 않은 최소한의 작업에 대한 재귀 호출.
 *
 * 완전 탐색 레시피.
 *  1. 시간 복잡도는 경우의 수에 비례한다.
 *  2. 답의 후보를 만드는 과정을 재귀로 조각내 여러 개의 선택으로 나눈다.
 *  3. 하나의 조각에선 답의 일부를 만들고, 나머지 답을 재귀 호출을 완성
 *  4. 조각이 하나밖에 남지 않은 경우, 답을 생성했으므로 base case 로 선택해 처리.
 */
public class BruteForce {
    
    public static int sum(int N) {
        if (N == 1) return 1;   // base case
        return sum(N-1) + N;
    }

    public static void pick(int N, int M, Stack<Integer> picked) {
        if (picked.size() == M) {
            for (int e: picked)
                System.out.print(e + " ");
            System.out.println();
        } else {
            int last = picked.isEmpty()? 0: picked.peek();
            for (int i=last+1; i<=N; i++) {
                picked.push(i);
                pick(N, M, picked);
                picked.pop();
            }
        }
    }
    
    public static void main(String[] args) {
        pick(7, 4, new Stack<>());
    }
}
