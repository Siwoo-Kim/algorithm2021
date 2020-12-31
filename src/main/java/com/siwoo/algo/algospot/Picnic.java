package com.siwoo.algo.algospot;

import com.siwoo.algo.util.AppConfig;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * N 명의 학생에 대한 i, j 가 친구인지 여부인 정보가 주어졌을 때,
 * 서로 친구인 학생 두 명씩 짝을 지어야 할 때 경우의 수.
 * 
 * 3
 * 2 1
 * 0 1
 * 4 6
 * 0 1 1 2 2 3 3 0 0 2 1 3
 * 6 10
 * 0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 */
public class Picnic {
    private boolean[][] friend;
    private boolean[] visit;
    private int N;

    public Picnic(int N, boolean[][] friend) {
        this.N = N;
        this.friend = friend;
        this.visit = new boolean[N];
    }
    
    public int canPair() {
        boolean finished = true;
        int first = -1;
        for (int i=0; i<N; i++)
            if (!visit[i]) {
                finished = false;
                first = i;
                break;
            }
        if (finished) return 1;
        int answer = 0;
        visit[first] = true;
        for (int i=first+1; i<N; i++) {
            if (!visit[i] && friend[first][i]) {
                visit[i] = true;
                answer += canPair();
                visit[i] = false;
            }
        }
        visit[first] = false;
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/picnic.txt";
        Scanner scanner = new Scanner(new FileInputStream(path));
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int N = scanner.nextInt(),
                    M = scanner.nextInt();
            boolean[][] friend = new boolean[N][N];
            for (int m=0; m<M; m++) {
                int x = scanner.nextInt(), y = scanner.nextInt();
                friend[x][y] = friend[y][x] = true;
            }
            Picnic picnic = new Picnic(N, friend);
            System.out.println(picnic.canPair());
        }
    }
}
