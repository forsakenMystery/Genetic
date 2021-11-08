/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Hamed Khashehchi
 */
public class GeneticNSlidingPuzzle implements Genetic {
    
    private static int generations = 1;
    private static final int max_population = 512;
    private static final int max_path = 64;// 32 is hardest
    private ArrayList<Pheno> population;
    private ArrayList<ArrayList<Pheno>> all;
    private final int numberOfOffSpring = 16;
    private final int stick = 8;
    private Board original_state;
    private Pheno theBestAnswer;
    private HashSet<Pheno> allTheAnswers;
    public static ArrayList<Float> allBestPopulation;
    public static ArrayList<Float> allMeanPopulation;
    
    public Pheno getTheBestAnswer() {
        return theBestAnswer;
    }
    
    public HashSet<Pheno> getAllTheAnswers() {
        return allTheAnswers;
    }
    
    public ArrayList<Float> getAllBestPopulation() {
        return allBestPopulation;
    }
    
    public ArrayList<Float> getAllMeanPopulation() {
        return allMeanPopulation;
    }
    
    public GeneticNSlidingPuzzle(Board start, int loop) {
        population = new ArrayList<>();
        allBestPopulation = new ArrayList<>();
        allMeanPopulation = new ArrayList<>();
        allTheAnswers = new HashSet<>();
        all = new ArrayList<>();
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < max_population; i++) {
            try {
                int path = r.nextInt(max_path) + 1;
                ArrayList<Move> moves = new ArrayList<>();
                Random t = new Random(System.nanoTime());
                for (int j = 0; j < path; j++) {
                    int move = t.nextInt(4);
                    switch (move) {
                        case 0:
                            moves.add(Move.left);
                            break;
                        case 1:
                            moves.add(Move.right);
                            break;
                        case 2:
                            moves.add(Move.top);
                            break;
                        case 3:
                            moves.add(Move.bottom);
                            break;
                        default:
                            break;
                    }
                }
                Pheno p = new Pheno((ArrayList<Move>) moves.clone(), (Board) start.clone());
                population.add(p);
//                System.out.println("i = " + i);
//                System.out.println("p.getCost() = " + p.getCost());
//                System.out.println("================\\\\\\\\\\\\\\\\\\");
//            System.out.println("p = " + p);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(GeneticNSlidingPuzzle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(population);
//        System.out.println("population = " + population);
        all.add(population);
        for(int i = 0;i<population.size(); i++){
            Pheno p = population.get(i);
//            System.out.println("i = " + i);
//            System.out.println("p.getCost() = " + p.getCost());
//            System.out.println("\\\\\\\\\\\\\\\\\\");
        }
        try {
            //        System.out.println("all = " + all);
            this.original_state = (Board) start.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(GeneticNSlidingPuzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        start_genetic(loop);
    }
    
    @Override
    public void start_genetic(int loop) {
        int i = 0;
        while (i++ < loop) {
            System.out.println(i + "/" + loop);
            allBestPopulation.add(population.get(0).getCost());
            float sum = 0.0f;
            sum = population.stream().map((p) -> p.getCost()).reduce(sum, (accumulator, _item) -> accumulator + _item);
            allMeanPopulation.add(sum / population.size());
            for (int kk = 0; kk < population.size(); kk++) {
                Pheno p = population.get(kk);
                if (p.getCost() == 0) {
                    try {
                        allTheAnswers.add((Pheno) p.clone());
//                        System.out.println("p = " + p);
//                        Board tale = new Board((Board) original_state.clone());
//                        p.getAnswer().forEach((m) -> {
//                            tale.move(m);
//                            System.out.println(tale.fitness());
//                            System.out.println("tale = " + tale);
//                            System.out.println("/+/+/+/+/+/+/+/+/+/+/+/+/+/+");
//                        });
//                        System.out.println("original_state = " + original_state);
//                        System.out.println("tale = " + tale);
//                        System.out.println(p.getHow());
//                        Thread.sleep(100);
//                        System.out.println("*=*=*=*=*=*=*=*=*=*=*");
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(GeneticNSlidingPuzzle.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } else {
                    break;
                }
            }
            Random r = new Random(System.nanoTime());
            ArrayList<Pheno> chosen = new ArrayList<>();
            for (int j = 0; j < 2 * numberOfOffSpring; j++) {
                int next = myRandom(population.size());
//                System.out.println("population.size() = " + population.size());
//                System.out.println("next = " + next);
                chosen.add(population.get(next));
            }
//            System.out.println("chosen = " + chosen);
            ArrayList<Pheno> childs = createChilds(chosen);
            population.addAll(childs);
            int toRemove = childs.size();
//            System.out.println("toRemove = " + toRemove);
            Collections.sort(population);
            int rangeOfRemove = toRemove * 2;
            if (rangeOfRemove > population.size()) {
                rangeOfRemove = population.size();
            }
//            System.out.println("rangeOfRemove = " + rangeOfRemove);
            while (toRemove > 0) {
//                System.out.println("===============");
//                System.out.println("toRemove = " + toRemove);
                rangeOfRemove = toRemove * 2;
                if (rangeOfRemove > population.size()) {
                    rangeOfRemove = population.size();
                }
//                System.out.println("population.size() = " + population.size());
//                System.out.println("rangeOfRemove = " + rangeOfRemove);
                int removing = myRandom(rangeOfRemove);
//                System.out.println("removing = " + removing);
                population.remove(population.size() - 1 - removing);
                toRemove--;
            }
//            System.out.println("best in population = " + population.get(0));
//            System.out.println("original_state = " + original_state);
            all.add((ArrayList<Pheno>) population.clone());
            System.out.println("======================================");
        }
//        System.out.println("allTheAnswers = " + allTheAnswers);
        for (Pheno p : allTheAnswers) {
            if (theBestAnswer == null) {
                try {
                    theBestAnswer = (Pheno) p.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(GeneticNSlidingPuzzle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (theBestAnswer.getAnswer().size() > p.getAnswer().size()) {
                    try {
                        theBestAnswer = (Pheno) p.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(GeneticNSlidingPuzzle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        System.out.println("original_state = " + original_state);
        System.out.println("theBestAnswer = " + theBestAnswer);
        System.out.println("allBestPopulation = " + allBestPopulation);
        System.out.println("allMeanPopulation = " + allMeanPopulation);
    }
    
    private int myRandom(int k) {
        Random r = new Random();
        // 1
        // 2 3
        // 4 5 6
        //=====
        //0 1 2
        int triangularK = k * (k + 1) / 2;
        
        int x = r.nextInt(triangularK) + 1;
        
        double triangularRoot = (Math.sqrt(8 * x + 1) - 1) / 2;
        
        int bucket = (int) Math.ceil(triangularRoot);
        
        return k - bucket;
    }
    
    private ArrayList<Pheno> createChilds(ArrayList<Pheno> choosedParent) {
//        System.out.println("choosedParent = " + choosedParent);
        ArrayList<Pheno> childs = new ArrayList<>();
        Random r = new Random(System.nanoTime());
        int size = choosedParent.size();
        int cut_size = size / 4;
//        cutting so answer wont get too long
//        System.out.println("reducing size");
        for (int i = 0; i < cut_size; i++) {
//            System.out.println("===================");
            int toCut = r.nextInt(size - 1) + 1;
//            System.out.println("toCut = " + toCut);
            Pheno get = choosedParent.get(toCut);
//            System.out.println("get = " + get);
            ArrayList<Move> answer = (ArrayList<Move>) get.getAnswer().clone();
//            System.out.println("answer = " + answer);
            int sticky = answer.size();
//            System.out.println("sticky = " + sticky);
            int nextStick = r.nextInt(sticky);
//            System.out.println("nextStick = " + nextStick);
//            System.out.println("nextStick = " + nextStick);
//            System.out.println(toCut + ", get = " + get);
            Pheno get1 = new Pheno(new ArrayList<Move>(answer.subList(0, nextStick)), get.getBoard());
            get1.setHow("reducing size");
            Pheno get2 = new Pheno(new ArrayList<Move>(answer.subList(nextStick, sticky)), get.getBoard());
            get2.setHow("reducing size");
//            System.out.println("get2 = " + get2);
//            System.out.println("get1 = " + get1);
            childs.add(get1);
            childs.add(get2);
        }
//        random
//        System.out.println("random");
        for (int i = 0; i < cut_size / 2; i++) {
//            System.out.println("===================");
            int path = r.nextInt(max_path);
            ArrayList<Move> moves = new ArrayList<>();
            Random t = new Random(System.nanoTime());
            for (int j = 0; j < path; j++) {
                int move = t.nextInt(4);
                switch (move) {
                    case 0:
                        moves.add(Move.left);
                        break;
                    case 1:
                        moves.add(Move.right);
                        break;
                    case 2:
                        moves.add(Move.top);
                        break;
                    case 3:
                        moves.add(Move.bottom);
                        break;
                    default:
                        break;
                }
            }
            Pheno p = new Pheno(moves, population.get(0).getBoard());
            p.setHow("random");
//            System.out.println("p = " + p);
            childs.add(p);
        }
//        mutation
//        System.out.println("muation");
        for (int i = 0; i < cut_size; i++) {
//            System.out.println("===================");
            int toMutate = r.nextInt(size);
//            System.out.println("toMutate = " + toMutate);
            Pheno get = choosedParent.get(toMutate);
//            System.out.println("get = " + get);

            ArrayList<Move> answer = (ArrayList<Move>) get.getAnswer().clone();
            Random t = new Random(System.nanoTime());
//            System.out.println("answer = " + answer);
            int numberOfMutation = t.nextInt(answer.size()) + 1;
//            System.out.println("numberOfMutation = " + numberOfMutation);
            for (int j = 0; j < numberOfMutation; j++) {
                int k = t.nextInt(answer.size());
//                System.out.println("k = " + k);
                int move = t.nextInt(4);
                switch (move) {
                    case 0:
                        answer.set(k, Move.left);
                        break;
                    case 1:
                        answer.set(k, Move.right);
                        break;
                    case 2:
                        answer.set(k, Move.top);
                        break;
                    case 3:
                        answer.set(k, Move.bottom);
                        break;
                    default:
                        break;
                }
            }
            Pheno get1 = new Pheno(answer, get.getBoard());
//            System.out.println("get = " + get);
//            System.out.println("get1 = " + get1);
            get1.setHow("mutation");
            childs.add(get1);
        }
//        crossover
//        System.out.println("crossover");
        for (int i = 0; i < cut_size; i++) {
//            System.out.println("===================");
            int toCross1 = r.nextInt(size);
//            System.out.println("toCross1 = " + toCross1);
            Pheno get1 = choosedParent.get(toCross1);
            int toCross2 = r.nextInt(size);
            while (toCross1 == toCross2) {
                toCross2 = r.nextInt(size);
            }
//            System.out.println("toCross2 = " + toCross2);
            Pheno get2 = choosedParent.get(toCross2);
            ArrayList<Move> answer1 = (ArrayList<Move>) get1.getAnswer().clone();
            ArrayList<Move> answer2 = (ArrayList<Move>) get2.getAnswer().clone();
            int crossOverInHere = r.nextInt(answer1.size() < answer2.size() ? answer1.size() : answer2.size());
//            System.out.println("crossOverInHere = " + crossOverInHere);
            ArrayList<Move> subList = new ArrayList<Move>(answer1.subList(0, crossOverInHere));
            subList.addAll(answer2.subList(crossOverInHere, answer2.size()));
            if (subList.size() > max_path) {
                subList = new ArrayList<Move>(subList.subList(0, max_path));
            }
            Pheno get3 = new Pheno(subList, get2.getBoard());
//            System.out.println("get1 = " + get1);
//            System.out.println("get2 = " + get2);
            subList = new ArrayList<Move>(answer2.subList(0, crossOverInHere));
            subList.addAll(answer1.subList(crossOverInHere, answer1.size()));
            if (subList.size() > max_path) {
                subList = new ArrayList<Move>(subList.subList(0, max_path));
            }
            Pheno get4 = new Pheno(subList, get1.getBoard());
//            System.out.println("get3 = " + get3);
//            System.out.println("get4 = " + get4);
            get3.setHow("cross-over");
            get4.setHow("cross-over");
            childs.add(get4);
            childs.add(get3);
        }
        //addition
//        System.out.println("Addition");
        for (int i = 0; i < cut_size; i++) {
//            System.out.println("===================");
            int toAdd = r.nextInt(size);
            Pheno get = choosedParent.get(toAdd);
//            System.out.println("get = " + get);
            ArrayList<Move> answer = (ArrayList<Move>) get.getAnswer().clone();
            int move = r.nextInt(4);
            switch (move) {
                case 0:
                    answer.add(Move.left);
                    break;
                case 1:
                    answer.add(Move.right);
                    break;
                case 2:
                    answer.add(Move.top);
                    break;
                case 3:
                    answer.add(Move.bottom);
                    break;
                default:
                    break;
            }
            Pheno p = new Pheno(answer, get.getBoard());
            p.setHow("addition");
//            System.out.println("p = " + p);
            childs.add(p);
        }
        return childs;
    }
    
}
