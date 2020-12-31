package com.siwoo.algo.algospot;

import java.util.Scanner;

/**
 * L 개의 팀과 L 일 이상의 공연 개최.
 * N 개의 공연장 비용.
 * A[i] = i 일을 빌리는 데 드는 비용.
 * 
 * N 과 L 그리고 A 가 주어졌을 때 최소 평균 비용.
 * 
 */
public class P1_2 {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int N = scanner.nextInt(),
                 L = scanner.nextInt();
            int[] A = new int[N];
            for (int i=0; i<N; i++)
                A[i] = scanner.nextInt();
            double min = Double.POSITIVE_INFINITY;
            for (int i=0; i<N-L; i++) {
                double sum = 0;
                for (int j=i; j<L+i; j++)
                    sum += A[j];
                min = Math.min(sum/L, min);
                for (int j=L+i, cnt=L+1; j<N; j++, cnt++) {
                    sum += A[j];
                    min = Math.min(sum/cnt, min);
                }
            }
            System.out.println(min);
        }
    }
}
