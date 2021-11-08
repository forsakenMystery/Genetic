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
public class Add extends BinaryOperand{

    public Add() {
    }

    @Override
    public void symbolicEval() {
        System.out.print("(");
        this.left.symbolicEval();
        System.out.print(") + (");
        this.right.symbolicEval();
        System.out.print(")");
    }

    @Override
    double numericalEval(double[] x) {
        return this.left.numericalEval(x) + this.right.numericalEval(x); 
    }

    @Override
    Node duplicate() {
        Add a = new Add();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

    @Override
    public String toString() {
        return "add("+this.left+", "+this.right+")"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
