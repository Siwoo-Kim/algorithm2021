package com.siwoo.algo.programmers;

import java.util.Arrays;

public class BinaryString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution("001")));
        System.out.println(Arrays.toString(solution.solution("01110")));
        System.out.println(Arrays.toString(solution.solution("1111111")));
    }
    
    static class Solution {
        public int[] solution(String s) {
            return binary(s);
        }

        private int[] binary(String s) {
            int cnt = 0;
            for (int c: s.toCharArray()) {
                if (c == '0')
                    cnt++;
            }
            int value = s.length()-cnt;
            StringBuilder sb = new StringBuilder();
            while (value > 0) {
                if (value % 2 != 0)
                    sb.append(1);
                else
                    sb.append(0);
                value /= 2;
            }
            if (sb.toString().equals("1")) {
                return new int[]{1, cnt};
            }
            sb = sb.reverse();
            int[] result = binary(sb.toString());
            result[0] += 1;
            result[1] += cnt;
            return result;
        }
    }
}
