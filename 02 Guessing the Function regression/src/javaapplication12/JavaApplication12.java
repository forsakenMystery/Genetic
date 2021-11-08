/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.Random;

/**
 *
 * @author Hamed Khashehchi
 */
public class JavaApplication12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO code application logic here
//        System.out.println("root1");
//        
//        Node root1 = new Add();
//        boolean functionMask[] = {true, true, true, true, true, true, true};
//        root1.addRandomKids(3, 2, functionMask, root1);
//        root1.symbolicEval();
//        
//        System.out.println("");
//        System.out.println("root2");
//        
//        Node root2 = new Sub();
//        root2.addRandomKids(3, 2, functionMask, root2);
//        root2.symbolicEval();
//        
//        System.out.println("");
//        System.out.println("");
//        
//        System.out.println("mutation");
//        
//        System.out.println("");
//        System.out.println("root1");
//        
//        Genetic g = new Genetic();
////        g.crossover(root1, root2);
//        Node n = g.mutation(root1);
//        if(n!=null){
//            root1 = n;
//        }
//        n = g.mutation(root2);
//        if(n!=null){
//            root2 = n;
//        }
//        root1.symbolicEval();
//        
//        System.out.println("");
//        
//        System.out.println("root2");
//        
//        root2.symbolicEval();
//        
//        System.out.println("");
//        System.out.println("");
//        System.out.println("mutation");
//        System.out.println("");
//        n = g.mutation(root1);
//        if(n!=null){
//            root1 = n;
//        }
//        n = g.mutation(root2);
//        if(n!=null){
//            root2 = n;
//        }
//        root1.symbolicEval();
//        
//        System.out.println("");
//        
//        System.out.println("root2");
//        
//        root2.symbolicEval();
//        
//        System.out.println("");
        
        Genetic g = new Genetic(512);
        
        
    }

}
