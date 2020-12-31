package com.siwoo.algo.acmicpc;

import java.util.Scanner;

public class P6064 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int N = scanner.nextInt(),
                    M = scanner.nextInt(),
                    x = scanner.nextInt() - 1,
                    y = scanner.nextInt() - 1;
            boolean ok = false;
            for (int k=x; k<(N*M); k+=N) {
                if (k % M == y) {
                    System.out.println(k + 1);
                    ok = true;
                    break;
                }
            }
            if (!ok)
                System.out.println(-1);
        }
        
    }
}

