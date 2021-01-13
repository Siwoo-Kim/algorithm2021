package com.siwoo.algo.acmicpc;

import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열  A가 있다. 
 * 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다
 */
@Using(algorithm = Algorithm.PERMUTATION)
public class P2529 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        String[] signs = new String[N];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            signs[i] = token.nextToken();
        int[] a = IntStream.rangeClosed(0, N).toArray();
        StringBuilder min = new StringBuilder(),
                max = new StringBuilder();
        do {
            if (ok(a, signs)) {
                for (int e: a)
                    min.append(e);
                break;
            }
        } while (nextPermutation(a));
        a = IntStream.rangeClosed(9-N, 9).toArray();
        for (int i=0; i<a.length/2; i++)
            swap(i, a.length-1-i, a);
        do {
            if (ok(a, signs)) {
                for (int e: a)
                    max.append(e);
                break;
            }
        } while (prevPermutation(a));
        System.out.println(max);
        System.out.println(min);
    }

    private static boolean prevPermutation(int[] a) {
        int i = a.length-1;
        while (i>0 && a[i-1] <= a[i])
            i--;
        if (i == 0) return false;
        int j = a.length-1;
        while (a[i-1] <= a[j]) j--;
        swap(i-1, j, a);
        j = a.length-1;
        while (j > i)
            swap(i++, j--, a);
        return true;
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length-1;
        while (i>0 && a[i-1] >= a[i])
            i--;
        if (i == 0) return false;
        int j = a.length-1;
        while (a[i-1] >= a[j]) j--;
        swap(i-1, j, a);
        j = a.length-1;
        while (j > i)
            swap(i++, j--, a);
        return true;
    }

    private static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean ok(int[] a, String[] signs) {
        for (int i=1; i<a.length; i++) {
            if ("<".equals(signs[i-1])) {
                if (!(a[i-1] < a[i]))
                    return false;
            } else {
                if (!(a[i-1] > a[i]))
                    return false;
            }
        }
        return true;
    }
}
