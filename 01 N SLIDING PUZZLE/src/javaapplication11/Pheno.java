/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication11;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Hamed Khashehchi
 */
public class Pheno implements Comparable<Pheno> {

    private ArrayList<Move> answer;
    private float cost;
    private Board board;

    public float getCost() {
        return cost;
    }

    public Pheno(ArrayList<Move> answer, Board board) {
        this.answer = answer;
        this.board = board;
        solve();
    }

    public Board getBoard() {
        return board;
    }

    public void setAnswer(ArrayList<Move> answer) {
        this.answer = answer;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.answer);
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
        final Pheno other = (Pheno) obj;
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    private void solve() {
        cost = Integer.MAX_VALUE;
        Board s = new Board(board);
//        System.out.println("solving");
        answer.forEach((Move m) -> {
//            System.out.println("////////////////////////////");
//            System.out.println("m = " + m);
//            System.out.println("s = " + s);
//            System.out.println(s.getMoveTillNow());
            s.move(m);
            cost = (s.fitness());
        });
//        System.out.println("board = " + board);
//        System.out.println("s = " + s);
//        System.out.println(s.getMoveTillNow());
//        System.out.println("answer = " + answer);
//        System.out.println("*********************");
        if (s.getMoveTillNow().isEmpty()) {
            // do nothing so maybe a sequence here works for us
        } else {
            answer = s.getMoveTillNow();
        }
    }

    public ArrayList<Move> getAnswer() {
        return answer;
    }

    @Override
    public int compareTo(Pheno t) {
        return Float.compare(this.cost, t.cost);
    }

    @Override
    public String toString() {
        return "moves : " + getAnswer().toString() + ", cost : " + cost + "\n"; //To change body of generated methods, choose Tools | Templates.
    }

}
