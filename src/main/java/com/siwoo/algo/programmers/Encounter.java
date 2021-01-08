package com.siwoo.algo.programmers;

public class Encounter {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(10, 4, 7));
    }
    
    private static class Solution {
        public int solution(int n, int a, int b) {
            return dnq(n, a-1, b-1);
        }

        private int dnq(int n, int a, int b) {
            if (a == b) return 0;
            return dnq(n / 2, a / 2, b / 2) + 1;
        }
    }
}
