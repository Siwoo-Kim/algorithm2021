package com.siwoo.algo.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

/**
 * 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열  A가 있다. 
 * 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.
 * 
 * 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다.
 */
public class P2529 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static String[] S;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = reader.readLine().split("\\s+");
        Integer[] a = new Integer[10];
        for (int i=0; i<=9; i++)
            a[i] = i;
        Permutation<Integer> p = new Permutation<>(a, Integer::compare);
        StringBuilder min = null;
        do {
            if (check(S, p)) {
                min = new StringBuilder();
                for (int i=0; i<=N; i++)
                    min.append(p.a[i]);
                break;
            }
        } while (p.next());
        for (int i=0; i<=9; i++)
            a[i] = 9-i;
        p = new Permutation<>(a, (x, y) -> Integer.compare(y, x));
        StringBuilder max = null;
        do {
            if (check(S, p)) {
                max = new StringBuilder();
                for (int i=0; i<=N; i++)
                    max.append(p.a[i]);
                break;
            }
        } while (p.next());
        System.out.println(max);
        System.out.println(min);
    }

    private static boolean check(String[] s, Permutation<Integer> p) {
        Integer[] a = p.a;
        for (int i=0; i<s.length; i++) {
            String op = s[i];
            if (op.equals("<") && a[i] > a[i+1])
                return false;
            if (op.equals(">") && a[i] < a[i+1])
                return false;
        }
        return true;
    }

    private static class Permutation<E extends Comparable<E>> {
        private final E[] a;
        private final Comparator<E> c;
        
        public Permutation(E[] a, Comparator<E> c) {
            this.a = a;
            this.c = c;
        }

        public boolean next() {
            int i = a.length-1;
            while (i != 0 && c.compare(a[i-1], a[i]) >= 0)
                i--;
            if (i == 0) return false;
            int j = a.length-1;
            while (c.compare(a[i-1], a[j]) >= 0) j--;
            swap(i-1, j, a);
            j = a.length-1;
            while (i < j)
                swap(i++, j--, a);
            return true;
        }

        private void swap(int i, int j, E[] a) {
            E e = a[i];
            a[i] = a[j];
            a[j] = e;
        }
    }
}
