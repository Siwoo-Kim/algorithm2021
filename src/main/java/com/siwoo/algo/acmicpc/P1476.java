package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P1476 {
    private static Scanner scanner = new Scanner(System.in);
    private static int E = 16, S = 29, M = 20;
    
    public static void main(String[] args) {
        int e = scanner.nextInt(),
                s = scanner.nextInt(),
                m = scanner.nextInt();
        int e1 = 0, s1 = 0, m1 = 0, year = 0;
        while (true) {
            year++;
            e1++;
            s1++;
            m1++;
            if (e1 == E)
                e1 = 1;
            if (s1 == S)
                s1 = 1;
            if (m1 == M)
                m1 = 1;
            if (e == e1 && s == s1 && m == m1) {
                System.out.println(year);
                return;
            }
        }
    }
}
