package com.siwoo.algo.paradigm.bruteforce;

import java.util.*;

/**
 * 조합 - 순서와 관계 없이 원소를 선택하는 경우의 수.
 * 
 * 중복이 없는 조합, 중복이 있는 조합.
 *  중복이 없는 조합 - 같은 원소 a[i] 을 한번만 선택하여 경우의 수를 생성.
 *  중복이 있는 조합 - 같은 원소 a[i] 을 중복 선택하여 경우의 수를 생성.
 *  
 * @param <E>
 */
public class Combination<E> {
    
    public Collection<E[]> combination(int N, int M, E[] elements) {
//        return combination(0, N, M, elements, new Stack<>(), new ArrayList<>());
        return select(0, N, M, elements, new Stack<>(), new ArrayList<>());
    }

    private Collection<E[]> select(int index, int N, int M, E[] elements, Stack<E> stack, ArrayList<E[]> result) {
        if (stack.size() == M) {
            @SuppressWarnings("unchecked")
            E[] select = (E[]) new Object[M];
            stack.copyInto(select);
            result.add(select);
            return result;
        }
        if (index == N) return result;
        stack.push(elements[index]);
        select(index+1, N, M, elements, stack, result);
        stack.pop();
        select(index+1, N, M, elements, stack, result);
        return result;
    }

    private Collection<E[]> combination(int index, int N, int M, E[] elements,
                                        Stack<E> stack, List<E[]> result) {
        if (stack.size() == M) {
            @SuppressWarnings("unchecked")
            E[] copy = (E[]) new Object[M];
            stack.copyInto(copy);
            result.add(copy);
            return result;
        }
        for (int i=index; i<N; i++) {
            stack.push(elements[i]);
            combination(i+1, N, M, elements, stack, result);
            stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        Combination<Integer> c = new Combination<>();
        Integer[] elements = new Integer[10];
        for (int i=0; i<elements.length; i++)
            elements[i] = i+1;
        Collection<Integer[]> selected =  c.combination(elements.length, 3, elements);
        for (Object[] integers: selected)
            System.out.println(Arrays.toString(integers));
    }
}
