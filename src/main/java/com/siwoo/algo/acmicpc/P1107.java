package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P1107 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M, MAX = 500000;
    private static Set<Integer> broken = new HashSet<>();

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        for (int i=0; i<M; i++)
            broken.add(scanner.nextInt());
        int min = Math.abs(N-100);
        for (int i=0; i<=MAX*2; i++) {
            if (canGo(i)) {
                int length = i == 0? 1: 0, x = i;
                while (x != 0) {
                    x /= 10;
                    length++;
                }
                min = Math.min(min, length+Math.abs(i-N));
            }
        }
        System.out.println(min);
    }

    private static boolean canGo(int i) {
        if (i == 0) return !broken.contains(i);
        while (i != 0) {
            int x = i % 10;
            if (broken.contains(x))
                return false;
            i /= 10;
        }
        return true;
    }
}
