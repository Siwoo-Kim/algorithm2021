package com.siwoo.algo.paradigm.math;

import java.util.Arrays;

/**
 * 소수. prime
 *  약수가 1 과 자신밖에 없는 수.
 *  
 *  소수의 특성.
 *      x 가 소수라면 x/2 보다 작거나 같은 자연수로 나누어 떨어지면 안된다.
 *      어떤 수 y 의 최소 약수는 2y 이므로. x/2 + 1 이후의 수 s 부터는 x 의 약수가 될 수 없다.
 *      => 2 ~ x/2 까지만 검사해주면 된다.
 *      
 *      x 가 소수라면 2 보다 크거나 sqrt(x) 보다 작거나 같은 자연수로 나누어 떨어지면 안된다.
 *      N=x*y, x<=y 라면  x<=sqrt(N), y>=sqrt(N) // x 가 약수.
 *      
 *      만약 x<sqrt(N), y<sqrt(N) 이 만족한다면, x*y<N 이므로.
 */
public class Prime {
    private static int MAX = 1000000;
    private static boolean[] PRIMES = new boolean[MAX+1];
    
    static {
        Arrays.fill(PRIMES, true);
        PRIMES[0] = PRIMES[1] = false;
        for (int i=2; i<=MAX; i++) {
            if (PRIMES[i]) {
                for (int j=i+i; j<=MAX; j+=i)
                    PRIMES[j] = false;
            }
        }
    }
    
    private static boolean isPrime(int x) {
        return PRIMES[x];
    }

    public static void main(String[] args) {
        System.out.println(isPrime(2));
        System.out.println(isPrime(3));
        System.out.println(isPrime(41));
        System.out.println(isPrime(89));
        
        System.out.println(isPrime(2<<2));
        System.out.println(isPrime(2<<8));
        System.out.println(isPrime(121));
        System.out.println(isPrime(161));
    }
}
