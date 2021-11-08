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
public class Data {

    private double x1;
    private double x2;
    private double y;

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY() {
        return y;
    }

    public Data(double x1, double x2, double y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x1 + ", " + x2 + ")->" + y; //To change body of generated methods, choose Tools | Templates.
    }

}
