package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P13398 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A, D, DR;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new int[N];
        DR = new int[N];
        D[0] = A[0];
        for (int i=1; i<N; i++)
            D[i] = Math.max(D[i-1]+A[i], A[i]);
        DR[N-1] = A[N-1];
        for (int i=N-2; i>=0; i--)
            DR[i] = Math.max(DR[i+1]+A[i], A[i]);
        
        int answer = D[0];
        for (int i=0; i<N; i++)
            answer = Math.max(answer, D[i]);
        for (int k=1; k<N-1; k++) {
            answer = Math.max(D[k-1] + DR[k+1], answer);
        }
        System.out.println(answer);
    }
}
