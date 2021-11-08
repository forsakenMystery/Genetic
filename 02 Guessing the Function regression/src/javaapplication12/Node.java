/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.Objects;

/**
 *
 * @author Hamed Khashehchi
 */
public abstract class Node {
    private Type type;
    private int depth;

    public Node(Type type) {
        this.type = type;
        this.depth = 0;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    
    
    
    abstract void symbolicEval();
    abstract double numericalEval(double x[]);
    abstract void addChild(Node n, int position);
    abstract void addRandomKids(int maxDepth, int numIndepVars, boolean functionMask[], Node parent);
    abstract Node duplicate();
    abstract PairPlus trace(int nodeNumber, int clipNumber);
    abstract int mySize();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + this.depth;
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
        final Node other = (Node) obj;
        if (this.depth != other.depth) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
    
    
}
