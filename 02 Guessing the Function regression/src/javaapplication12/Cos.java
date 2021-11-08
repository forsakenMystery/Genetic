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
public class Cos extends BinaryOperand {
@Override
    public String toString() {
        return "cos("+this.left+" . "+this.right+")"; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    void symbolicEval() {
        System.out.print("{ Cos[ (");
        this.left.symbolicEval();
        System.out.print(") . (");
        this.right.symbolicEval();
        System.out.print(") ] }");
    }

    @Override
    double numericalEval(double[] x) {
        return Math.cos(this.left.numericalEval(x) * this.right.numericalEval(x));
    }

    @Override
    Node duplicate() {
        Cos a = new Cos();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

}
