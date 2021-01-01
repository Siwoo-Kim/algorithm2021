package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P10972 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int i = A.length-1;
        while (i > 0 && A[i-1] >= A[i]) i--;
        if (i == 0) {
            System.out.println(-1);
            return;
        }
        int j = A.length-1;
        while (A[i-1] >= A[j]) j--;
        swap(i-1, j, A);
        j = A.length-1;
        while (i < j)
            swap(i++, j--, A);
        StringBuilder sb = new StringBuilder();
        for (int e: A)
            sb.append(e).append(" ");
        System.out.println(sb);
    }

    private static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
