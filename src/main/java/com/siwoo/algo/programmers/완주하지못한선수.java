package com.siwoo.algo.programmers;

import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] p = {"leo", "kiki", "eden"},
                c = {"eden", "kiki"};
        System.out.println(solution.solution(p, c));
    }
    
    static class Solution {
        
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> cnt = new HashMap<>();
            for (String s: participant) {
                cnt.putIfAbsent(s, 0);
                cnt.put(s, cnt.get(s) + 1);
            }
            for (String s: completion) {
                cnt.put(s, cnt.get(s) - 1);
                if (cnt.get(s) == 0)
                    cnt.remove(s);
            }
            for (String s: cnt.keySet())
                return s;
            throw new AssertionError();
        }
    }
}
