package com.siwoo.algo.algospot;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.AppConfig;
import com.siwoo.algo.util.Using;
import org.checkerframework.checker.units.qual.C;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 4 * 4 격자판의 각 격자안에 시계가 주어지고
 * 각 시계는 12시, 3시, 6시, 9시 을 가리킬 때, 이 시계를 모두 12 시를 가리키려 한다.
 * 
 * 시계의 시간을 조작하는 스위치 (10개의) 는 하나 이상의 시계들과 연결되어 있고, 스위치를 누를 때마다 
 * 3시간씩 앞으로 움직인다고 할 때, 모든 시계를 12 시로 돌리기 위한 최소 스위치의 클릭 횟수를 구하여라.
 * 
 * 문제 특성.
 *      같은 스위치를 4 번 이상 누르는 것은 의미없다.
 */
@Using(algorithm = Algorithm.BRUTE_FORCE)
public class ClockSync {
    private static int[][] SWITCHES = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };
    
    public int[] clocks;
    private int answer;

    public ClockSync(int[] clocks) {
        this.clocks = clocks;
        answer = sync(0);
    }

    private int sync(int s) {
        if (s == SWITCHES.length) {
            return isSync()? 0: Integer.MAX_VALUE;
        }
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<4; i++) {
            int tmp = sync(s+1);
            if (tmp != Integer.MAX_VALUE)
                answer = Math.min(answer, tmp + i);
            click(clocks, s);
        }
        return answer;
    }

    private boolean isSync() {
        return Arrays.stream(clocks).allMatch(e -> e == 12);
    }

    private boolean click(int[] clocks, int s) {
        for (int c: SWITCHES[s]) {
            clocks[c] += 3;
            if (clocks[c] == 15)
                clocks[c] = 3;
        }
        for (int c: clocks)
            if (c != 12)
                return false;
        return true;
    }
    
    private int answer() {
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/clocksync.txt";
        Scanner scanner = new Scanner(new FileInputStream(path));
        int T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            int[] clock = new int[4*4];
            for (int i=0; i<clock.length; i++)
                clock[i] = scanner.nextInt();
            ClockSync clockSync = new ClockSync(clock);
            System.out.println(clockSync.answer());
        }
    }
}
