/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamed Khashehchi
 */
public class Genetic {

    private static final int FUNCTION_NUMBER = 7;
    private ArrayList<Pheno> population;
    private int generation = 1;
//    private ArrayList<ArrayList<Pheno>> all;
    private static final int MAX_DEPTH = 16;
    private final int numberOfOffSpring = 8;
    private int pop;
    private ArrayList<Pair> accurate;

    public Genetic(int pop) throws CloneNotSupportedException {
        this.pop = pop;
        accurate = new ArrayList<>();
        population = new ArrayList<>();
//        all = new ArrayList<>();
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < pop; i++) {
//            System.out.println("====================");
            int depth = r.nextInt(MAX_DEPTH) + 1;
            population.add(new Pheno(depth));
        }
//        System.out.println("population = " + population);
        Collections.sort(population);
//        System.out.println("population = " + population);
//        all.add(population);
        run_genetic(1e-3, -1);
    }

    public Genetic() {
    }

    Node mutation(Node T1) {
        PairPlus P1;
        int size1, clipNumber1;
        size1 = T1.mySize();
        Random r = new Random(System.nanoTime());
        do {
            clipNumber1 = Math.abs(r.nextInt() % (size1 - 1)) + 1;
//            System.out.println("node number = " + clipNumber1);
            P1 = T1.trace(1, clipNumber1);
        } while (P1.getTarget().getType().equals(Type.terminal));
        boolean functionMask[] = new boolean[FUNCTION_NUMBER];
        Node t;
        for (int i = 0; i < FUNCTION_NUMBER; i++) {
            functionMask[i] = true;
        }
//        System.out.println("===========================");
//        if (P1.getPredecessor() != null) {
////            System.out.println(P1.getPredecessor().getClass());
//        }
//        System.out.println(P1.getTarget().getClass());
//        System.out.println(P1.getTarget().getType());
        do {
            t = RandomNode.getR().randomFunction(functionMask);
//            System.out.println(t.getType());
//            System.out.println(t.getClass());
//            System.out.println(P1.getTarget().getClass());
//            System.out.println("*************");
        } while (t.getClass().equals(P1.getTarget().getClass()));

        if (P1.getTarget().getClass().equals(Cos.class)) {
            t.addChild(((Cos) P1.getTarget()).getLeft(), 1);
            t.addChild(((Cos) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Sin.class)) {
            t.addChild(((Sin) P1.getTarget()).getLeft(), 1);
            t.addChild(((Sin) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Power.class)) {
            t.addChild(((Power) P1.getTarget()).getLeft(), 1);
            t.addChild(((Power) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Mul.class)) {
            t.addChild(((Mul) P1.getTarget()).getLeft(), 1);
            t.addChild(((Mul) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Add.class)) {
            t.addChild(((Add) P1.getTarget()).getLeft(), 1);
            t.addChild(((Add) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Sub.class)) {
            t.addChild(((Sub) P1.getTarget()).getLeft(), 1);
            t.addChild(((Sub) P1.getTarget()).getRight(), 2);
        } else if (P1.getTarget().getClass().equals(Div.class)) {
            t.addChild(((Div) P1.getTarget()).getLeft(), 1);
            t.addChild(((Div) P1.getTarget()).getRight(), 2);
        } else {
            t.addChild(((Add) P1.getTarget()).getLeft(), 1);
            t.addChild(((Add) P1.getTarget()).getRight(), 2);
        }
//        P1.getTarget().addChild(t, );
        if (P1.getPredecessor() != null) {
//            System.out.println("faq");
            P1.getPredecessor().addChild(t, Relation.getRelation(P1.getRelation()));
        } else {
//            System.out.println("dafaq");
            return t;
        }
        return null;
    }

    void crossover(Node T1, Node T2) {
        PairPlus P1, P2;
        int size1, size2, clipNumber1, clipNumber2;

        size1 = T1.mySize();
        Random r = new Random();
        clipNumber1 = Math.abs(r.nextInt() % (size1 - 1)) + 2;
        size2 = T2.mySize();
//        System.out.println("clipNumber1 = " + clipNumber1);
        clipNumber2 = Math.abs(r.nextInt() % (size2 - 1)) + 2;
//        System.out.println("clipNumber2 = " + clipNumber2);
        P1 = T1.trace(1, clipNumber1);
        P2 = T2.trace(1, clipNumber2);
//        System.out.println("P1 = " + P1);
//        System.out.println("P2 = " + P2);
        P2.getRelation();
//        Relation.valueOf(P2.getRelation().toString());
        P1.getPredecessor().addChild(P2.getTarget(), Relation.getRelation(P1.getRelation()));
        P2.getPredecessor().addChild(P1.getTarget(), Relation.getRelation(P2.getRelation()));
    }

    private void run_genetic(double acc, int cap) throws CloneNotSupportedException {
        boolean flag = false;
        boolean first = false;
        boolean second = false;
        boolean third = false;
        boolean forth = false;
        boolean fifth = false;
        int fiveInARow = 2;

        if (cap == -1) {
            flag = true;
        }
        int i = 1;
        double accuracy = population.get(0).getCost();
        StringBuilder b = new StringBuilder();
        while (true) {
            if (!flag) {
                if (accuracy < acc) {
                    break;
                }
                if (i > cap) {
                    break;
                }
                System.out.println(i + "/" + cap);
            } else {
                if (accuracy < acc) {
                    break;
                }
            }
            if (accuracy < 100 && !first) {
                first = true;
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p).append("\n");
                File file = new File("answer100.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(b.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (accuracy < 50 && !second) {
                second = true;
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p).append("\n");
                File file = new File("answer50.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(b.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (accuracy < 10 && !third) {
                third = true;
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p).append("\n");
                File file = new File("answer10.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(b.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (accuracy < 5 && !fifth) {
                fifth = true;
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p).append("\n");
                File file = new File("answer5.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(b.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (accuracy < 1 && !forth) {
                forth = true;
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p).append("\n");
                File file = new File("answer1.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(b.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fiveInARow-- > 0) {
                Pair p = new Pair(generation, (Pheno) population.get(0).clone());
                accurate.add(p);
                System.out.println("generation = " + generation);
                System.out.println("population.get(0) = " + population.get(0).getNode());
                b.append(p + "\n");
                System.out.println("===========================");
            }
            Random r = new Random(System.nanoTime());
            ArrayList<Pheno> chosen = new ArrayList<>();
            ArrayList<Pheno> newPopulation = new ArrayList<>();
            for (int j = 0; j < 2 * numberOfOffSpring; j++) {
                int next = myRandom(population.size());
                chosen.add(population.get(next));
            }
            //mutation
            for (int j = 0; j < numberOfOffSpring; j++) {
                int next = r.nextInt(chosen.size());
//                System.out.println("===========");
//                System.out.println("next = " + next);
//                System.out.println(chosen.get(next));
                int howMany = ((myRandom(chosen.get(next).getNode().mySize())) / 8) + 1;
                Node duplicate = chosen.get(next).getNode().duplicate();
                while (howMany-- > 0) {
                    Node n = mutation(duplicate);
                    if (n != null) {
                        duplicate = n;
                    }
                }
                newPopulation.add(new Pheno(duplicate));
            }
            //cross over
            for (int j = 0; j < numberOfOffSpring; j++) {
                int next = myRandom(chosen.size());
                int next2 = myRandom(chosen.size());
//                int next3 = r.nextInt(chosen.size());
                Node n1 = chosen.get(next).getNode();
                Node n2 = chosen.get(next2).getNode();
//                Node n3 = chosen.get(next3).getNode();
                Node duplicate1 = n1.duplicate();
                Node duplicate2 = n2.duplicate();
//                Node duplicate3 = n3.duplicate();
                crossover(duplicate1, duplicate2);

                if (duplicate1.getDepth() < MAX_DEPTH) {
                    newPopulation.add(new Pheno(duplicate1.duplicate()));
                } else {
                    Pheno p = new Pheno(duplicate1.duplicate());
                    if (p.getCost() < population.get(0).getCost()) {
                        newPopulation.add(p);
                    }
                }
                if (duplicate2.getDepth() < MAX_DEPTH) {
                    newPopulation.add(new Pheno(duplicate2.duplicate()));
                } else {
                    Pheno p = new Pheno(duplicate2.duplicate());
                    if (p.getCost() < population.get(0).getCost()) {
                        newPopulation.add(p);
                    }
                }

//                crossover(duplicate1, duplicate3);
//                if (duplicate1.getDepth() < MAX_DEPTH) {
//                    newPopulation.add(new Pheno(duplicate1.duplicate()));
//                } else {
//                    Pheno p = new Pheno(duplicate1.duplicate());
//                    if (p.getCost() < population.get(0).getCost()) {
//                        newPopulation.add(p);
//                    }
//                }
//                
//                crossover(duplicate2, duplicate3);
//                if (duplicate2.getDepth() < MAX_DEPTH) {
//                    newPopulation.add(new Pheno(duplicate2.duplicate()));
//                } else {
//                    Pheno p = new Pheno(duplicate2.duplicate());
//                    if (p.getCost() < population.get(0).getCost()) {
//                        newPopulation.add(p);
//                    }
//                }
//                
//                if (duplicate3.getDepth() < MAX_DEPTH) {
//                    newPopulation.add(new Pheno(duplicate3.duplicate()));
//                } else {
//                    Pheno p = new Pheno(duplicate3.duplicate());
//                    if (p.getCost() < population.get(0).getCost()) {
//                        newPopulation.add(p);
//                    }
//                }
            }
            //random
            for (int j = 0; j < numberOfOffSpring; j++) {
                newPopulation.add(new Pheno(r.nextInt(MAX_DEPTH)));
            }
            population.addAll(newPopulation);
            Collections.sort(population);
            while (population.size() != pop) {
                int toBeRemoved;
                if (r.nextInt(10) < 1) {
                    toBeRemoved = myRandom(population.size()/2);
                    population.remove(population.size() - (population.size()/2) - toBeRemoved);
                } else {
                    toBeRemoved = r.nextInt(population.size() / 2);
                    population.remove(population.size() - 1 - toBeRemoved);
                }
//                int toBeRemoved = (int) Math.round((r.nextGaussian() * (population.size()-1)));

                
            }
            accuracy = population.get(0).getCost();
            System.out.println("accuracy = " + accuracy);
//            all.add(population);
            i++;
            generation++;
            if (i % 100 == 0) {
                System.out.println("==========================");
                System.out.println("==========================");
                System.out.println(i);
                fiveInARow = 2;
                System.out.println("==========================");
                System.out.println("==========================");
            }
        }
        Pair p = new Pair(generation, (Pheno) population.get(0).clone());
        accurate.add(p);
        System.out.println("generation = " + generation);
        System.out.println("population.get(0) = " + population.get(0).getNode());
        b.append(p).append("\n");
        File file = new File("answer.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(b.toString());
        } catch (IOException ex) {
            Logger.getLogger(Genetic.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("===========================");
        System.out.println("accurate = " + accurate);
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
