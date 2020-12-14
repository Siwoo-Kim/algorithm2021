package com.siwoo.algo.paradigm.dp;

/**
 * [paradigm] [dynamic programming]
 *  큰 문제를 "중복되는" 작은 문제로 나눠, 최대한 이미 연산한 정보를 재활용하여 문제를 해결.
 *  
 * problem
 *  * brute-force 에서 중복 연산을 줄여 알고리즘의 성능을 개선하고 싶다.
 *
 * [algo] components
 *  overlapping sub-problems
 *      - 작은 문제는 중복 되어야 한다. (중복되지 않으면 brute force)
 *      - 이미 한번 구한 문제는 재사용한다. (memorization)
 *      
 *  optimal sub-structures.
 *      - 작은 문제로 큰 문제를 풀 수 있어야 한다.
 *      - 어떤 문제는 한가지 정답만을 가져야 한다.
 *   
 *  다이나믹 문제를 풀 때는 점화식을 세워라.
 *  
 *  f0 = 0
 *  f1 = 1
 *  fn = fn-1 + fn-2 (n>=2)
 *  
 *  
 *  f5 -> f(4, 3)
 *         f4 -> f(3, 2)
 *              f3 -> f(2, 1)   //중복
 *                  f2 -> f(1, 0) //중복
 *                      f1
 *                      f0
 *              f2 -> f(1, 0)   //중복
 *                  f1
 *                  f0
 *         f3 -> f(2, 1)    //중복
 *              f2 -> f(1, 0)   //중복
 *              f1
 *      
 */
public class DynamicProgramming {
}
