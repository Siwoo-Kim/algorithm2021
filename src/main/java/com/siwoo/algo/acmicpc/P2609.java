package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

@Using(algorithm = Algorithm.MATH)
public class P2609 {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int a = scanner.nextInt(),
                b = scanner.nextInt();
        int gcd = gcd(a, b);
        int lcm = gcd * (a/gcd) * (b/gcd);
        System.out.println(gcd);
        System.out.println(lcm);
    }
    
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
