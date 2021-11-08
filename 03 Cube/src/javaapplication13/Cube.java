/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

/**
 *
 * @author Hamed Khashehchi
 */
public class Cube {
    private char c;

    public Cube(char c) {
        this.c = c;
    }

    public Cube() {
    }

    public void setC(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.c;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cube other = (Cube) obj;
        if (this.c != other.c) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return c+""; //To change body of generated methods, choose Tools | Templates.
    }
    
}
