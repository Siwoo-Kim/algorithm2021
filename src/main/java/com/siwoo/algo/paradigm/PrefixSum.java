package com.siwoo.algo.paradigm;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * 누적합 문제.
 *  배열 a 의 구간 i..j 에 대한 m 개의 쿼리 문제.
 *  
 *  누적합 문제 알고리즘.
 *      1. segment tree.
 *      2. fenwick tree.
 *      3. dynamic programming
 *
 *  누적합을 위한 다이나믹 프로그래밍.
 *      s[i] 을 0..i 까지의 누적합이라 정의하자.
 *      
 *      s[i] = s[i-1] + a[i]
 *      query(i, j) 은 s[j] - s[i-1]
 *      
 *      다이나믹 프로그래밍의 한계.
 *          a[i] 의 값이 변경되면 s[i] 이후의 값은 다시 업데이트 되어야 한다.
 *          
 */
public abstract class PrefixSum {
    int[] a;

    public PrefixSum(int[] a) {
        this.a = a;
    }

    public abstract int query(int i, int j);
    
    private static class DP extends PrefixSum {
        private int[] s;
        
        public DP(int[] a) {
            super(a);
            s = new int[a.length];
            s[0] = a[0];
            for (int i=1; i<a.length; i++)
                s[i] = s[i-1] + a[i];
        }

        @Override
        public int query(int i, int j) {
            checkElementIndex(i, a.length);
            checkElementIndex(j, a.length);
            return s[j] - (i == 0? 0: s[i-1]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/prefixsum.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        int N = scanner.nextInt(),
                M = scanner.nextInt();
        scanner.nextLine();
        int[] a = new int[N];
        for (int i=0; i<N; i++)
            a[i] = scanner.nextInt();
        PrefixSum prefixSum = new DP(a);
        scanner.nextLine();
        for (int i=0; i<M; i++) {
            int from = scanner.nextInt(),
                    to = scanner.nextInt();
            System.out.println(prefixSum.query(from-1, to-1));
        }
    }
}
