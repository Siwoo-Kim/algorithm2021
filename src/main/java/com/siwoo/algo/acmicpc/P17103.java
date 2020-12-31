package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P17103 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX+1];
    private static int N;

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<=MAX; i++)
            if (primes[i])
                for (int j=i+i; j<=MAX; j+=i)
                    primes[j] = false;
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int a = Integer.parseInt(reader.readLine()),
                    cnt = 0;
            for (int j=2; j<=a/2; j++)
                if (primes[j] && primes[a-j])
                    cnt++;
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
