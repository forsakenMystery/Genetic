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
public class Power extends BinaryOperand {

    @Override
    void symbolicEval() {
        System.out.print("(");
        this.left.symbolicEval();
        System.out.print(") ^ (");
        this.right.symbolicEval();
        System.out.print(")");
    }

    @Override
    double numericalEval(double[] x) {
//        System.out.println("left part of power = " + this.left.numericalEval(x));
//        System.out.println("right part of power = " + this.right.numericalEval(x));
//        System.out.println("my evaluation = " + Math.pow(this.left.numericalEval(x), this.right.numericalEval(x)));
//        System.out.println("******");
        return Math.pow(this.left.numericalEval(x), this.right.numericalEval(x));
    }

    @Override
    Node duplicate() {
        Power a = new Power();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

    @Override
    public String toString() {
        return "(" + this.left + " ^ " + this.right + ")"; //To change body of generated methods, choose Tools | Templates.
    }

}
