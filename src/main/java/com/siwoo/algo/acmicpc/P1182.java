package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.BITSET)
public class P1182 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int cnt = 0;
        for (int bitset = 1; bitset<=(1<<N)-1; bitset++) {
            int sum = 0;
            for (int bit = 0; bit < N; bit++) {
                if ((bitset & (1 << bit)) != 0) {
                    sum += A[bit];
                }
            }
            if (sum == S)
                cnt++;
        }
        System.out.println(cnt);
    }
}
