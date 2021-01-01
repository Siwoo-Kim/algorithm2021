package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P10819 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(A);
        int answer = 0;
        do {
            answer = Integer.max(answer, calc(0));
        } while (nextPermutation(A));
        System.out.println(answer);
    }

    private static boolean nextPermutation(int[] A) {
        int i = A.length-1;
        while (i != 0 && A[i-1] >= A[i])
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

    public static int calc(int x) {
        if (x == A.length-1) return 0;
        return Math.abs(A[x] - A[x+1]) + calc(x+1);
    }
}
