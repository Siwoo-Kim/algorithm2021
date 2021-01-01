package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P10974 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static int[] A;

    public static void main(String[] args) {
        N = scanner.nextInt();
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = i+1;
        StringBuilder sb = new StringBuilder();
        do {
            for (int e: A)
                sb.append(e).append(" ");
            sb.append("\n");
        } while (nextPermutation(A));
        System.out.println(sb);
    }

    private static boolean nextPermutation(int[] A) {
        int i = A.length-1;
        while (i != 0 && P10974.A[i-1] >= P10974.A[i])
            i--;
        if (i == 0) return false;
        int j = A.length-1;
        while (A[i-1] >= A[j])
            j--;
        swap(i-1, j, A);
        j = A.length-1;
        while (i < j)
            swap(i++, j--, A);
        return true;
    }

    private static void swap(int i, int j, int[] A) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
