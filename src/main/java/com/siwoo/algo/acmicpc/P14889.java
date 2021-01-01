package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

/**
 * 오늘은 스타트링크에 다니는 사람들이 모여서 축구를 해보려고 한다. 
 * 축구는 평일 오후에 하고 의무 참석도 아니다. 
 * 축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다. 
 * 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.
 *
 * BOJ를 운영하는 회사 답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다. 
 * 능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다. 
 * 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다. 
 * Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.
 * 
 */
@Using(algorithm = Algorithm.BACK_TRACKING)
public class P14889 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] S;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = new int[N][N];
        for (int i=0; i<N; i++)
            S[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int sum = pick(0, new Stack<>());
        System.out.println(sum);
    }

    private static int pick(int i, Stack<Integer> teamA) {
        if (i == N) {
            if (teamA.size() != N/2) 
                return Integer.MAX_VALUE;
            Stack<Integer> teamB = new Stack<>();
            for (int j=0; j<N; j++)
                if (!teamA.contains(j))
                    teamB.add(j);
            Pair[] pairs = pairs(teamA);
            int sumA = 0;
            for (Pair p: pairs)
                sumA += S[p.x][p.y];
            int sumB = 0;
            pairs = pairs(teamB);
            for (Pair p: pairs)
                sumB += S[p.x][p.y];
            return Math.abs(sumA-sumB);
        }
        if (teamA.size() >= N/2)    //backtracking
            return Integer.MAX_VALUE;
        teamA.push(i);
        int x = pick(i+1, teamA);
        if (x == 0) return 0;   //backtracking
        teamA.pop();
        x = Math.min(x, pick(i+1, teamA));
        return x;
    }

    private static class Pair {
        final int x, y;

        public Pair(int x, int y) {
            assert x != y;
            this.x = x;
            this.y = y;
        }
    }
    
    private static Pair[] pairs(Collection<Integer> c) {
        Pair[] pairs = new Pair[c.size() * (c.size()-1)];
        int i = 0;
        for (int e1: c)
            for (int e2: c)
                if (e1 != e2)
                    pairs[i++] = new Pair(e1, e2);
        return pairs;
    }
}
