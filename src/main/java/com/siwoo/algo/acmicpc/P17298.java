package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 오등큰수
 *  오른쪽에 있으면서 a[i] 보다 큰 수.
 *  
 *  1. a[i] 에 대해서, 스택에 a[i] 보다 작은 수가 있을때까지
 *      스택을 비워준다.
 *  2. a[i] 을 스택에 넣는다.
 *  
 *  a[i] 의 왼쪽편에 대한 (스택에) 오큰수를 모두 찾아줬음으로 정답.
 *  
 *  스택에 a[i] 보다 큰 어떤 수 p 보다 작고 a[i] 보다 작은 원소를 처리할 수 있는가?
 *      => 위의 가정 자체가 모순.
 *      
 *  위 과정에서 스택에 존재하는 p 요소의 아래에는 p 보다 작은 수만 존재할 수 있다.
 *   => 만약 스택의 위에 어떤 원소 p 가 아래의 스택 아래의 p2 보다 작다고 가정하자.
 *   => 1 과정으로 p 의 차례에서 p2 을 처리했으므로 (p 은 p2 의 오큰수) 이러한 경우는 존재할 수 없다.
 *  
 */
@Using(algorithm = Algorithm.STACK)
public class P17298 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        B = new int[N];
        Arrays.fill(B, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i=1; i<N; i++) {
            while (!stack.isEmpty() 
                    && A[i] > A[stack.peek()])
                B[stack.pop()] = A[i];
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int e: B)
            sb.append(e).append(" ");
        System.out.println(sb);
    }
}
