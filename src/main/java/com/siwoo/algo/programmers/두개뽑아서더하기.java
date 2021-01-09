package com.siwoo.algo.programmers;

import java.util.*;

public class 두개뽑아서더하기 {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[]{2, 1, 3, 4, 1})));
        solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[]{5, 0, 2, 7})));
    }
    
    static class Solution {
        private int N, M = 2;
        private int[] numbers;
        private Set<Integer> visit = new HashSet<>();
        
        public int[] solution(int[] numbers) {
            this.numbers = numbers;
            N = numbers.length;
            PriorityQueue<Integer> pq = 
                    solution(0, new Stack<>(), new PriorityQueue<>());
            int[] a = new int[pq.size()];
            for (int i=0; i<a.length; i++)
                a[i] = pq.poll();
            return a;
        }

        private PriorityQueue<Integer> solution(int index, 
                                                Stack<Integer> stack, 
                                                PriorityQueue<Integer> pq) {
            if (stack.size() == M) {
                int sum = 0;
                for (int e: stack)
                    sum += e;
                if (!visit.contains(sum)) {
                    pq.add(sum);
                    visit.add(sum);
                }
                return pq;
            }
            for (int i=index; i<N; i++) {
                stack.push(numbers[i]);
                solution(i+1, stack, pq);
                stack.pop();
            }
            return pq;
        }
    }
}
