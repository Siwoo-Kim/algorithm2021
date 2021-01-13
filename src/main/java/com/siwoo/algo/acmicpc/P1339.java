package com.siwoo.algo.acmicpc;

import com.siwoo.algo.paradigm.math.Permutation;
import com.siwoo.algo.util.Algorithm;
import com.siwoo.algo.util.Using;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Using(algorithm = Algorithm.PERMUTATION)
public class P1339 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        String[] words = new String[N];
        for (int i=0; i<N; i++) {
            words[i] = reader.readLine();
        }
        Set<Character> set = new HashSet<>();
        for (String word: words)
            for (char c: word.toCharArray())
                set.add(c);
        Character[] chars = set.toArray(new Character[0]);
        M = chars.length;
        int[] indexes = new int[M];
        for (int i=0; i<M; i++)
            indexes[i] = i;
        int min = 10-M, max = 0;
        do {
            int sum = 0;
            Map<Character, Integer> mapValue = new HashMap<>();
            for (int i=0; i<indexes.length; i++)
                mapValue.put(chars[indexes[i]], min+i);
            for (String word: words) {
                int k = 0;
                for (char c: word.toCharArray())
                    k = k * 10 + mapValue.get(c);
                sum += k;
            }
            max = Math.max(sum, max);
        } while (nextPermutation(indexes));
        System.out.println(max);
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length-1;
        while (i>0 && a[i-1] >= a[i]) i--;
        if (i == 0) return false;
        int j = a.length-1;
        while (a[i-1] >= a[j]) j--;
        swap(i-1, j, a);
        j = a.length-1;
        while (j>i)
            swap(i++, j--, a);
        return true;
    }

    private static void swap(int i, int j, int[] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
