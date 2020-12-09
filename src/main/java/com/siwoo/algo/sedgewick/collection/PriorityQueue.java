package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.paradigm.Sort;
import com.siwoo.algo.util.AppConfig;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Transaction;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * [heap] [PriorityQueue]
 *  우선순위 큐는 binary tree 로 모든 노드 v 가 두 자식보다 크거나 같다는 조건을 보장하는 자료구조.
 *  
 *  우선순위 큐의 가장 큰 키는 뿌리 노드이다.
 *      => 모든 노드 v 가 두 자식보다 크므로 귀납적으로 유도.
 *  
 * problem
 *  * 저장된 자료 중 가장 큰 (혹은 작은) 요소를 꺼내고 삭제와 동시에 동적 삽입시에도 논리적 순서가 유지되어야 한다..
 *  ex) 우선순위가 부여된 event 처리 시스템, 입력의 크기를 모르는 N 요소를 정렬시.
 *  
 * [heap] components
 *  1. root 은 항상 1 이다.
 *  2. 노드 k 의 부모는 k/2 이다.
 *  3. 노드 k 의 자식은 k*2, k*2+1 이다.
 *  4. 힙의 트리는 레벨순으로 배열에 나열된다.
 *  5. 힙의 트리의 높이는 logN 이다.
 *  6. 힙의 요소를 삭제, 삽입시 힙의 불변식을 만족하도록 작업하는 것을 heapifying 이라 한다.
 *  
 *  [heap] api.
 *      enqueue - insert element into the pq
 *      dequeue - delete min in the pq
 *
 *  [heap] heapifying. 
 *      swim
 *          어떤 노드 k 가 부모 노드 p 보다 커졌다면, k 와 p 을 교환한다.
 *              => k 가 p 의 왼쪽 자식이라 하자.
 *              p 의 right 은 p >= right 이고 k >= p 이므로 p 와 k 을 교환하더라도
 *              힙의 상태는 유지된다.
 *          교환 이후 k 은 여전히 새로운 p 보다 클 수 있으므로 반복 수행한다.
 *          
 *      sink
 *          어떤 노드 k 가 두 자식 중 하나보다 작다면 두 자식 중 작은 c 와 교환한다.
 *          k 와 c 을 교환한 이후에도 k 은 여전히 새로운 자식보다 작을 수 있으므로 
 *          힙의 조건을 만족할때까지 반복 수행한다.
 *          
 *  [algo] limitation
 *      키 값의 불변성이 지켜져야 힙 속성을 만족한다.
 *      인덱스 기반의 api 을 지원할 수 있다면 더 유연하게 사용 가능.
 *  
 *  [heap] 정렬.
 *      배열 a 을 힙 구성하여 힙에서 내림차순으로 항목을 꺼내어 정렬된 결과를 만듬.
 *      
 *      힙 구성. heap construction 
 *          오른쪽에서 부터 sink 을 이용해 부분 힙을 생성.
 *          모든 위치 i 에 대하여 sink 하여 두 노드를 부분 힙으로 만들었다면
 *          힙 속성이 완성. (중간부터 시작한다. 왜냐면 N/2 의 자식은 N/2*2, N/2*2+1 이며 제일 끝에 위치)
 *          
 *      정렬 취합. sort down
 *          가장 큰 요소 1 과 현재 힙의 가장 마지막 요소를 교환하고 sink 을 한 후 힙의 크기를 줄여가며 반복한다.
 *          
 *          힙의 속성에 의해 a[1] 에는 가장 큰 요소가 존재한다.
 *          1 과 N 을 swap 한 후 1 에 대해서 N 만큼의 힙을 sink(1) 한다.
 *          위을 N 이 1이 될때까지 반복한다.
 *          
 *      
 *  [algo] time complexity
 *      * logN
 *      
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>, Sort<E> {
    @SuppressWarnings("unchecked")
    private E[] heap = (E[]) new Comparable[2];
    private Comparator<E> c;
    private int N = 0;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> c) {
        this.c = c;
    }

    /**
     * insert e into the pq
     * 
     * @param e
     */
    @Override
    public void enqueue(E e) {
        heap[++N] = e;
        if (N+1 == heap.length)
            resize((N+1) << 1);
        swim(N);
    }

    @SuppressWarnings("unchecked")
    private void resize(int max) {
        E[] copy = (E[]) new Comparable[max];
        for (int i=1; i<=N; i++)
            copy[i] = heap[i];
        heap = copy;
    }

    /**
     * swim k until heap[k] < heap[p]
     * 
     * @param k
     */
    public void swim(int k) {
        if (k == 1) return; // root has no parent
        int p = k / 2;
        if (less(heap[p], heap[k])) {
            swap(p, k, heap);
            swim(k / 2);
        }
    }

    private void sink(E[] elements, int k, int N) {
        int c = k << 1;
        if (c > N) return;
        if (c+1 <= N && less(elements[c], elements[c+1]))
            c++;
        if (less(elements[k], elements[c])) {
            swap(k, c, elements);
            sink(elements, c, N);
        }
    }

    public void sink(int k) {
        sink(heap, k, N);
    }

    private void swap(int i, int j, E[] a) {
        E e = a[i];
        a[i] = a[j];
        a[j] = e;
    }

    public boolean less(E e1, E e2) {
        if (c != null)
            return c.compare(e1, e2) < 0;
        return e1.compareTo(e2) < 0;
    }

    //todo: do not "arraycopy"
    @SuppressWarnings("unchecked")
    @Override
    public void sort(E[] elements) {
        E[] copy = (E[]) new Comparable[elements.length+1];
        System.arraycopy(elements, 0, copy, 1, elements.length);
        int N = elements.length;
        for (int i=N/2; i>=1; i--)
            sink(copy, i, N);
        while (N > 1) {
            swap(1, N--, copy);
            sink(copy, 1, N);
        }
        System.arraycopy(copy, 1, elements, 0, elements.length);
    }


    /**
     * dequeue the min element from the pq
     * 
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) 
            throw new NoSuchElementException();
        E e = heap[1];
        swap(1, N, heap);
        heap[N] = null;
        N--;
        sink(1);
        return e;
    }

    /**
     * peek the min element from the pq
     * 
     * @return
     */
    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * the number of elements in the pq
     * 
     * @return
     */
    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<E> {

        private int i = 1;

        @Override
        public boolean hasNext() {
            return i <= N;
        }

        @Override
        public E next() {
            return heap[i++];
        }
    }

    public static void main(String[] args) {
        String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/tinyBatch.txt";
        Queue<Transaction> transactions = new PriorityQueue<>(new Transaction.HowMuchOrder().reversed());
        int M = 5;
        In in = new In(path);
        while (in.hasNextLine()) {
            Transaction t = new Transaction(in.readLine());
            transactions.enqueue(t);
            if (transactions.size() > M)
                transactions.dequeue();
        }
        for (Transaction t: transactions)
            System.out.println(t);
        System.out.println("===========================");
        Stack<Transaction> stack = new LinkedList<>();
        while (!transactions.isEmpty())
            stack.push(transactions.dequeue());
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}
