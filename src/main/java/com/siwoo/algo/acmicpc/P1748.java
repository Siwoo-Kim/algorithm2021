package com.siwoo.algo.acmicpc;

import java.util.Scanner;

public class P1748 {
    private static Scanner scanner = new Scanner(System.in);
    private static long N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        long prev = 0, nums = 0;
        boolean found = false;
        for (long k=1, j=10; ;k+=1, j*=10) {
            long last = j-1-prev;
            if (j > N) {
                last = N - prev;
                found = true;
            }
            nums += last * k;
            prev += last;
            if (found) break;
        }
        System.out.println(nums);
    }
}
