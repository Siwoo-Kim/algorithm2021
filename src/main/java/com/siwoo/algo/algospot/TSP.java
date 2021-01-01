package com.siwoo.algo.algospot;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.AppConfig;
import com.siwoo.algo.util.Using;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * N 개의 도시에 전부를 한 번씩 방문한 후 시작 도시로 돌아올 때 최소 경로.
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class TSP {
    private boolean[] visit;
    private int[][] dist;
    private int N, ANSWER;

    public TSP(int N, int[][] dist) {
        this.N = N;
        this.dist = dist;
        visit = new boolean[N];
        ANSWER = shortestPath();
    }

    public int shortestPath() {
        visit[0] = true;
        return shortestPath(0,  0, 0);
    }

    private int shortestPath(int v, int cost, int cnt) {
        if (cnt == N-1) return cost + dist[v][0];
        int min = Integer.MAX_VALUE;
        for (int k=0; k<N; k++) {
            if (!visit[k]) {
                visit[k] = true;
                min = Math.min(min, shortestPath(k, cost + dist[v][k], cnt+1));
                visit[k] = false;
            }
        }
        return min;
    }

    private int answer() {
        return ANSWER;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new FileInputStream(AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/tsp.txt"));
        int N = scanner.nextInt();
        int[][] dist = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++)
                dist[i][j] = scanner.nextInt();
        }
        TSP tsp = new TSP(N, dist);
        System.out.println(tsp.answer());
    }
}
