package com.siwoo.algo.paradigm.sort;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * [algo] [msd sort for string]
 *  
 * problem
 *  * key-index counting 방식으로 서로 다른 길이를 가진 문자여들에 대해서 정렬하고 싶다.
 *
 * components
 *  1. key-index counting. 
 *      기본적인 알고리즘은 {@link LSDStringSort} 와 동일.
 *      단, 이미 끝난 문자열을 처리하기 위해서 count[0] 에 그러한 문자을 넣어주고
 *      이후 만자들에 대해서 한자리씩 뒤에 밀어낸다.
 *      
 *  2. divide and conquer (based on msd).
 *      최상위 자리 (msd) 기준으로 재귀적으로 정렬.
 *      
 *  algorithm.
 *      1. 현재 자리 수 msd 에 대하여 정렬을 수행한다.
 *      2. 빈도 헤아리기.
 *      3. 카운트를 인덱스로 변환.
 *      4. distribution
 *      5. 각 정렬된 키 기준으로 재귀적으로 호출 이때 msd 은 1 증가.
 *
 *
 *  time complexity
 *      *
 */
public class MSDStringSort implements Sort<String> {
    private static final int radix = 1<<8;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int index) {
        if (index < s.length())
            return s.charAt(index);
        else
            return -1;
    }

    @Override
    public void sort(String[] elements) {
        int N = elements.length;
        aux = new String[N];
        sort(elements, 0, N, 0);
    }

    private void sort(String[] s, int left, int right, int msd) {
        if (right - left <= 1) return;
        int[] count = new int[radix+2]; // 끝난 문자열을 위한 공간 확보
        for (int i=left; i<right; i++)  // 빈도 계산. 
            count[charAt(s[i], msd) + 2]++;
        for (int i=0; i<radix+1; i++)
            count[i+1] += count[i]; // 빈도를 인덱스로 변환.
        for (int i=left; i<right; i++)
            aux[count[charAt(s[i], msd)+1]++] = s[i];
        for (int i=left; i<right; i++)
            s[i] = aux[i-left];
        for (int i=0; i<radix; i++)
            // count[i] ~ count[i+1] is the boundary for given char
            // left+count[i] ~ left+count[i+1] is the boundary for current call.
            sort(s, left+count[i], left+count[i+1], msd+1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/msd.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        int N = Integer.parseInt(scanner.nextLine());
        String[] s = new String[N];
        for (int i=0; i<N; i++)
            s[i] = scanner.nextLine();
        Sort<String> sort = new MSDStringSort();
        sort.sort(s);
        for (String e: s)
            System.out.println(e);
    }
}
