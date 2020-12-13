package com.siwoo.algo.paradigm.math;

/**
 * (a + b) % m  == ((a % m) + (a % m)) % m
 * (a * b) % m  == ((a % m) * ( a % m)) % m
 * (a - b) % m  == ((a % m) - ( a % m) + m) % m 
 *      
 *      0 <= a % m < m
 *      0 <= b % m < m
 *      
 *      (a%m - b%m) 의 결과는 -c < (a%m - b%m) < c 을 만족
 *      따라서, 0 <= (a%m-b%m) + m < 2m 이므로
 *      이후에 % m 으로 다시 나눠준다.
 *  
 */
public class Modular {
    
    public static long plus(long a, long b, long mod) {
        return ((a % mod) + (b % mod)) % mod;
    }
    
    public static long multiply(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }
    
    public static long minus(long a, long b, long mod) {
        return ((a % mod) - (b % mod) + mod) % mod;
    }

    public static void main(String[] args) {
        int a = 31;
        int b = 13;
        int mod = 3;
        System.out.printf("%d + %d %% %d = %d%n", a, b, mod, (a + b) % mod);
        System.out.printf("%d + %d %% %d = %d%n", a, b, mod, plus(a, b, mod));
        
        System.out.printf("%d - %d %% %d = %d%n", a, b, mod, (a - b) % mod);
        System.out.printf("%d - %d %% %d = %d%n", a, b, mod, minus(a, b, mod));
        
        System.out.printf("%d * %d %% %d = %d%n", a, b, mod, (a * b) % mod);
        System.out.printf("%d * %d %% %d = %d%n", a, b, mod, multiply(a, b, mod));
    }
}
