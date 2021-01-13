package com.siwoo.algo.programmers;

import java.util.Arrays;

/**
 * 풀이.
 * 
 * 껍질 삼각형을 아랫 방향 (row+1, col), 오른쪽 방향 (row, col+1), 대각선 반대 방향 (row-1, col-1) 을 채운다.
 * 
 * 삼각형의 cell 의 갯수는 1개, 2개 3개 .. 순이므로 n(n+1)/2
 * 
 * 삼각형의 끝점의 도달한지 알아내기.
 *  1. row 혹은 col 은 0 <= row, col < n
 *  2. 현재 채워넣어야 할 수 k 은 n(n+1)/2 을 넘지 않는다.
 *  3. 이미 채워넣지 않은 cell 이여야 한다.
 */
public class 삼각달팽이 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(5)));
    }

    static class Solution {
        private int[][] T;
        
        public int[] solution(int size) {
            T = new int[size][size];
            int N = (size * (size+1)) / 2;
            int k = 1, row = 0, col = 0;
            T[row][col] = k;
            while (k < N) {
                while (row+1 < size && k < N && T[row+1][col] == 0)
                    T[++row][col] = ++k;
                while (col+1 < size && k < N && T[row][col+1] == 0)
                    T[row][++col] = ++k;
                while (row-1 >= 0 && col-1 >= 0 && k < N && T[row-1][col-1] == 0)
                    T[--row][--col] = ++k;
            }
            k = 0;
            int[] answer = new int[N];
            for (int i=0; i<T.length; i++)
                for (int j=0; j<=i; j++)
                    answer[k++] = T[i][j];
            return answer;
        }
    }
}
