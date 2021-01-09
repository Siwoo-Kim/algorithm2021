package com.siwoo.algo.acmicpc;

import java.util.*;
import java.util.function.Function;

public class P14226 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        Buffer buffer = new Buffer(1, 0);
        Queue<Buffer> q = new LinkedList<>();
        q.add(buffer);
        Map<Buffer, Integer> distTo = new HashMap<>();
        distTo.put(buffer, 0);
        List<Function<Buffer, Buffer>> paths = Arrays.asList(
                Buffer::copy, Buffer::paste, Buffer::delete);
        while (!q.isEmpty()) {
            Buffer v = q.poll();
            if (v.count == N) {
                System.out.println(distTo.get(v));
                return;
            }
            for (Function<Buffer, Buffer> b: paths) {
                Buffer w = b.apply(v);
                if (w != null 
                        && !distTo.containsKey(w)) {
                    distTo.put(w, distTo.get(v) + 1);
                    q.add(w);
                }
            }
        }
    }
    
    private static class Buffer {
        private int count;
        private int buffer;

        public Buffer(int count, int buffer) {
            this.count = count;
            this.buffer = buffer;
        }

        public Buffer copy() {
            return new Buffer(count, count);
        }

        public Buffer paste() {
            if (buffer == 0) return null;
            return new Buffer(count+buffer, buffer);
        }

        public Buffer delete() {
            if (count == 0) return null;
            return new Buffer(count-1, buffer);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Buffer buffer1 = (Buffer) o;
            return count == buffer1.count &&
                    buffer == buffer1.buffer;
        }

        @Override
        public int hashCode() {
            return Objects.hash(count, buffer);
        }
    }
}
