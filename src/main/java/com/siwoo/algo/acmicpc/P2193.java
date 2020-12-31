package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.util.Scanner;

/**
 * 0과 1로만 이루어진 수를 이진수라 한다. 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 이친수는 다음의 성질을 만족한다.
 *
 * 이친수는 0으로 시작하지 않는다.
 * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
 * 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
 *
 * N(1 ≤ N ≤ 90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
 * 
 * D[n]{zero, one} 을 n 개의 길이의 이친수의 갯수와 마지막 n 번째에 놓을 숫자 0, 1 이라 가정한다면
 *  D[n].zero += D[n-1].one + D[n-1].zero
 *  D[n].one += D[n-1].zero // 연속된 1은 계산하지 않는다.
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P2193 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 90;
    private static long[][] D = new long[MAX+1][2];

    public static void main(String[] args) {
        D[0][0] = 1;
        dp(1);
        int N = scanner.nextInt();
        System.out.println(D[N][1]);
    }

    private static void dp(int i) {
        if (i > MAX) return;
        D[i][1] = D[i-1][0];
        D[i][0] = D[i-1][0] + D[i-1][1];
        dp(i+1);
    }

}
