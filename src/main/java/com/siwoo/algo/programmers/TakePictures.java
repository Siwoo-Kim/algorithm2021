package com.siwoo.algo.programmers;

import java.util.*;

public class TakePictures {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] s = {"N~F=0", "R~T>2"};
        System.out.println(solution.solution(2, s));
    }
    static class Solution {
        private final char[] A = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        
        public int solution(int N, String[] data) {
            Map<Character, List<Rule>> rules = new HashMap<>();
            Map<Character, Set<Character>> dep = new HashMap<>();
            for (int i=0; i<N; i++) {
                Rule rule = new Rule(data[i]);
                rules.putIfAbsent(rule.v, new ArrayList<>());
                rules.get(rule.v).add(rule);
                dep.putIfAbsent(rule.v, new HashSet<>());
                dep.get(rule.v).add(rule.w);
            }
            int cnt = 0;
            do {
                boolean ok = true;
                for (int i=0; i<A.length; i++) {
                    for (int j=0; j<A.length; j++) {
                        if (i == j) continue;
                        if (rules.containsKey(A[i])) {
                            for (Rule r: rules.get(A[i])) {
                                if (r.v == A[i] 
                                        && r.w == A[j]) {
                                    if (!r.check(A[i], A[j],  Math.abs(i-j)-1)) {
                                        ok = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (!ok) break;
                    }
                    if (!ok) break;
                }
                if (ok) cnt++;
            } while (nextPermutation(A));
            return cnt;
        }

        private boolean nextPermutation(char[] A) {
            int i = A.length-1;
            while (i>0 && A[i-1] >= A[i])
                i--;
            if (i == 0) return false;
            int j = A.length-1;
            while (A[i-1] >= A[j])
                j--;
            swap(i-1, j, A);
            j = A.length-1;
            while (i<j)
                swap(i++, j--, A);
            return true;
        }

        private void swap(int i, int j, char[] A) {
            char c = A[i];
            A[i] = A[j];
            A[j] = c;
        }

        private static class Rule {
            private char v, w, op;
            private int dist;

            public Rule(String s) {
                this.v = s.charAt(0);
                this.w = s.charAt(2);
                this.op = s.charAt(3);
                dist = Integer.parseInt(Character.toString(s.charAt(4)));
            }

            public boolean check(char v, char w, int d) {
                if (op == '=')
                    return dist == d;
                if (op == '<')
                    return dist > d;
                if (op == '>')
                    return dist < d;
                return false;
            }
        }
    }
}
