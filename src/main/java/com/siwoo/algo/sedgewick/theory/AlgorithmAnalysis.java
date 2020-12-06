package com.siwoo.algo.sedgewick.theory;

import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

/**
 * 알고리즘 분석.
 *  
 *  알고리즘 a 는
 *      얼마나 시간이 걸릴까?
 *      얼만큼의 공간을 사용할까?
 *  
 *  과학적 모델 scientific method
 *      observe - 자연 세계의 요소를 관찰.
 *      hypothesize  - 관찰을 통한 가설.
 *      predict - 가설을 이용해 사건을 예측 
 *      verify  - 가설을 통한 예측이 맞는지 확인.
 *      validate    - 위의 과정 (가설 수립, 관찰) 을 반복하여 검증
 *  
 *  수학적 모델 mathematical models.
 *      cost of statement - 각 구문의 실행 비용 (운영 체제와 컴파일러에 종속적)
 *      frequency of execution of statement - 각 구문의 실행 빈도 (코드에 종속)
 *      
 *      -> 대부분은 문제의 값 보다는 문제의 크기가 알고리즘 a 의 성능에 영향을 끼친다.
 *      
 *  틸다 근사 tilde approximations
 *      알고리즘의 빈도 분석 후 작은 항목을 제거하여 다루기 쉬운 형태로 수학적 모델을 단순화.
 *      
 *      대표적 증가 오더 함수.
 *          1   constant
 *          logN    logarithmic = 밑은 생략한다. 왜냐면 상수 밑을 가진 알고리즘은 상수 크기만큼 차이가 난다.
 *          N   linear
 *          NlogN   linearithmic = 밑은 생략한다. 왜냐면 상수 밑을 가진 알고리즘은 상수 크기만큼 차이가 난다.
 *          N^2, N^3 ...    quadratic, cubic
 *          2^N exponential
 *      
 *      이러한 증가 오더를 사용하면 하드웨어 종속되지 않은 알고리즘 분석이 가능.
 *
 *  비용 모델 cost model
 *      알고리즘 a 의 기본 동작이 어떤 비용 모델을 가지는지 고찰.
 *      ex) count 의 비용 모델은 배열에 대한 접근 횟수를 헤아린다.
 *      
 *      명제 = 사실 혹은 거짓인지 판별할 수 있는 선언.
 *      
 *      명제 - count 은 N 개의 숫자 중 합계가 0 인 트리플의 갯수를 구하기 위해 ~N^3 번 배열에 접근한다.
 *      증명 - count 은 ~N^3/6 개의 트리플 각각의 숫자 3개에 접근한다.
 *
 *   수학적 모델을 만드는 과정.
 *      1. 문제 크기에 대한 정의와 더붙어서 입력 모델을 만든다.
 *      2. 내부 루프를 찾아 구분.
 *      3. 내부 루프의 작업을 포함해 비용 모델을 정의
 *      4. 주어진 입력에 따라, 작업들의 실행 빈도를 파악한다.
 *      
 *  알고리즘에서 사용하는 흔한 함수.
 *      lnN
 *      lgN
 *      Hn  = 1 + 1/2 + 1/3 + 1/4 .. 1/N
 *      N!
 */
public class AlgorithmAnalysis {
    
    public static int count(int[] a) {
        int N = a.length;   // O(N^3)
        int cnt = 0;
        for (int i=0; i<N; i++)     //N^3
            for (int j=i+1; j<N; j++)       //N^2
                for (int k=j+1; k<N; k++)   //N
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }
    
    public static int countSelect(int[] a) {
        return select(a, 0, new Stack<>()); // O(2^N)
    }
    
    public static int countCombination(int[] a) {
        return combination(a, 0, 0, 0); // O(NC3)
    }
    
    private static int countFaster(int[] a) {   // n^2logN = 현존하는 three sum 중 가장 빠른 알고리즘
        int N = a.length;
        Arrays.sort(a);
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                if (binarySearch(-(a[i] + a[j]), a) > j) // 중복을 헤아리지 않기 위해 뒤의 요소들만 적용한다.
                    cnt++;
            }
        }
        return cnt;
    }

    private static int binarySearch(int x, int[] a) {
        return binarySearch(0, a.length-1, x, a);
    }

    private static int binarySearch(int left, int right, int x, int[] a) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (x < a[mid])
            return binarySearch(left, mid-1, x, a);
        else if (x > a[mid])
            return binarySearch(mid+1, right, x, a);
        return mid;
    }

    private static int select(int[] a, int index, Stack<Integer> stack) {
        if (stack.size() == 3) {
            int sum = 0;
            for (int e: stack)
                sum += a[e];
            return sum == 0? 1: 0;
        }
        if (stack.size() > 3 || index == a.length) return 0;
        stack.push(index);
        int cnt = select(a, index+1, stack);
        stack.pop();
        return cnt + select(a, index+1, stack);
    }

    private static int combination(int[] a, int index, int sum, int cnt) {
        if (cnt == 3) return sum == 0? 1: 0;
        int r = 0;
        for (int i=index; i<a.length; i++)
            r += combination(a, i+1, sum+a[i], cnt+1);
        return r;
    }

    public static void main(String[] args) {
        String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data");
        //1Kints = 70
        //2Kints = 528
        //4Kints = 4039
//        In in = new In(path + "/4Kints.txt");
//        int[] a = in.readAllInts();
//        System.out.println(a.length);
//        Stopwatch timer = new Stopwatch();
//        int cnt = countFaster(a);
//        double time = timer.elapsedTime();
//        System.out.println(cnt + " triples " + time + " seconds");
        
        for (int N=250; N<=4000; N+=N) {
            double time = timeTrial(N, AlgorithmAnalysis::count);
            System.out.printf("%7d %.5f\n", N, time);
        }
        System.out.println("===========================");
        for (int N=250; N<=1000; N+=N) {    //2^1000 
            double time = timeTrial(N, AlgorithmAnalysis::countSelect);
            System.out.printf("%7d %.5f\n", N, time);
        }
        System.out.println("===========================");
        for (int N=250; N<=4000; N+=N) {
            double time = timeTrial(N, AlgorithmAnalysis::countCombination);
            System.out.printf("%7d %.5f\n", N, time);
        }
        System.out.println("===========================");
        for (int N=250; N<=4000; N+=N) {
            double time = timeTrial(N, AlgorithmAnalysis::countFaster);
            System.out.printf("%7d %.5f\n", N, time);
        }
    }
    
    public static double timeTrial(int N, Function<int[], Integer> algo) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i=0; i<N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = algo.apply(a);
        return timer.elapsedTime();
    }
}
