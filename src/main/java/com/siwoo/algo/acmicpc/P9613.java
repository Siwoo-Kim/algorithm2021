package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static com.siwoo.algo.util.Algorithm.BRUTE_FORCE;

/**
 * sum(gcd(nC2))
 */
@Using(algorithm = BRUTE_FORCE)
public class P9613 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            String[] s = reader.readLine().split("\\s+");
            int[] a = new int[Integer.parseInt(s[0])];
            for (int i=0; i<a.length; i++)
                a[i] = Integer.parseInt(s[i+1]);
            long gcd = selectionOfGCD(a, 0, new Stack<>());
            System.out.println(gcd);
        }
    }
    
    public static long selectionOfGCD(int[] a, int index, Stack<Integer> stack) {
        if (stack.size() == 2)
            return gcd(stack.get(0), stack.get(1));
        long answer = 0;
        for (int i=index; i<a.length; i++) {
            stack.push(a[i]);
            answer += selectionOfGCD(a, i+1, stack);
            stack.pop();
        }
        return answer;
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
