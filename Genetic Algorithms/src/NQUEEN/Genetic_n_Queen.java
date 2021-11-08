/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NQUEEN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author Hamed Khashehchi
 */
public class Genetic_n_Queen {

    private static int queen;
    private static int population;
    private Pheno[] generation;
    private static int generations = 1;
    private ArrayList<Pheno[]> all;
    private static int slice;
    private static int mutation;
    private static float randomize; // selection of worst one
    private static int choice;
    private static int offspring;
    private ArrayList<Long> timestamp;
    private long start;
    private long end;
    private long firstAnswerTime;

    public long getFirstAnswerTime() {
        return firstAnswerTime;
    }

    public long getWholeAnswerTime() {
        long sum = 0;
        sum = timestamp.stream().map((l) -> l).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    public ArrayList<Double> getAvgCost() {
        ArrayList<Double> dou = new ArrayList<>();
        for (Pheno[] p : all) {
            double sum = 0.0;
            for (Pheno lay : p) {
//                System.out.println("wtf");
//                System.out.println("lay = " + lay);
                sum += lay.getCost();
            }
//            System.out.println("sum = " + sum);
//            System.out.println("p.length = " + p.length);
            double averageCost = sum / p.length;
            dou.add(averageCost);
        }
        return dou;
    }

    public ArrayList<Long> getMaxCost() {
        ArrayList<Long> dou = new ArrayList<>();
        for (Pheno[] p : all) {
            long min=Long.MIN_VALUE;
            for (Pheno lay : p) {
//                System.out.println("wtf");
//                System.out.println("lay = " + lay);
//                System.out.println("min = " + min);
                if(lay.getCost()>min){
                    min = lay.getCost();
                }
            }
            dou.add(min);
        }
        return dou;
    }

    public ArrayList<Pheno[]> getAll() {
        return all;
    }

    public ArrayList<Long> getTimestamp() {
        return timestamp;
    }

    public Pheno[] getGeneration() {
        return generation;
    }

    public ArrayList<Pheno> getBest() {
        ArrayList<Pheno> p = new ArrayList<>();
        p.add(generation[0]);
        int i = 1;
        while (generation[i].getCost() == p.get(p.size() - 1).getCost()) {
            if (!p.contains(generation[i])) {
                p.add(generation[i++]);
            } else {
                i++;
            }
        }

        return p;
    }

    public static void ses(int slice, int mutation, float randomize, int choice, int offspring) {
        Genetic_n_Queen.slice = slice;
        Genetic_n_Queen.mutation = mutation;
        Genetic_n_Queen.randomize = randomize;
        Genetic_n_Queen.choice = choice;
        Genetic_n_Queen.offspring = offspring;
    }

    public static void set(int q, int p) {
        Genetic_n_Queen.queen = q;
        Genetic_n_Queen.population = p;
    }

    public Genetic_n_Queen(int loop) {
        all = new ArrayList<>();
        timestamp = new ArrayList<>();
        generation = new Pheno[population];
        for (int i = 0; i < population; i++) {
            generation[i] = new Pheno(Genetic_n_Queen.queen, 0, i);
//            generation[i].show();
        }
        start_genetic(loop);
    }

    public void start_genetic(int loop) {
        boolean first = false;
        Random r = new Random();
        int i = 0;
        int temp, a, b;
        start = System.nanoTime();
        while (i++ < loop) {
            all.add(generation.clone());
            System.out.println("i = " + i + "/" + loop);
            temp = Genetic_n_Queen.offspring;
            ArrayList<Pheno> selection = new ArrayList<>();
            start = System.nanoTime();
            while (temp-- != 0) {
                a = r.nextInt(Genetic_n_Queen.population);
                b = r.nextInt(Genetic_n_Queen.population);
                if (a == b) {
                    temp++;
                } else {
                    ArrayList<Pheno> cross = cross_over(a, b);
                    ArrayList<Pheno> clone = (ArrayList<Pheno>) cross.clone();
                    clone.forEach((p) -> {
//                        System.out.println("before mutate");
//                        p.show();
                        mutation(p);
//                        System.out.println("after mutate");
//                        p.show();
                    });
                    selection.addAll(clone);
                    selection.addAll(cross);
                }
            }
            Pheno[] gen = new Pheno[Genetic_n_Queen.population + selection.size()];
            System.arraycopy(generation, 0, gen, 0, generation.length);
//            System.out.println("gen = " + Arrays.toString(gen));
//            System.out.println("gen = " + Arrays.toString(Arrays.copyOfRange(gen, generation.length, gen.length)));
            for (int k = generation.length; k < gen.length; k++) {
                gen[k] = selection.get(k - generation.length);
            }
//            System.out.println("gen = " + Arrays.toString(Arrays.copyOf(gen, generation.length)));
//            System.out.println("gen = " + Arrays.toString(Arrays.copyOfRange(gen, generation.length, gen.length)));
//            sleep(5000);
            Arrays.sort(gen);
//            System.out.println("gen = " + Arrays.toString(gen));
            System.arraycopy(gen, 0, generation, 0, generation.length);
            boolean survive = r.nextFloat() > Genetic_n_Queen.randomize;
            int s = 1;
            HashSet<Integer> surviv = new HashSet<>();
            while (survive) {
                System.out.println("we have a survivor");
                int survivor = r.nextInt(selection.size()) + generation.length;
                while (surviv.contains(survivor)) {
                    survivor = r.nextInt(selection.size()) + generation.length;
                }
                surviv.add(survivor);
                generation[generation.length - s++] = gen[survivor];
                survive = r.nextFloat() > Genetic_n_Queen.randomize;
            }
            end = System.nanoTime();
            generation[0].show();
            generation[1].show();
            generations++;
            timestamp.add(end - start);
            if (generation[0].getCost() == 0 && !first) {
                first = true;
                firstAnswerTime = end - start;
            }
            System.out.println("end-start = " + (end - start));
//            sleep(500);
            System.out.println("==========================\n");
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Genetic_n_Queen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mutation(Pheno p) {
        int[] phen = p.getPhen();
        int temp = mutation;
        Random r = new Random();
        int a;
        int b;
        while (temp-- != 0) {
            a = r.nextInt(Genetic_n_Queen.queen);
            b = r.nextInt(Genetic_n_Queen.queen);
            if (a != b) {
//                System.out.println("phen = " + Arrays.toString(phen));
                phen[a] += phen[b];
                phen[b] = phen[a] - phen[b];
                phen[a] -= phen[b];
//                System.out.println("phen = " + Arrays.toString(phen));
            } else {
                temp++;
            }
        }
        p.setPhen(phen);
    }

    public ArrayList<Pheno> cross_over(int first, int second) {
        Pheno p = new Pheno(Genetic_n_Queen.queen, generations, -1);
        ArrayList<Pheno> ans = new ArrayList<>();
        Pheno fir = this.generation[first];
        int[] f = fir.getPhen();
        Pheno sec = this.generation[second];
        int[] s = sec.getPhen();

//        System.out.println("");
//        System.out.println("========================");
//        System.out.println("");
//        System.out.println("s = " + Arrays.toString(s));
//        System.out.println("f = " + Arrays.toString(f));
        int[] result = new int[Genetic_n_Queen.queen];

        System.arraycopy(f, 0, result, 0, Genetic_n_Queen.slice);
        System.arraycopy(s, Genetic_n_Queen.slice, result, Genetic_n_Queen.slice, Genetic_n_Queen.queen - Genetic_n_Queen.slice);
//        System.out.println("result = " + Arrays.toString(result));

        int[] repeat = new int[Genetic_n_Queen.slice];
        System.arraycopy(f, 0, repeat, 0, Genetic_n_Queen.slice);
//        System.out.println("repeat = " + Arrays.toString(repeat));

        int[] rep = new int[Genetic_n_Queen.queen - Genetic_n_Queen.slice];
        System.arraycopy(s, Genetic_n_Queen.slice, rep, 0, Genetic_n_Queen.queen - Genetic_n_Queen.slice);
//        System.out.println("rep = " + Arrays.toString(rep));

        int[] a = IntStream.range(0, Genetic_n_Queen.queen).toArray();
        ArrayList<Integer> said = new ArrayList<>();
        for (Integer t : a) {
            said.add(t);
        }
//        System.out.println("");
//        System.out.println("said = " + said);
        HashSet<Integer> ant = new HashSet<>();
        for (Integer t : repeat) {
            ant.add(t);
        }
        ArrayList<Integer> twice = new ArrayList<>();
        for (Integer t : rep) {
            if (ant.contains(t)) {
                twice.add(t);
            }
            ant.add(t);
        }
        for (int i = 0; i < said.size(); i++) {
            if (ant.contains(said.get(i))) {
                said.remove(i--);
            }
        }
//        System.out.println("ant = " + ant);
//        System.out.println("said = " + said);
//        System.out.println("twice = " + twice);
        for (int i = Genetic_n_Queen.slice, j = 0; i < Genetic_n_Queen.queen; i++) {
            if (twice.contains(result[i])) {
                result[i] = said.get(j++);
            }
        }
//        System.out.println("result = " + Arrays.toString(result));
        p.setPhen(result);
        ans.add(p);

//        System.out.println("");
//        System.out.println("==============================");
//        System.out.println("");
//        System.out.println("s = " + Arrays.toString(s));
//        System.out.println("f = " + Arrays.toString(f));
        p = new Pheno(Genetic_n_Queen.queen, generations, -1);
        result = new int[Genetic_n_Queen.queen];

        System.arraycopy(s, 0, result, 0, Genetic_n_Queen.slice);
        System.arraycopy(f, Genetic_n_Queen.slice, result, Genetic_n_Queen.slice, Genetic_n_Queen.queen - Genetic_n_Queen.slice);
//        System.out.println("result = " + Arrays.toString(result));

        repeat = new int[Genetic_n_Queen.slice];
        System.arraycopy(s, 0, repeat, 0, Genetic_n_Queen.slice);
//        System.out.println("repeat = " + Arrays.toString(repeat));

        rep = new int[Genetic_n_Queen.queen - Genetic_n_Queen.slice];
        System.arraycopy(f, Genetic_n_Queen.slice, rep, 0, Genetic_n_Queen.queen - Genetic_n_Queen.slice);
//        System.out.println("rep = " + Arrays.toString(rep));

        a = IntStream.range(0, Genetic_n_Queen.queen).toArray();
        said = new ArrayList<>();
        for (Integer t : a) {
            said.add(t);
        }
//        System.out.println("");
//        System.out.println("said = " + said);
        ant = new HashSet<>();
        for (Integer t : repeat) {
            ant.add(t);
        }
        twice = new ArrayList<>();
        for (Integer t : rep) {
            if (ant.contains(t)) {
                twice.add(t);
            }
            ant.add(t);
        }
        for (int i = 0; i < said.size(); i++) {
            if (ant.contains(said.get(i))) {
                said.remove(i--);
            }
        }
//        System.out.println("ant = " + ant);
//        System.out.println("said = " + said);
//        System.out.println("twice = " + twice);
        for (int i = Genetic_n_Queen.slice, j = 0; i < Genetic_n_Queen.queen; i++) {
            if (twice.contains(result[i])) {
                result[i] = said.get(j++);
            }
        }
//        System.out.println("result = " + Arrays.toString(result));
        p.setPhen(result);
        ans.add(p);

        return ans;
    }

}
