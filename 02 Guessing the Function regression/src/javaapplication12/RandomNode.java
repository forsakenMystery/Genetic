/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.Random;

/**
 *
 * @author Hamed Khashehchi
 */
public class RandomNode {

    private static RandomNode r = new RandomNode();
    private final int MAX_RANDOM = 5;

    private RandomNode() {
    }

    public static RandomNode getR() {
        return r;
    }

    Node randomFunction(boolean functionMask[]) {
        Node t;
        int howMany = 0;
        for (int k = 0; k < functionMask.length; k++) {
            if (functionMask[k]) {
                howMany++;
            }
        }
        if (howMany < 1 || howMany > functionMask.length) {
            System.out.println("bad function");
        }
        Random r = new Random(System.nanoTime());
        int i = Math.abs(r.nextInt() % howMany) + 1;
        int j = 0;
        int l = 0;
        while (j < i) {
            if (functionMask[l]) {
                j++;
            }
            l++;
        }
        switch (l) {
            case 1:
                t = new Add();
                break;
            case 2:
                t = new Sub();
                break;
            case 3:
                t = new Div();
                break;
            case 4:
                t = new Cos();
                break;
            case 5:
                t = new Sin();
                break;
            case 6:
                t = new Power();
                break;
            case 7:
                t = new Mul();
                break;
            default:
                t = new Add();
        }
//        System.out.println(t);
        return t;
    }

    Node randomTerminal(int numIndepVars) {
        Node t;
        Random r = new Random(System.nanoTime());
        int i = Math.abs(r.nextInt() % (1 + numIndepVars));
        if (i < numIndepVars) {
            t = new Variable(i);
        } else {
            t = new Numeric(r.nextDouble() * MAX_RANDOM);
        }
        return t;
    }

    Node randomAnything(int numIndepVars, boolean functionMask[]) {
        Node t;
        Random r = new Random(System.nanoTime());
        int i = Math.abs(r.nextInt() % (functionMask.length + numIndepVars));
        if (i < numIndepVars) {
            t = randomTerminal(numIndepVars);
        } else {
            t = randomFunction(functionMask);
        }
        return t;
    }

}
