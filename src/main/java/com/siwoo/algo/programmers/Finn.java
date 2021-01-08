package com.siwoo.algo.programmers;

public class Finn {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(15));
    }
    
    static class Solution {
        public int solution(int n) {
            int i = 1, j = 1, cnt = 0, sum = 0;
            while (j <= n) {
                if (sum == n)
                    cnt++;
                if (sum <= n) {
                    sum += i;
                    i++;
                } else {
                    sum -= j;
                    j++;
                }
            }
            return cnt;
        }
    }
}
