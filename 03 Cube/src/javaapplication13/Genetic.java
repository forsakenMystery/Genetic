/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Hamed Khashehchi
 */
public class Genetic {

    private ArrayList<Pheno> population;
    private int generation = 1;
    private final int numberOfOffSpring = 64;
    private int pop;
    private Table original;

    public Genetic(int pop) throws CloneNotSupportedException {
        this.population = new ArrayList<>();
        this.pop = pop;
        Table t = new Table();
        this.original = (Table) t.clone();
        for (int i = 0; i < pop; i++) {
            population.add(new Pheno(t));
        }
        Collections.sort(population);
        run_genetic(4096);
    }

    private void run_genetic(int run) throws CloneNotSupportedException {
        int i = 1;
        while (i < run) {
            System.out.println(i + "/" + run);
            Random r = new Random(System.nanoTime());
            ArrayList<Pheno> chosen = new ArrayList<>();
            ArrayList<Pheno> newPopulation = new ArrayList<>();
            for (int j = 0; j < 2 * numberOfOffSpring; j++) {
                int next = myRandom(population.size());
                chosen.add(population.get(next));
            }
            // mutation
            for (int j = 0; j < numberOfOffSpring; j++) {
                int next = r.nextInt(chosen.size());
                Pheno get = chosen.get(next);
                int howMany = r.nextInt(7) + 1;
                while (howMany-- > 0) {
                    int nxt = r.nextInt(get.getMovesLength());
                    String s;
                    char c;
                    if (r.nextBoolean()) {
                        s = "push";
                    } else {
                        s = "pop";
                    }
                    c = Table.getAns().charAt(r.nextInt(Table.getAns().length()));
                    Pair<String, Character> p = new Pair<>(s, c);
                    get.setMoves(nxt, p);
                }
            }
            //cross over
            for (int j = 0; j < numberOfOffSpring; j++) {
                int next = r.nextInt(chosen.size());
                Pheno get = chosen.get(next);
                int next1 = r.nextInt(chosen.size());
                Pheno get1 = chosen.get(next1);
                int min;
                if (get1.getMovesLength() < get.getMovesLength()) {
                    min = get1.getMovesLength();

                } else {
                    min = get.getMovesLength();
                }
                int nxt = r.nextInt(min ) + 1;
                ArrayList<Pair<String, Character>> mov = new ArrayList<>();
                ArrayList<Pair<String, Character>> mov2 = new ArrayList<>();
                for (int k = 0; k < nxt; k++) {
                    mov.add(get.getMoves(k));
                    mov2.add(get1.getMoves(k));
                }
                int k = nxt;
                while (k < get.getMovesLength()) {
                    mov2.add(get.getMoves(k++));
                }
                k = nxt;
                while (k < get1.getMovesLength()) {
                    mov.add(get1.getMoves(k++));
                }
                Pheno p = new Pheno(original);
                p.setMoves(mov2);
                newPopulation.add(p);
                p = new Pheno(original);
                p.setMoves(mov);
                newPopulation.add(p);
            }
            //random
            for (int j = 0; j < numberOfOffSpring; j++) {
                newPopulation.add(new Pheno(original));
            }

            population.addAll(newPopulation);
            Collections.sort(population);
            while (population.size() != pop) {
                int toBeRemoved;
                if (r.nextInt(10) < 1) {
                    toBeRemoved = myRandom(population.size() / 2);
                    population.remove(population.size() - (population.size() / 2) - toBeRemoved);
                } else {
                    toBeRemoved = r.nextInt(population.size() / 2);
                    population.remove(population.size() - 1 - toBeRemoved);
                }
            }
            i++;
        }
        System.out.println(original);
        System.out.println("======");
        System.out.println(population.get(0));
        Pheno get = population.get(0);
        for (int j = 0; j < get.getMovesLength(); j++) {
            System.out.println("j = " + j);
            Pair<String, Character> moves = get.getMoves(j);
            original.move(moves.getKey(), moves.getValue());
            System.out.println("original = " + original);
            System.out.println("================");
        }
    }

    private int myRandom(int k) {
//        System.out.println("k = " + k);
        Random r = new Random();
        // 1
        // 2 3
        // 4 5 6
        //=====
        //0 1 2
        int triangularK = k * (k + 1) / 2;
//        System.out.println("triangularK = " + triangularK);
        if (triangularK <= 0) {
//            System.out.println("wtf");
            triangularK = 100;
        }
        int x = r.nextInt(triangularK) + 1;

        double triangularRoot = (Math.sqrt(8 * x + 1) - 1) / 2;

        int bucket = (int) Math.ceil(triangularRoot);

        return k - bucket;
    }

}
