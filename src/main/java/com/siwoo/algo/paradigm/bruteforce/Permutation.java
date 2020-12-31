package com.siwoo.algo.paradigm.bruteforce;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 순열 - 순서에 의미가 있으며 원소를 선택하는 경우의 수.
 * 
 * 배열 a 에 대한 중복이 없는 순열, 중복이 있는 순열.
 *  중복이 없는 순열 - 같은 원소 a[i] 을 한번만 선택하여 경우의 수를 생성.
 *  중복이 있는 순열 - 같은 원소 a[i] 을 중복 선택하여 경우의 수를 생성.
 */
public class Permutation<E> {

    /**
     * 중복이 없는 순열.
     * 
     * @param N
     * @param M
     * @param elements
     * @return
     */
    public Collection<E[]> permutation(int N, int M, E[] elements) {
        checkArgument(N >= M);
        checkNotNull(elements);
        return permutation(N, M, elements, 
                new HashSet<>(),
                new Stack<>(), 
                new ArrayList<>());
    }

    private Collection<E[]> 
        permutation(int N, int M, E[] elements, 
                    Set<E> visit,
                    Stack<E> stack, 
                    List<E[]> result) {
        if (stack.size() == M) {
            @SuppressWarnings("unchecked")
            E[] copy = (E[]) new Object[M];
            stack.copyInto(copy);
            result.add(copy);
            return result;
        }
        for (int i=0; i<N; i++) {
            if (!visit.contains(elements[i])) {
                visit.add(elements[i]);
                stack.push(elements[i]);
                permutation(N, M, elements, visit, stack, result);
                stack.pop();
                visit.remove(elements[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Permutation<Integer> p = new Permutation<>();
        Integer[] elements = new Integer[4];
        for (int i=0; i<elements.length; i++)
            elements[i] = i+1;
        Collection<Integer[]> selected =  p.permutation(elements.length, 2, elements);
        for (Object[] integers: selected)
            System.out.println(Arrays.toString(integers));
    }
}
