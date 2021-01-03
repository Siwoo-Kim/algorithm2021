package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Using(algorithm = Algorithm.BITSET)
public class P14391 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] A;
    private static int[] DH = {0, 1}, DV = {1, 0};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new int[N][M];
        for (int i=0; i<N; i++)
            A[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int answer = 0;
        for (int bitset=0; bitset<=(1<<N*M)-1; bitset++) {
            int sum = 0;
            boolean[][] visit = new boolean[N][M];
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++)
                    sum += calc(i*M+j, bitset, visit);
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }

    private static int calc(int id, int bitset, boolean[][] visit) {
        int row = id / M,
                col = id % M,
                sum = 0;
        boolean hz = horizontal(id, bitset);
        while (valid(row, col) 
                && !visit[row][col] 
                && hz == horizontal(id, bitset)) {
            visit[row][col] = true;
            sum = sum * 10 + A[row][col];
            int[] d;
            if (hz)
                d = DH;
            else
                d = DV;
            row += d[0];
            col += d[1];
            id = row * M + col;
        }
        return sum;
    }

    private static boolean valid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    private static boolean horizontal(int id, int bitset) {
        return (bitset & (1 << id)) != 0;
    }
}
