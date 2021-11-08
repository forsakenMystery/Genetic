/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Hamed Khashehchi
 */
public class Board implements Comparable<Board> {

    private final int n = 4;
    private int board[][];
    private int x;
    private int y;
    private int numberOfMove;
    private final int moves = 4;
    private ArrayList<Move> moveTillNow;
    private float fitting;
    private float[] probability;

    public Board() {

        board = new int[n][n];
        reset();
    }

    public float fitness() {
        float f = 0;
//        int[][] bb = {{1, 3, 4}, {0, 7, 6}, {5, 2, 8}};
//        board = bb;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != (i * n + j + 1) % (n*n)) {
                    q:
                    for (int m = 0; m < n; m++) {
                        for (int s = 0; s < n; s++) {
                            if (board[m][s] == (i * n + j + 1) % (n*n)) {
//                                System.out.println("I was looking for " + (i * n + j + 1) % 9);
//                                System.out.println("instead of being in (" + i + "," + j + ") it was in (" + m + ", " + s+")s");
//                                System.out.println(Math.sqrt((i - m) * (i - m) + (j - s) * (j - s)));
//                                System.out.println("============================");
                                f += Math.sqrt(((i - m) * (i - m)) + ((j - s) * (j - s)));
                                break q;
                            }
                        }
                    }
                }
            }
        }
//        System.out.println("=****************=");
//        System.out.println("f = " + f);
        return f;
    }

    public Board(Board a) {
        this.probability = new float[]{0.25f, 0.25f, 0.25f, 0.25f};
//        this.board = a.board.clone();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.board[i][j] = a.board[i][j];
            }
        }
        this.x = a.x;
        this.y = a.y;
        this.fitting = a.fitting;

        moveTillNow = new ArrayList<>();
        moveTillNow.addAll(a.moveTillNow);
        this.numberOfMove = a.numberOfMove;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Board f = new Board();
        f.probability = this.probability.clone();
        f.x = this.x;
        f.y = this.y;
        f.moveTillNow = (ArrayList<Move>) this.moveTillNow.clone();
        f.numberOfMove = this.numberOfMove;
        f.board = new int[n][n];
        f.fitting = this.fitting;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f.board[i][j] = this.board[i][j];
            }
        }
        return f; //To change body of generated methods, choose Tools | Templates.
    }

    public int[][] getBoard() {
        return board;
    }

    public ArrayList<Move> getMoveTillNow() {
        return moveTillNow;
    }

    public void setNumberOfMove() {
        this.numberOfMove = 0;
        this.moveTillNow.clear();
    }

    public void randomWalk() {
        randomWalk(50);
        this.setNumberOfMove();
    }

    public ArrayList<Move> possible() {
        ArrayList<Move> mov = new ArrayList<>();
        Board ans = new Board(this);
        if (ans.move(Move.top)) {
            ans.move(Move.bottom);
            mov.add(Move.top);
        }
        if (ans.move(Move.bottom)) {
            ans.move(Move.top);
            mov.add(Move.bottom);
        }
        if (ans.move(Move.left)) {
            ans.move(Move.right);
            mov.add(Move.left);
        }
        if (ans.move(Move.right)) {
            ans.move(Move.left);
            mov.add(Move.right);
        }
        return mov;
    }

    public void randomWalk(int n) {
        while (n-- != 0) {
            Random r = new Random(System.nanoTime());
            int go = r.nextInt(moves);
            if (go == 0) {
                move(Move.bottom);
            } else if (go == 1) {
                move(Move.left);
            } else if (go == 2) {
                move(Move.right);
            } else {
                move(Move.top);
            }
        }
    }

    public boolean move(Move m) {
        int x_prime = x;
        int y_prime = y;
        if (Move.bottom.equals(m)) {
//            System.out.println("bottom");
            if (x < n-1) {
                x++;
            }
        } else if (Move.top.equals(m)) {
//            System.out.println("top");
            if (x > 0) {
                x--;
            }
        } else if (Move.left.equals(m)) {
//            System.out.println("left");
            if (y > 0) {
                y--;
            }
        } else if (Move.right.equals(m)) {
//            System.out.println("right");
            if (y < n-1) {
                y++;
            }
        }
        int temp = board[x][y];
        int tempo = board[x_prime][y_prime];
        if (x_prime != x || y_prime != y) {
            board[x][y] = tempo;
            board[x_prime][y_prime] = temp;
            moveTillNow.add(m);
            numberOfMove = moveTillNow.size();
            fitting = fitness();
            return true;
        }
        fitting = fitness();
        return false;
    }

    private void reset() {
        x = n-1;
        y = n-1;
        this.probability = new float[]{0.25f, 0.25f, 0.25f, 0.25f};
        fitting = 0;
        moveTillNow = new ArrayList<>();
        numberOfMove = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (i * n + j + 1) % (n*n);
            }
        }
    }

    @Override
    public String toString() {
        String s = "\nx = " + x + ", y = " + y + ", number of moves = " + numberOfMove + ", fitness = " + fitting + "\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s += board[i][j] + " ";
            }
            if (i != n - 1) {
                s += "\n";
            }
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Board t) {
        return Float.compare(this.fitting, t.fitting);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.n;
        hash = 97 * hash + Arrays.deepHashCode(this.board);
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.numberOfMove;
        hash = 97 * hash + Objects.hashCode(this.moveTillNow);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (this.n != other.n) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.numberOfMove != other.numberOfMove) {
            return false;
        }
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        if (!Objects.equals(this.moveTillNow, other.moveTillNow)) {
            return false;
        }
        return true;
    }

}
