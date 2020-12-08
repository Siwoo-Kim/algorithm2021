package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * f(FULL, HALF, day) 을 day 일에 약 한 조각과 약 반조각의 갯수라 가정한다면,
 *  
 *  f(FULL, HALF, day) = 
 *      한 조각 약을 먹은 경우 c1
 *          f(FULL-1, HALF+1, day-1)    (FULL > 0)
 *      반 조각 약을 먹은 경우 c2
 *          f(FULL, HALF-1, day-1) (HALF > 0)
 *      단, FULL 이 0 이라면 1    
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P4811 {
    private static Scanner scanner = new Scanner(System.in);
    private static Long[][] D;

    public static void main(String[] args) {
        while (true) {
            int N = scanner.nextInt();
            if (N == 0) return;
            D = new Long[(N*2)+1][(N*2)+1];
            long x = takeMedicine(N, 0, N*N);
            System.out.println(x);
        }
    }

    private static long takeMedicine(int full, int half, int day) {
        if (day == 0 || full == 0) return D[full][half] = 1L;
        if (D[full][half] != null) return D[full][half];
        long cnt = takeMedicine(full-1, half+1, day-1);
        if (half > 0)
            cnt += takeMedicine(full, half-1, day-1);
        return D[full][half] = cnt;
    }
}
