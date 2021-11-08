/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamed Khashehchi
 */
public class Pheno implements Comparable<Pheno> {

    private ArrayList<Move> answer;
    private float cost;
    private Board board;
    private String how;

    public void setHow(String how) {
        if (this.how == null || this.how.equals("")) {
            this.how = how;
        } else {
            this.how += ", " + how;
        }
    }

    public String getHow() {
        return how;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayList<Move> a = (ArrayList<Move>) this.answer.clone();
        Pheno p = new Pheno(a, (Board) board.clone());
        p.solve();
        return p;
    }

    public float getCost() {
        return cost;
    }

    public Pheno(ArrayList<Move> answer, Board board) {
        this.answer = answer;
        try {
            this.board = (Board) board.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Pheno.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cost = Float.MAX_VALUE;
        solve();
    }

    public Board getBoard() {
        return board;
    }

    public void setAnswer(ArrayList<Move> answer) {
        this.answer = answer;
        solve();
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
        try {
            cost = Float.MAX_VALUE;
            Board s = new Board((Board) board.clone());
//        System.out.println("solving");
            answer.forEach((Move m) -> {
//            System.out.println("////////////////////////////");
//            System.out.println("m = " + m);
//            System.out.println("s = " + s);
//            System.out.println(s.getMoveTillNow());
                s.move(m);
                cost = s.fitness();
//            if(cost == 0 ){
////                System.out.println("s = " + s);
////                try {
////                    Thread.sleep(10000);
////                } catch (InterruptedException ex) {
////                    Logger.getLogger(Pheno.class.getName()).log(Level.SEVERE, null, ex);
////                }
//            }
            });
//            if (cost == 0) {
//                System.out.println("fuck fuck fuck fuck fuck fuck fuck fuck\n\nfuck fuck fuck fuck fuck fuck fuck fuck");
//                System.out.println("answer = " + answer);
//                System.out.println("s = " + s);
//            }
//        System.out.println("board = " + board);
//        System.out.println("s = " + s);
//        System.out.println(s.getMoveTillNow());
//        System.out.println("answer = " + answer);
//        System.out.println("*********************");
//            if (s.getMoveTillNow().isEmpty()) {
//                // do nothing so maybe a sequence here works for us
//            } else {
////                answer = s.getMoveTillNow();
//            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Pheno.class.getName()).log(Level.SEVERE, null, ex);
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
