/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Hamed Khashehchi
 */
public class JavaApplication13 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO code application logic here
//        Table t = new Table();
//        Pheno pheno = new Pheno(t);
//        System.out.println(pheno.getFitness());
        Genetic g = new Genetic(2048);
//        ArrayList<Pair<String, Character>> p = new ArrayList<>();
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("pop", 'k'));
//        p.add(new Pair<>("pop", 'h'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 'j'));
//        p.add(new Pair<>("pop", 'e'));
//        p.add(new Pair<>("pop", 'n'));
//        p.add(new Pair<>("pop", 'a'));
//        p.add(new Pair<>("pop", 's'));
//        p.add(new Pair<>("pop", 'i'));
//        p.add(new Pair<>("pop", 'r'));
//        
//        p.add(new Pair<>("push", 'k'));
//        p.add(new Pair<>("push", 'h'));
//        p.add(new Pair<>("push", 'a'));
//        p.add(new Pair<>("push", 'j'));
//        p.add(new Pair<>("push", 'e'));
//        p.add(new Pair<>("push", 'n'));
//        p.add(new Pair<>("push", 'a'));
//        p.add(new Pair<>("push", 's'));
//        p.add(new Pair<>("push", 'i'));
//        p.add(new Pair<>("push", 'r'));
//        pheno.setMoves(p);
//        System.out.println("pheno = " + pheno);
//        System.out.println("t = " + t);
//        
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        t.move("pop", 'h');
//        t.move("pop", 'a');
//        t.move("pop", 'j');
//        t.move("pop", 'e');
//        t.move("pop", 'n');
//        t.move("pop", 'a');
//        t.move("pop", 's');
//        t.move("pop", 'i');
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        t.move("push", 'k');
//        System.out.println("t = " + t);
//        t.move("push", 'h');
//        System.out.println("t = " + t);
//        t.move("push", 'a');
//        System.out.println("t = " + t);
//        t.move("push", 'j');
//        System.out.println("t = " + t);
//        t.move("push", 'e');
//        System.out.println("t = " + t);
//        t.move("push", 'n');
//        System.out.println("t = " + t);
//        t.move("push", 'a');
//        System.out.println("t = " + t);
//        t.move("push", 's');
//        System.out.println("t = " + t);
//        t.move("push", 'i');
//        System.out.println("t = " + t);
//        t.move("push", 'r');
//        System.out.println("t = " + t);
//        System.out.println(t.isFinish());
//        System.out.println(pheno.getFitness());
//        t.move("pop", 'c');
//        System.out.println("t = " + t);
//        t.move("push", 'k');
//        System.out.println("t = " + t);
//        t.move("pop", 'k');
//        System.out.println("t = " + t);
//        t.move("push", 'r');
//        System.out.println("t = " + t);
//        t.move("pop", 'r');
//        System.out.println("t = " + t);
//        
//        t.move("pop", 'a');
//        System.out.println("t = " + t);
//        
//        t.move("pop", 'h');
//        System.out.println("t = " + t);
//        
    }

}
