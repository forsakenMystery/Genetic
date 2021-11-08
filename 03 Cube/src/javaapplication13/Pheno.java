/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Hamed Khashehchi
 */
public class Pheno implements Comparable<Pheno> {

    private Table t;
    private Table original;
    private ArrayList<Pair<String, Character>> moves;
    private int fitness;
    private final static int MAX_POPULATION = 128;

    public void setMoves(int i, Pair<String, Character> move) throws CloneNotSupportedException {
        this.moves.set(i, move);
        fitness();
    }

    public Pair<String, Character> getMoves(int i) {
        return moves.get(i);
    }

    public int getMovesLength() {
        return moves.size();
    }

    public void setMoves(ArrayList<Pair<String, Character>> moves) throws CloneNotSupportedException {
        this.moves = moves;
        fitness();
    }

    public Pheno(Table t) throws CloneNotSupportedException {
        this.t = (Table) t.clone();
        this.original = (Table) t.clone();
        this.moves = new ArrayList<>();
        fitness = 0;
        Random r = new Random(System.nanoTime());
        int nextInt = r.nextInt(MAX_POPULATION) + 1;
        String statement = Table.getAns();
        for (int i = 0; i < nextInt; i++) {
            if ("".equals(statement)) {
                statement = Table.getAns();
            }
            if (r.nextBoolean()) {
                String move = "push";
                int next = r.nextInt(statement.length());
                char c = statement.charAt(next);
                statement = statement.replaceFirst(c + "", "");
                Pair<String, Character> p = new Pair<>(move, c);
                moves.add(p);
            } else {
                String move = "pop";
                int next = r.nextInt(statement.length());
                char c = statement.charAt(next);
                statement = statement.replaceFirst(c + "", "");
                Pair<String, Character> p = new Pair<>(move, c);
                moves.add(p);
            }
        }
        fitness();
//        System.out.println(moves);
    }

    private void fitness() throws CloneNotSupportedException {
        fitness = 0;
        this.t = (Table) original.clone();
//        System.out.println("t = " + t);
//        System.out.println("t = " + t);
//        System.out.println("======");
        for (int i = 0; i < moves.size(); i++) {
            t.move(moves.get(i).getKey(), moves.get(i).getValue());
//            System.out.println("t = " + t);
//            System.out.println(moves.get(i).getKey());
//            System.out.println(moves.get(i).getValue());
//            System.out.println("t = " + t);
//            System.out.println("======");
        }
//        System.out.println("pheno");
//        System.out.println(t.isCorrct());
        if (t.isCorrct()) {
            fitness = 100000;
            fitness += -(moves.size() * moves.size());
//            System.out.println(moves.size());
//            System.out.println("fitness = " + fitness);
        } else if (t.isFinish()) {
            fitness = 0;
        } else {
            fitness = -100;
        }
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Pheno t) {
        return Integer.compare(t.fitness, this.fitness);
    }

    @Override
    public String toString() {
        return moves + " => " + fitness; //To change body of generated methods, choose Tools | Templates.
    }

}
