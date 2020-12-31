package com.siwoo.algo.paradigm.string;


public interface PatternSearch {

    Iterable<Integer> search(String s, String p);


    static void main(String[] args) {
        String S = "ABACADABRAC",
                M = "ABRA";
        PatternSearch ps = new BruteForcePatternSearch();
        for (int i: ps.search(S, M))
            System.out.println(S.substring(i, i+M.length()));
    }
}
