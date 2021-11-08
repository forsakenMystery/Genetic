/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

/**
 *
 * @author Hamed Khashehchi
 */
public class Numeric extends Node {

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }

    public Numeric(double value) {
        super(Type.terminal);
        this.value = value;
    }

    @Override
    public void symbolicEval() {
        System.out.print(this.value);
    }

    @Override
    public double numericalEval(double[] x) {
        return this.value;
    }

    @Override
    public void addRandomKids(int maxDepth, int numIndepVars, boolean[] functionMask, Node parent) {
        throw new UnsupportedOperationException("Not supported. It is terminal ffs");
    }

    @Override
    public Node duplicate() {
        return new Numeric(this.value);
    }

    @Override
    public PairPlus trace(int nodeNumber, int clipNumber) {
        return new PairPlus(null, null, nodeNumber, Relation.left);
    }

    @Override
    public void addChild(Node n, int position) {
        throw new UnsupportedOperationException("Not supported. It's terminal ffs");
    }

    @Override
    int mySize() {
        return 1;
    }

}
