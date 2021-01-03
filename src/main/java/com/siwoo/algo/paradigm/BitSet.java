package com.siwoo.algo.paradigm;

/**
 * [algo] [bitset]
 *  bit 을 이용한 부분 집합 표현.
 *  (해당하는 bit 의 값 0 과 1 에 따라, bit 번째 자리수의 원소를 포함하냐 안하냐를 표현.)
 *  
 * problem
 *  * 어떤 집합 S 에서 일부 원소만 선택 (부분 집합) 하는 모든 경우의 수를 구하고 싶다.
 *
 * components
 *  1. bit
 *  2. bit 연산, ~, &, |, ^
 *  3. shift 연산 <<, >>
 * 
 * A    B   ~A  A & B   A | B   A ^ B
 * 0    0   1       0       0       0
 * 0    1   1       0       1       1
 * 1    0   0       0       1       1
 * 1    1   0       1       1       0
 * 
 * 쉬프트 연산 <<, >>
 * x << n  x*2^n
 * x >> n  x/(2^n)
 * 
 * 집합 {1, 3, 4, 5, 9} 인 경우
 * 2^1 + 2^3 + 2^4 + 2^5 + 2^9  = 570
 * 
 * 전체 집합. (1<<N)-1
 * 공집합. 0
 */
public class BitSet {
    private static int bitset, N;

    public BitSet() { }
    
    public boolean contains(int e) {
        return (bitset & (1 << e)) != 0;
    }
    
    public void add(int e) {
        if (!contains(e)) {
            N++;
            bitset |= (1 << e);
        }
    }
    
    public boolean remove(int e) {
        if (contains(e)) {
            bitset &= ~(1 << e);
            N--;
            return true;
        }
        return false;
    }
    
    public int size() {
        return N;
    }

    @Override
    public String toString() {
        return Integer.toBinaryString(bitset);
    }

    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.add(1);
        bitSet.add(3);
        bitSet.add(5);
        bitSet.add(7);
        bitSet.add(9);
        System.out.println(bitSet.contains(0));
        System.out.println(bitSet.contains(1));
        System.out.println(bitSet.contains(2));
        System.out.println(bitSet.contains(3));
        bitSet.remove(1);
        bitSet.remove(2);
        bitSet.remove(3);
        bitSet.remove(4);
        System.out.println(bitSet.size());
        System.out.println(bitSet.toString());
    }
}
