package com.siwoo.algo.sedgewick.collection;

import com.siwoo.algo.util.AppConfig;
import org.checkerframework.checker.units.qual.A;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * set of chars.
 */
public class Alphabet {
    private final char[] alpha;
    private final int[] indices;    // index of a char in the alphabet 
    private final int R;    // radix of the alphabet

    public static final Alphabet BINARY = new Alphabet("01");
    public static final Alphabet OCTAL = new Alphabet("01234567");
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    public static final Alphabet HEX_DECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet ASCII = new Alphabet(1<<7);
    public static final Alphabet EXTENDED_ASCII = new Alphabet(1<<8);
    public static final Alphabet UNICODE = new Alphabet(1<<16);
    
    public Alphabet(int R) {
        this.R = R;
        alpha = new char[R]; 
        indices = new int[R];
        for (int i=0; i<R; i++) {
            alpha[i] = (char) i;
            indices[i] = i;
        }
    }
    
    public Alphabet(String s) {
        R = s.length();
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i=0; i<R; i++) {   //check dup
            if (unicode[s.charAt(i)])
                throw new IllegalArgumentException();
            unicode[s.charAt(i)] = true;
        }
        indices = new int[Character.MAX_VALUE]; // char to index
        Arrays.fill(indices, -1);
        alpha = new char[R];        // index to char
        for (int i=0; i<R; i++) {
            alpha[i] = s.charAt(i);
            indices[alpha[i]] = i;
        }
    }

    /**
     * char of given index
     * 
     * @param index
     * @return
     */
    public char toChar(int index) {
        checkElementIndex(index, R);
        return alpha[index];
    }

    /**
     * index of given char
     * 
     * @param c
     * @return
     */
    public int toIndex(char c) {
       if (!contains(c)) throw new IllegalArgumentException();
       return indices[c];
    }

    /**
     * is the char in the alphabet?
     * 
     * @param c
     * @return
     */
    public boolean contains(char c) {
        return indices[c] != -1;
    }

    /**
     * the number of set chars in the alphabet.
     * 
     * @return
     */
    public int radix() {
        return R;
    }

    /**
     * the number of bits need to represents the alphabet
     * 
     * @return
     */
    public int logR() {
        int log = R-1;
        for (; log>=1; log/=2)
            log++;
        return log;
    }

    /**
     * string to indices
     * 
     * @param s
     * @return
     */
    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i=0; i<s.length(); i++)
            indices[i] = toIndex(s.charAt(i));
        return indices;
    }

    /**
     * indices to string
     * 
     * @param indices
     * @return
     */
    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<indices.length; i++)
            sb.append(toChar(indices[i]));
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String path = AppConfig.INSTANCE.getProperty("app.resources.algs4data") + "/pi.txt";
        final String pi = new Scanner(new FileInputStream(path)).nextLine();
        Alphabet alphabet = new Alphabet("0123456789");
        int[] count = new int[Character.MAX_VALUE];
        for (int i=0; i<pi.length(); i++)
            if (alphabet.contains(pi.charAt(i)))
                count[pi.charAt(i)]++;
        for (int i=0; i<alphabet.radix(); i++)
            System.out.println(alphabet.toChar(i) + ": " + count[alphabet.toChar(i)]);
    }
}
