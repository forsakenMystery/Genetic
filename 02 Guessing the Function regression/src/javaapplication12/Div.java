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
public class Div extends BinaryOperand {

    private final double Epsilon = 1e-7;

    public Div() {
    }
@Override
    public String toString() {
        return "("+this.left+" / "+this.right+")"; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void symbolicEval() {
        System.out.print("(");
        this.left.symbolicEval();
        System.out.print(") / (");
        this.right.symbolicEval();
        System.out.print(")");
    }

    @Override
    double numericalEval(double[] x) {
        double left = this.left.numericalEval(x);
        double right = this.right.numericalEval(x);
        if (Math.abs(right) > Epsilon) {
            return left / right;
        } else {
            return left / (right + Epsilon);
        }
    }

    @Override
    Node duplicate() {
        Div a = new Div();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

}
