/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication11;

/**
 *
 * @author Hamed Khashehchi
 */
public enum Move {
    right, left, top, bottom, illegal;
    
    public static Move getBottom() {
        return bottom;
    }

    public static Move getLeft() {
        return left;
    }

    public static Move getRight() {
        return right;
    }

    public static Move getTop() {
        return top;
    }

    public static Move getIllegal() {
        return illegal;
    }
    
    
}
