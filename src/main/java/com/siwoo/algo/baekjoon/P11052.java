package com.siwoo.algo.baekjoon;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;
import org.checkerframework.checker.guieffect.qual.UI;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 요즘 민규네 동네에서는 스타트링크에서 만든 PS카드를 모으는 것이 유행이다.
 *
 * 민규는 카드의 개수가 적은 팩이더라도 가격이 비싸면 높은 등급의 카드가 많이 들어있을 것이라는 미신을 믿고 있다. 따라서, 
 * 민규는 돈을 최대한 많이 지불해서 카드 N개 구매하려고 한다. 
 * 카드가 i개 포함된 카드팩의 가격은 Pi원이다.
 *
 *  D[n] 이 n 개의 카드를 선택했을 때, 최대 금액이라 한다면
 *  
 *  1 <= i <= n
 *  D[n] = max(D[n-i]+P[i])
 *      카드 i 개 만큼 산다면, 이전 선택의 최대 값은 N-i 이다.
 *      
 *      반드시 N 의 만큼의 카드를 구매해야 하기 때문에 결과는 하나밖에 나올 수 없다.
 *
 * 카드 팩의 가격이 주어졌을 때, N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값을 구하는 프로그램을 작성하시오.
 */
@Using(algorithm = Algorithm.DYNAMIC_PROGRAMMING)
public class P11052 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static int[] P;
    private static Long[] D;

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        P = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Long[N+1];
        long max = dp(N);
        System.out.println(max);
    }

    private static long dp(int n) {
        if (D[n] != null) return D[n];
        long max = 0;
        for (int i=1; i<=n; i++) 
            max = Long.max(max, dp(n-i) + P[i-1]);
        return D[n] = max;
    }
}
