package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * ()( ( (()()) (()) () ) )
 *       ------ ----        //겹치는 판자의 수 3
 *     -----------------
 *   ----------------------  
 *   
 * 현재 레이저를 r 지금까지 겹쳐진 판자의 수를 n 이라 했을 때,
 * 생성되는 부분 판자의 수는 n, 한 판자의 끝을 만났을 땐 + 1 (레이저로 잘리고 남은 부분)
 * 
 * 판자와 레이저의 구분 방법.
 *  이전의 열린 괄호의 위치가 i-1 이라면 레이저, 아니라면 판자.
 *  
 */
@Using(algorithm = Algorithm.STACK)
public class P10799 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String s = reader.readLine();
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(i);
            else {
                int j = stack.pop();
                if (i - j == 1)
                    cnt += stack.size();
                else
                    cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
