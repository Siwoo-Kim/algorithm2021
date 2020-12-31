package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1..N 부터 숫자가 차례대로 스택에 들어갔고,
 * 스택 수열이 주어졌을 때, 이 수열의 원소를 i 라 하자.
 * 
 * j > i 을 만족하는 숫자들은 반드시 스택에 존재하거나, pop 이 되었어야 한다. (순서대로 push 하였으므로)
 * 이때, 스택에 존재하는 j  반드시 내림차순을 유지해야 한다.
 * 
 * 경우의 수. m = 0
 *  1. m < i 라면 , m 부터 i 까지 모든 요소는 스택에 존재해야 하니 넣어준다.
 *  2. m == i 라면, 스택의 순서가 유효하니 pop 한다.
 *  3. m > i 라면, m 은 어떤 수 j 보다 스택의 위에 존재하니 실패.
 * 
 */
@Using(algorithm = Algorithm.STACK)
public class P1874 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(reader.readLine());
            if (x > j) {
                while (x != j) {
                    stack.push(++j);
                    sb.append("+\n");
                }
            }
            if (stack.isEmpty() || stack.peek() != x) {
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append("-\n");
        }
        System.out.println(sb);
    }
}
