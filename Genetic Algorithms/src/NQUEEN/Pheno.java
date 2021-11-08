/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NQUEEN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Hamed Khashehchi
 */
public class Pheno implements Comparable<Pheno> {

    static void set(int slice, int mutation, float randomize, int choice, int offspring) {
        Pheno.slice = slice;
        Pheno.mutation = mutation;
        Pheno.randomize = randomize;
        Pheno.choice = choice;
        Pheno.offspring = offspring;
    }

    private int cost;
    private int[] answer;
    private int n;
    private int generation;
    private int name;
    private static int slice; // from where you slice
    private static int mutation; // max how many mutation
    private static float randomize; //
    private static int choice; // how many selection
    private static int offspring; // how many offspring created

    public int getCost() {
        return cost;
    }

    public Pheno(int n, int generation, int name) {
        this.n = n;
        this.answer = new int[n];
        randomize();
        threat();
        this.generation = generation;
        this.name = name;
    }

    private void threat() {
        cost = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = i + 1; j < this.n; j++) {
//                System.out.println("===================");
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
//                System.out.println("answer[i] = " + answer[i]);
//                System.out.println("answer[j] = " + answer[j]);
                if (answer[i] == answer[j]) {
//                    System.out.println("wtf");
                    cost++;
                }
                float m = (float) (answer[i] - answer[j]) / (i - j);
//                System.out.println("m = " + m);
                if (m == 1) {
//                    System.out.println("ftw");
                    cost++;
                }
                if (m == -1) {
//                    System.out.println("ffs");
                    cost++;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "[ generation: " + this.generation + ", name: " + this.name + ", cost: " + this.cost + " ]"; //To change body of generated methods, choose Tools | Templates.
    }

    public void show() {
        System.out.println("{ " + toString() + " : " + Arrays.toString(this.answer) + " }");
    }

    public void setPhen(int[] answer) {
        this.answer = answer;
        threat();
    }

    public int[] getPhen() {
        return answer;
    }

    private void randomize() {
        int[] a = IntStream.range(0, n).toArray();
        Integer[] c = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        List<Integer> list = Arrays.asList(c);
        Collections.shuffle(list);
        int i = 0;
        for (int h : list) {
            this.answer[i++] = h;
        }
    }

    @Override
    public int compareTo(Pheno t) {
        return this.cost - t.cost;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.hashCode(this.answer);
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
        if (!Arrays.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

}
