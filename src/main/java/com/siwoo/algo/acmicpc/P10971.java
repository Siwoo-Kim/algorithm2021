package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P10971 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new int[N][N];
        for (int i=0; i<N; i++)
            D[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        Permutation p = new Permutation(IntStream.range(0, N).toArray());
        long min = Long.MAX_VALUE;
        do {
            long dist = 0;
            boolean ok = true;
            for (int to=1; to<N; to++) {
                int d = D[p.at(to-1)][p.at(to)];
                if (d == 0) ok = false;
                else dist += d;
            }
            int d = D[p.at(N-1)][p.at(0)];
            if (d == 0 || !ok) continue;
            dist += d;
            min  = Math.min(min, dist);
        } while (p.next());
        System.out.println(min);
    }
    
    private static class Permutation {
        private final int[] A;

        public Permutation(int[] A) {
            this.A = A;
        }
        
        public boolean next() {
            int i=A.length-1;
            while (i != 0 && A[i-1] >= A[i]) i--;
            if (i == 0) return false;
            int j=A.length-1;
            while (A[i-1] >= A[j]) j--;
            swap(i-1, j, A);
            j=A.length-1;
            while (i < j)
                swap(i++, j--, A);
            return true;
        }

        private void swap(int i, int j, int[] A) {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }

        public int at(int i) {
            return A[i];
        }
    }
}
