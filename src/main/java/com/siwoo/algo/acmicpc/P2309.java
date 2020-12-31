package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.
 *
 * 아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 
 * 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.
 *
 * 아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.
 * 
 * 풀이.
 *  조합 문제
 *  9C7
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P2309 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 9, R=7;
    private static int[] A = new int[N];

    public static void main(String[] args) throws IOException {
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(reader.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Stack<Integer> stack = combination(0, new Stack<>());
        while (!stack.isEmpty())
            pq.add(stack.pop());
        while (!pq.isEmpty())
            System.out.println(pq.poll());
    }

    private static Stack<Integer> combination(int index, Stack<Integer> stack) {
        if (stack.size() == R) {
            int sum = stack.stream().mapToInt(Integer::intValue).sum();
            return sum == 100? stack: null;
        }
        Stack<Integer> answer;
        for (int i=index; i<N; i++) {
            stack.push(A[i]);
            answer = combination(i+1, stack);
            if (answer != null)
                return answer;
            stack.pop();
        }
        return null;
    }
}
