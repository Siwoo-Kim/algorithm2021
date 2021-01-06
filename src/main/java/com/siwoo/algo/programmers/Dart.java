package com.siwoo.algo.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dart {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("1S2D*3T"));
        System.out.println(solution.solution("1D2S#10S"));
        System.out.println(solution.solution("1D2S0T"));
        System.out.println(solution.solution("1S*2T*3S"));
        System.out.println(solution.solution("1D#2S*3S"));
        System.out.println(solution.solution("1T2D3D#"));
        System.out.println(solution.solution("1D2S3T*"));
    }

    private static class Solution {
        
        public int solution(String s) {
            List<Score> scores = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+[SDT\\*\\#]+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                Score score = new Score(s.substring(matcher.start(), matcher.end()));
                scores.add(score);
            }
            for (int i=0; i<scores.size(); i++) {
                if (scores.get(i).star()) {
                    scores.get(i).score *= 2;
                    if (i != 0)
                        scores.get(i-1).score *= 2;
                } else if (scores.get(i).achar())
                    scores.get(i).score = -scores.get(i).score;
            }
            int sum = 0;
            for (Score score: scores)
                sum += score.score;
            return sum;
        }
        
        private class Score {
            final String s;
            int score, p;
            
            public Score(String s) {
                this.s = s;
                score = s.charAt(0) - '0';
                if (Character.isDigit(s.charAt(1))) {
                    score *= 10;
                    p = s.charAt(2);
                } else
                    p = s.charAt(1);
                if (p == 'D')
                    score = score * score;
                else if(p == 'T')
                    score = score * score * score;
            }
            
            public boolean star() {
                return s.length() == 3 && s.charAt(2) == '*';
            }
            
            public boolean achar() {
                return s.length() == 3 && s.charAt(2) == '#';
            }
        }
    }
}
