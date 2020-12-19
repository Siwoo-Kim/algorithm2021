package com.siwoo.algo.paradigm.sort;

import com.siwoo.algo.util.AppConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * [algo] [lsd sort for string]
 *      lsd 문자열 정렬은 "고정 크기 문자열" 들을 안정 정렬한다.
 *      
 * problem
 *  * 고정 길이 w 을 가지는 문자열 s 에 대해서 정렬하고 싶다.
 *
 * components
 *  1. key-index counting (빈도 수 계산)
 *      1-1. 문자 c 에 대해 등장 횟수만큼 빈도수를 증가. count[c+1]++
 *          s 의 문자 c 에 대해 count[c+1] 을 증가. ( c 은 count[c+1] 에 빈도수를 저장한다)
 *          
 *      1-2. 문자 c 의 등장 횟수의 합계를 c+1 에 합산. count[c+1] += count[c]
 *          어떤 문자 c 에 정렬된 순서에서 처음 등장하는 순서는 그 키보다 작은 키들의 등장 빈도를 헤아려 더한다.
 *        
 *        eg) 문자가 '1' 의 빈도 수가 2 개 (0-1), '2'인 빈도 수가 3 개라면 (2-4 = '2' 보다 작은 c 의 합계는 2)
 *          정렬된 순서에서의 '3' 의 첫 등장 위치는 5 가 된다. (5~ = '3' 보다 작은 c 의 합계는 5)
 *          
 *        이 것을 일반화하면, 각각의 문자 r 에 대해서, 
 *          r 의 정렬된 순서에서 첫 등장 위치는 r+1 보다 작은 키 값들의 등장 빈도 합계이다.
       
 *      
 *  2. distribution (빈도 수를 인덱스로 변환)
 *      동등 키 c 에 대한 인덱스 변환과 다음 인덱스 계산.
 *          위의 1 작업으로 c 에 대한 정렬된 순서에서 첫 등장 위치를 구했으므로
 *          인덱스를 가져오고 동일 c 에 대한 다음 인덱스를 증.
 *          
 *          aux[count[c]++]
 *      
 *  algorithm.
 *      1. lsd 
 *          오른쪽에서 왼쪽 방향의 i 에 대해 문자 c 에 대해 정렬을 시도한다.
 *          
 *      2. key-index counting. 
 *          복수의 문자열 s 에 대해 i 번째 문자 c 빈도 수 (c+1 에 저장한다) 를 계산한다.
 *          이후 c 보다 작은 문자들의 빈도 수의 합계로 c 의 인덱스를 계산한다.
 *      
 *      3. distribution
 *          c 의 첫 등장 위치를 알았으므로 c 을 정렬 배열에 추가하며, 
 *          동시에 다른 문자열이면서 같은 문자들을 위해 count[c] 을 증가시켜 준다.
 *  
 *      lsd 은 안전 정렬이므로 i = 0 일때까지 진행하면 모든 문자열은 정렬.
 *      
 *  time complexity
 */
public class LSDStringSort implements Sort<String> {

    private final int RADIX = Character.MAX_VALUE;
    
    /**
     * sort the elements which have a fixed width.
     * 
     * @param elements
     * @param W width
     */
    public void sort(String[] elements, int W) {
        for (String s: elements)
            if (s.length() != W)
                throw new IllegalArgumentException();
        for (int w=W-1; w>=0; w--) { //lsd
            int[] count = new int[RADIX];
            for (int i=0; i<elements.length; i++) { // 빈도 수 계산
                char c = elements[i].charAt(w);
                count[c+1]++;
            }
            for (int i=0; i<RADIX-1; i++)   // 첫 등장 위치 계산
                count[i+1] += count[i];
            String[] aux = new String[elements.length];
            for (int i=0; i<elements.length; i++) {
                char c = elements[i].charAt(w);
                aux[count[c]++] = elements[i];
            }
            System.arraycopy(aux, 0, elements, 0, elements.length);
        }
    }
    
    @Override
    public void sort(String[] elements) {
        if (elements.length <= 1) return;   //no elements to sort
        sort(elements, elements[0].length());
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/lcd.txt";
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        String[] s = new String[Integer.parseInt(scanner.nextLine())];
        for (int i=0; i<s.length; i++)
            s[i] = scanner.nextLine();
        Sort<String> sort = new LSDStringSort();
        sort.sort(s);
        for (String e: s)
            System.out.println(e);
    }
}
