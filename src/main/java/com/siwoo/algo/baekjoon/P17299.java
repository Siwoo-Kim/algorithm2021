package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 오등큰수.
 *  P17298 과 같은 로직.
 *  
 *  a[i] 에 대해서 스택에 있는 원소들의 등장 횟수를 비교하며 오등큰수를 구해준다.
 *  (스택에 존재하는 원소 e 에 대해 a[i] < e 면서, e 의 뒤에 있으면서 e < x 성질을 이용.)
 *  
 */
@Using(algorithm = Algorithm.STACK)
public class P17299 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        a = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int e : a) {
            cnt.putIfAbsent(e, 0);
            cnt.put(e, cnt.get(e) + 1);
        }
        
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[a.length];
        for (int i=0; i< a.length; i++) {
            while (!stack.isEmpty()
                    && cnt.get(a[stack.peek()]) < cnt.get(a[i]))
                answer[stack.pop()] = a[i];
            stack.push(i);
        }
        while (!stack.isEmpty())
            answer[stack.pop()] = -1;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<answer.length; i++)
            sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }
}
