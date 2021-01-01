package com.siwoo.algo.algospot;

import com.siwoo.algo.util.AppConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 학생 N, 친구 쌍의 정보 M 개가 주어졌고
 * 친구끼리만 짝을 지으려고 할 때의 경우의 수는?
 * 
 * 정답에 대한 중복 처리법.
 *  -> 순서를 정해준.
 *  
 */
public class Picnic {
    public boolean[][] isFriend;
    public boolean[] taken;
    public int N, ANSWER;

    public Picnic(int N, boolean[][] isFriend) {
        this.isFriend = isFriend;
        this.N = N;
        taken = new boolean[N];
        ANSWER = numberOfCase();
    }

    public int answer() {
        return ANSWER;
    }
    
    public int numberOfCase() {
        int notPaired = -1;
        for (int i=0; i<N; i++)
            if (!taken[i]) {
                notPaired = i;
                break;
            }
        if (notPaired == -1) return 1;
        int answer = 0;
        for (int i=notPaired+1; i<N; i++) {
            if (isFriend[notPaired][i] && !taken[i]) {
                taken[i] = taken[notPaired] = true;
                answer += numberOfCase();
                taken[i] = taken[notPaired] = false;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream( AppConfig.INSTANCE.getProperty("app.resources.testdata") +"/picnic.txt"));
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int N = scanner.nextInt(),
                    M = scanner.nextInt();
            boolean[][] isFriend = new boolean[N][N];
            for (int i=0; i<M; i++) {
                int v = scanner.nextInt(),
                        w = scanner.nextInt();
                isFriend[v][w] = isFriend[w][v] = true;
            }
            Picnic picnic = new Picnic(N, isFriend);
            System.out.println(picnic.answer());
        }
    }
}
