package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 규현이가 쓴 수를 A라고 하면, A[i]는 규현이가 i번째 쓴 수이다. 
 * 그리고, S[i][j]는 A[i]부터 A[j]까지 합이 0보다 크면 +, 0이면 0, 0보다 작으면 -이다. 
 * 여기서 i는 항상 j보다 작거나 같다. 
 * 이렇게 배열을 채우면 배열에는 총 N*(N+1)/2개의 문자가 있다. 
 * (+, -, 0 중 하나) 이 S 배열이 주어졌을 때, 
 * 규현이가 쓴 N개의 수 A를 구해서 출력하면 된다. 
 * 규현이는 -10부터 10까지의 정수밖에 모르기 때문에, 
 * A도 -10부터 10까지의 정수로만 이루어져 있어야 한다.
 * 
 */
@Using(algorithm = Algorithm.BACK_TRACKING)
public class P1248 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;
    private static char[][] S;
    private static String s;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        s = reader.readLine();
        S = new char[N][N];
        int k = 0;
        for (int i=0; i<N; i++)
            for (int j=i; j<N; j++)
                S[i][j] = s.charAt(k++);
        int[] answer = subseqSum(0);
        StringBuilder sb = new StringBuilder();
        for (int e: answer)
            sb.append(e).append(" ");
        System.out.println(sb);
    }

    private static int[] subseqSum(int index) {
        if (index == N) return check(A, index)? A: null;
        int[] answer = null;
        for (int i=-10; i<=10; i++) {
            A[index] = i;
            if (!check(i, S[index][index])) //backtracking
                continue;
            if (check(A, index)) {  //backtracking
                answer = subseqSum(index + 1);
                if (answer != null)
                    return answer;
            }
        }
        return answer;
    }

    private static boolean check(int[] A, int END) {
        int k = 0;
        for (int i=0; i<END; i++) {
            int sum = 0;
            for (int j=i; j<END; j++) {
                sum += A[j];
                if (!check(sum, s.charAt(k++)))
                    return false;
            }
            k += N-END;
        }
        return true;
    }

    private static boolean check(int s, char c) {
        if (c == '-' && s < 0) return true;
        if (c == '+' && s > 0) return true;
        if (c == '0' && s == 0) return true;
        return false;
    }
}