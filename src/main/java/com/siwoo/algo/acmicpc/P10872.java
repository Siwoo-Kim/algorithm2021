package com.siwoo.algo.acmicpc;

import java.util.Scanner;

public class P10872 {

    public static void main(String[] args) {
        System.out.println(fac(new Scanner(System.in).nextInt()));
    }
    
    public static int fac(int n) {
        if (n == 0) return 1;
        return n * fac(n-1);
    }
}
