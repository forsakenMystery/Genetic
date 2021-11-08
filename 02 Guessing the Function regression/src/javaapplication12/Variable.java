/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.Arrays;

/**
 *
 * @author Hamed Khashehchi
 */
public class Variable extends Node {

    private int index;

    public Variable(int index) {
        super(Type.terminal);
        this.index = index;
    }

    @Override
    public void symbolicEval() {
        System.out.print("X" + (index + 1));
    }

    @Override
    public double numericalEval(double[] x) {
//        System.out.println("index = " + index);
//        System.out.println("x = " + Arrays.toString(x));
        return x[index];
    }

    @Override
    public void addRandomKids(int maxDepth, int numIndepVars, boolean[] functionMask, Node parent) {
        throw new UnsupportedOperationException("Not supported. ffs it's a terminal");
    }

    @Override
    public Node duplicate() {
        return new Variable(index);
    }

    @Override
    public PairPlus trace(int nodeNumber, int clipNumber) {
        return new PairPlus(null, null, nodeNumber, Relation.left);
    }

    @Override
    public void addChild(Node n, int position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    int mySize() {
        return 1;
    }

    @Override
    public String toString() {
        return "X"+(index+1); //To change body of generated methods, choose Tools | Templates.
    }
}
