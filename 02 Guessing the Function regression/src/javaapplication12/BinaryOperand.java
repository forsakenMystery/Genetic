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
public abstract class BinaryOperand extends Node {

    protected Node left;
    protected Node right;

    public BinaryOperand() {
        super(Type.function);
        this.left = null;
        this.right = null;
    }

    public Node getLeft() {
        return left;
    }

    @Override
    int mySize() {
        int size = 1;
        size += left.mySize();
        size += right.mySize();
        return size;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    abstract void symbolicEval();

    @Override
    abstract double numericalEval(double[] x);

    @Override
    void addChild(Node n, int position) {
        if (position == 1) {
            this.left = n;
//            System.out.println(n.getClass());
            n.setDepth(n.getDepth() + 1);
        } else if (position == 2) {
            this.right = n;
//            System.out.println(n.getClass());
            n.setDepth(n.getDepth() + 1);
        } else if (position == 0) {
            System.out.println("wtf");
        } else {
            throw new UnsupportedOperationException("None.");
        }
    }

    @Override
    void addRandomKids(int maxDepth, int numIndepVars, boolean[] functionMask, Node parent) {
        Node c1, c2;
        if (getDepth() < maxDepth - 1) {
//            System.out.println(getDepth());
//            System.out.println("maxDepth = " + maxDepth);
            c1 = RandomNode.getR().randomAnything(numIndepVars, functionMask);
            if (parent.getClass() == Cos.class || parent.getClass() == Sin.class) {
                c1 = new Numeric(1);
            }
            addChild(c1, 1);
            if (c1.getType() == Type.function) {
                c1.addRandomKids(maxDepth - 1, numIndepVars, functionMask, c1);
            }
            addChild(c2 = RandomNode.getR().randomAnything(numIndepVars, functionMask), 2);
            if (c2.getType() == Type.function) {
                c2.addRandomKids(maxDepth - 1, numIndepVars, functionMask, c2);
            }
        } else {
            c1 = RandomNode.getR().randomTerminal(numIndepVars);
            if (parent.getClass() == Cos.class || parent.getClass() == Sin.class) {
                c1 = new Numeric(1);
            }
            addChild(c1, 1);
            addChild(c2 = RandomNode.getR().randomTerminal(numIndepVars), 2);
        }
    }

    @Override
    abstract Node duplicate();

    @Override
    PairPlus trace(int nodeNumber, int clipNumber) {
//        System.out.println("==========================");
//        System.out.println(this.getClass());
        if(clipNumber==1){
            return new PairPlus(null, this, 1, Relation.operand);
        }
        if (nodeNumber + 1 == clipNumber) {
            return new PairPlus(this, left, clipNumber, Relation.left);
        }
        PairPlus testLeft = left.trace(nodeNumber + 1, clipNumber);
        if (testLeft.getPredecessor() != null) // found it down left side
        {
            return testLeft;
        }
        if (testLeft.getCounter() + 1 == clipNumber) {
            return new PairPlus(this, right, clipNumber, Relation.right);
        }
        PairPlus testRight = right.trace(testLeft.getCounter() + 1, clipNumber);
        return testRight;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.left);
        hash = 47 * hash + Objects.hashCode(this.right);
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
        final BinaryOperand other = (BinaryOperand) obj;
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return true;
    }

}
