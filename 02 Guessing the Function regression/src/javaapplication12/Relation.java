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
public enum Relation {
    operand, left, right;
    
    public static int getRelation(Relation r){
        switch (r) {
            case operand:
                return 0;
            case left:
                return 1;
            case right:
                return 2;
            default:
                break;
        }
        return 3;
    }
}
