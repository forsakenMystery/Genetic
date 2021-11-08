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
public class Mul extends BinaryOperand{

    public Mul() {
    }

    @Override
    public void symbolicEval() {
        System.out.print("(");
        this.left.symbolicEval();
        System.out.print(") * (");
        this.right.symbolicEval();
        System.out.print(")");
    }

    @Override
    double numericalEval(double[] x) {
        return this.left.numericalEval(x) * this.right.numericalEval(x); 
    }

    @Override
    Node duplicate() {
        Mul a = new Mul();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

    @Override
    public String toString() {
        return "mul("+this.left+", "+this.right+")"; //To change body of generated methods, choose Tools | Templates.
    }
    
}