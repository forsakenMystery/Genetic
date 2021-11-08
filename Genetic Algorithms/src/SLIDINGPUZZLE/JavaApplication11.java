/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

/**
 *
 * @author Hamed Khashehchi
 */
public class JavaApplication11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Board b = new Board();
//        System.out.println("b = " + b);
//        b.move(Move.right);
//        System.out.println("b = " + b);
//        b.move(Move.top);
//        System.out.println("b = " + b);
//        b.move(Move.bottom);
//        System.out.println("b = " + b);
//        b.move(Move.bottom);
//        System.out.println("b = " + b);
//        b.move(Move.left);
//        System.out.println("b = " + b);
        Board b = new Board();
        b.randomWalk();
        System.out.println("randomWalk = " + b);
        GeneticNSlidingPuzzle gc = new GeneticNSlidingPuzzle(b, 1024);
//        System.out.println("b = " + b);
//        Board s = new Board(b);
//        System.out.println("s = " + s);
//        s.move(Move.top);
//        s.move(Move.top);
//        s.move(Move.bottom);
//        System.out.println("s = " + s);
//        System.out.println(s.possible());
//        System.out.println("s = " + s);
//        System.out.println("b = " + b);
//        System.out.println(b.fitness());
//        System.out.println(b.getMoveTillNow());
//        System.out.println(s.getMoveTillNow());
    }
    
}
