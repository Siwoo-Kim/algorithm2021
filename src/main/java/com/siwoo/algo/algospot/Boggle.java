package com.siwoo.algo.algospot;

/**
 * 5 * 5 알파벳 격자 위에 상하좌우/대각선의 인접한 칸들의 글자를 이어서 단어를 찾아내기.
 *
 */
public class Boggle {
    private static final int MAX = 5;
    private char[][] BOARD;
    public boolean ok;

    public Boggle(char[][] BOARD) {
        this.BOARD = BOARD;
    }
    
    public boolean hasWord(String s) {
        for (int i=0; i<MAX; i++)
            for (int j=0; j<MAX; j++)
                if (hasWord(new Point(i, j), s))
                    return true;
        return false;
    }

    public boolean hasWord(Point start, String s) {
        return hasWord(start, s, 0);
    }

    private boolean hasWord(Point p, String s, int i) {
        if (!p.valid()) return false;
        if (s.length() == i) return true;
        if (s.charAt(i) != BOARD[p.x][p.y]) return false;
        for (Point d: Point.D) {
            Point w = new Point(p.x + d.x, p.y + d.y);
            if (hasWord(w, s, i+1)) return true;
        }
        return false;
    }

    private static class Point {
        private static final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, -1), new Point(-1, 1),
                new Point(1, -1), new Point(1, 1)
        };
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < MAX && y >= 0 && y < MAX;
        }
    }

    public static void main(String[] args) {
        String[] words = {
                "URLPM",
                "XPRET",
                "GIAET",
                "XTNZY",
                "XOQRS"
        };
        char[][] board = new char[MAX][MAX];
        for (int i=0; i<MAX; i++)
            board[i] = words[i].toCharArray();
        Boggle boggle = new Boggle(board);
        System.out.println(boggle.hasWord("PRETTY"));
        System.out.println(boggle.hasWord("GIRL"));
        System.out.println(boggle.hasWord("REPEAT"));
        System.out.println(boggle.hasWord("SIWOO"));
        System.out.println(boggle.hasWord("TNZQP"));
    }
}
