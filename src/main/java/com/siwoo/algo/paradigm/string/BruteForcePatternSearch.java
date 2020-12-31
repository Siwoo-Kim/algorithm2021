package com.siwoo.algo.paradigm.string;

import com.siwoo.algo.sedgewick.collection.LinkedList;
import com.siwoo.algo.sedgewick.collection.Stack;

public class BruteForcePatternSearch implements PatternSearch {
    
    @Override
    public Iterable<Integer> search(String s, String p) {
        int N = s.length();
        int M = p.length();
        Stack<Integer> stack = new LinkedList<>();
        for (int i=0; i<=N-M; i++) {
            int j = 0;
            for (; j<M; j++)
                if (s.charAt(i+j) != p.charAt(j))
                    break;
            if (j == M)
                stack.push(i);
        }
        return stack;
    }

}
