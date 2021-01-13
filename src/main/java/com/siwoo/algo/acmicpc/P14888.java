package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 
 * 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 
 * 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 * 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 
 * 
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 */
public class P14888 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, MAX = 1000000000;
    private static int[] ops, a;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        a = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        ops = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int max = select(0, new Stack<>(), -MAX, Math::max),
                min = select(0, new Stack<>(), MAX, Math::min);
        System.out.println(max);
        System.out.println(min);
    }

    private static int select(int index, Stack<Integer> stack, 
                              int start,
                              BiFunction<Integer, Integer, Integer> reducer) {
        if (index == N-1) {
            Integer[] ops = stack.toArray(new Integer[0]);
            int reduce = a[0];
            for (int i=1; i<N; i++)
                reduce = calc(reduce, a[i], ops[i-1]);
            return reduce;
        }
        int answer = start;
        for (int i=0; i<4; i++) {
            if (ops[i] > 0) {
                ops[i]--;
                stack.push(i);
                answer = reducer.apply(answer, select(index+1, stack, start, reducer));
                stack.pop();
                ops[i]++;
            }
        }
        return answer;
    }

    private static int calc(int left, int right, int op) {
        if (op == 0) return left + right;
        if (op == 1) return left - right;
        if (op == 2) return left * right;
        if (op == 3) return left / right;
        throw new AssertionError();
    }
}
