package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 효주는 포도주 시식회에 갔다. 그 곳에 갔더니, 테이블 위에 다양한 포도주가 들어있는 포도주 잔이 일렬로 놓여 있었다. 
 * 효주는 포도주 시식을 하려고 하는데, 여기에는 다음과 같은 두 가지 규칙이 있다.
 *
 * 1.포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
 * 2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
 * 
 * 효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다. 
 * 1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주/ 잔에 들어있는 포도주의 양이 주어졌을 때, 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오. 
 *
 * D[n]{0, 1, 2} 을 현재까지 연속한 잔의 갯수 0~2 와 n 자리 까지 포도주를 마셨을 때의 최대양이라 가정한다면,
 *  D[n][0] = max(D[n-1][0], D[n-1][1], D[n-2][2])
 *  D[n][1] = D[n-1][0] + A[n]
 *  D[n][2] = D[n-1][1] + A[n]
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P2156 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(reader.readLine());
        D = new Integer[N][3];
        dp(N-1);
        System.out.println(max3(D[N-1][0], D[N-1][1], D[N-1][2]));
    }

    private static void dp(int i) {
        if (i < 0) return;
        dp(i-1);
        D[i][0] = max3(getD(i-1, 0), getD(i-1, 1), getD(i-1, 2));
        D[i][1] = getD(i-1, 0) + A[i];
        D[i][2] = getD(i-1, 1) + A[i]; 
    }

    private static Integer getD(int i, int state) {
        if (i < 0) return 0;
        return D[i][state];
    }

    private static int max2(int a, int b) {
        return Math.max(a, b);
    }
    
    private static int max3(int a, int b, int c) {
        return max2(max2(a, b), c);
    }
}
