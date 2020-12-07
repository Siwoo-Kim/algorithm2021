package com.siwoo.algo.paradigm;

import com.siwoo.algo.util.AppConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * segment tree
 *  - rmq 을 해결하기 위한 자료구조.
 *  
 *  rmq (range minimum query)
 *      - 배열 a 에서 i..j 구간의 최소값을 찾는 query
 *      
 *      sqrt decomposition 을 이용한 rmq (루트 N 그룹화 - sqrt groups 이라 하자)
 *          - 배열 a 의 길이가 n 이라면 sqrt(n) 을 r 이라 할때, a 을 r 개의 그룹으로 분배.
 *          - 이후 group[i] 에 i 번 그룹의 최소값을 저장. 
 *      
 *      sqrt groups 을 이용한 rmq.  - O(sqrt(N))
 *          i, j  에 대한 rmq 의 경우.
 *              i 의 그룹 = i/r
 *              j 의 그룹 = j/r 
 *              i 와 j 사이의 그룹 = i/r + 1 .. j/r - 1
 *              
 *              1. i 와 j 가 같은 sqrt group 에 있는 경우. 
 *                  - sqrt(N) 이므로 그냥 구한다.
 *                  
 *              2. i 와 j 가 다른 sqrt groups 에 있는 경우.
 *                  필요한 sqrt groups 
 *                      1. i 가 속한 sqrt group.
 *                      2. j 가 속한 sqrt group.
 *                      3. i 와 j 사이의 sqrt groups.
 *                      
 *                      i 와 j 의 sqrt group 의 값은 사용하지 못한다. 
 *                      해당 sqrt group 은 그룹의 모든 원소의 최소값이므로.
 *                      
 *                      i 와 j 사이의 sqrt groups 의 값은 사용할 수 있다.
 *  
 *  segment tree.
 *      - rmq (구간 최솟값 쿼리 탐색) 을 위한 자료구조.
 *      - 배열 a 의 부분 구간에 대한 작업시 거의 모든 부분에 적용 가능.
 *      - 바이너리 트리.
 *      - 각 노드는 구간 start, end 와 최소값을 표현.
 *      - 중간값 (start+end)/2 을 m 이라 할때, 
 *          왼쪽 자식은 start ~ mid, 오른쪽 자식은 mid+1, end
 *      - 배열을 통한 트리 표현. i 의 왼쪽 자식 2i, 오른쪽 자식 2i+1
 *      - 배열 i 의 구간을 쉽게 구하기 위해 노드 (start, end, a 의 index) 을 정의.
 *      - 트리의 크기는 높이 H (logN) 이라 할때 2^(H+1)
 *      
 *      1. node 의 구간이 i,j 구간을 포함한다면
 *          해당 노드의 값을 사용하지 못하므로 양 쪽 자식을 호출. (* 가장 중요한 특성.)
 *          
 *      2. node 의 구간이 겹치지 않으면 해당하지 않으므로 (node 의 자식 또한) 리턴.     
 *      3. node 의 구간이 i,j 구간에 포함된다면 node 의 최솟값을 리턴.
 *      
 *      i..j 구간과 l..r 구간의 겹침을 확인.
 *          겹치지 않은 경우을 c1
 *              [ ? ] [ i .. j ] [ ? ]
 *              r < i           j < l
 *              (r < i) || (j < l)
 *          i..j 구간이 l..r 구간에 완전히 겹치는 경우을 c2
 *              [ i .. [ l .. r ] .. j ]
 *                i <= l       r <= j
 *              (i<=l) && (r <= j)
 *          걸치는 경우는 !(c1 && c2)
 *          
 */
public class SegmentTree {
    private int[] tree, a;
    
    public SegmentTree(int[] a) {
        this.a = a;
        int h = (int) Math.ceil(Math.log(a.length) / Math.log(2));
        tree = new int[1 << (h+1)];
        build(1, 0, a.length-1);
    }
    
    private void build(int node, int start, int end) {  //post order (need child's value)
        if (start == end) tree[node] = a[start];    //leaf node.
        else {
            int mid = (start+end) / 2;
            build(node<<1, start, mid); //left
            build((node<<1)+1,mid+1, end);  //right
            tree[node] = Math.min(tree[node<<1], tree[node*2+1]);
        }
    }

    private int query(int left, int right) {
        return query(1, 0, a.length-1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return -1; //no applicable (no intersection)
        if (start >= left && end <= right) return tree[node];   //section start..end is in section left..right
        int mid = (start + end) / 2;
        int m1 = query(node*2, start, mid, left, right);    //left
        int m2 = query(node*2+1, mid+1, end, left, right);  //right
        return min(m1, m2);
    }

    private int min(int m1, int m2) {
        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        return Math.min(m1, m2);
    }

    private static int rmq(int[] a, int i, int j) {
        assert i <= j && i >= 0 && j < a.length;
        int[] sqrtGroups = sqrtDecomposition(a);
        int r = (int) (Math.sqrt(a.length) + 1);
        int gi = i / r,
                gj = j / r;
        int min = Integer.MAX_VALUE;
        if (gi == gj) {
            for (int k=i; k<=j; k++)
                min = Math.min(min, a[k]);
        } else {
            int start = i;
            while (true) {
                min = Math.min(a[start], min);
                start++;
                if (start % r == 0)
                    break;
            }
            int end = j;
            while (true) {
                min = Math.min(a[end], min);
                end--;
                if (end % r == r-1)
                    break;
            }
            for (int g=start/r; g<=end/r; g++)
                min = Math.min(min, sqrtGroups[g]);
        }
        return min;
    }

    private static int[] sqrtDecomposition(int[] a) {
        int r = (int) Math.sqrt(a.length);
        int[] groups = new int[r+1];
        for (int i=0; i<a.length; i++) {
            if (i%r == 0)
                groups[i/r] = a[i];
            else
                groups[i/r] = Math.min(groups[i/r], a[i]);
        }
        return groups;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[] a = null;
        try (Scanner scanner = new Scanner(new FileInputStream(AppConfig.INSTANCE.getProperty("app.resources.testdata") + "/rmq.txt"))) {
            int n = scanner.nextInt();
            a = new int[n];
            for (int i=0; i<n; i++)
                a[i] = scanner.nextInt();
            System.out.println(rmq(a, 3, 5));
            System.out.println(rmq(a, 1, 9));
            System.out.println(rmq(a, 7, 10));
        }
        System.out.println("=====================");
        SegmentTree st = new SegmentTree(a);
        int x = st.query(3, 5);
        System.out.println(x);
        x = st.query(1, 9);
        System.out.println(x);
        x = st.query(7, 10);
        System.out.println(x);
        x = st.query(6, 7);
        System.out.println(x);
    }
}
