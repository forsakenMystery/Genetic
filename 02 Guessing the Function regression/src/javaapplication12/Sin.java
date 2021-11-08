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
public class Sin extends BinaryOperand {
@Override
    public String toString() {
        return "Sin("+this.left+" . "+this.right+")"; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    void symbolicEval() {
        System.out.print("{ Sin[ (");
        this.left.symbolicEval();
        System.out.print(") . (");
        this.right.symbolicEval();
        System.out.print(") ] }");
    }

    @Override
    double numericalEval(double[] x) {
        return Math.sin(this.left.numericalEval(x) * this.right.numericalEval(x));
    }

    @Override
    Node duplicate() {
        Sin a = new Sin();
        a.setLeft(this.left.duplicate());
        a.setRight(this.right.duplicate());
        return a;
    }

}
