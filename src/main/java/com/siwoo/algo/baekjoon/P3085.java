package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.
 *
 * 가장 처음에 N×N 크기에 사탕을 채워 놓는다. 
 * 사탕의 색은 모두 같지 않을 수도 있다. 
 * 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 
 * 이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.
 *
 * 사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.
 * 
 * 5
 * YCPZY
 * CYZZP
 * CCPPP
 * YCYZC
 * CPPZZ
 * = 4
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class P3085 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 0;
    private static char[][] BOARD;
    private static int[][] D = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new char[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();
        int max = go(0, 0);
        System.out.println(max);
    }

    private static int go(int x, int y) {
        if (x == N && y == N) return 0;
        if (y == N) { y = 0; x++; }
        int max = 0;
        for (int[] d: D) {
            int dx = x + d[0],
                    dy = y + d[1];
            if (!valid(dx, dy)) continue;
            swap(x, y, dx, dy, BOARD);
            int temp= countCandies(BOARD);
            max = Math.max(max, temp);
            swap(x, y, dx, dy, BOARD);
        }
        return Math.max(max, go(x, y+1));
    }

    private static int countCandies(char[][] BOARD) {
        int max = 0;
        for (int i=0; i<N; i++) {
            int rowBound = 1,
                    columnBound = 1;
            for (int j=1; j<N; j++) {
                if (BOARD[i][j] == BOARD[i][j-1])
                    rowBound++;
                else
                    rowBound=1;
                max = Math.max(max, rowBound);
                if (BOARD[j][i] == BOARD[j-1][i])
                    columnBound++;
                else
                    columnBound=1;
                max = Math.max(max, columnBound);
            }
        }
        return max;
    }

    private static void swap(int x, int y, int dx, int dy, char[][] BOARD) {
        char t = BOARD[x][y];
        BOARD[x][y] = BOARD[dx][dy];
        BOARD[dx][dy] = t;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
