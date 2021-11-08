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
public class PairPlus {
    private Node predecessor;
    private Node target;
    private int counter;
    private Relation relation;

    @Override
    public String toString() {
        return counter+", "+relation+" => "+predecessor+", "+target; //To change body of generated methods, choose Tools | Templates.
    }

    public PairPlus(Node predecessor, Node target, int counter, Relation relation) {
        this.predecessor = predecessor;
        this.target = target;
        this.counter = counter;
        this.relation = relation;
    }

    public PairPlus() {
    }

    /**
     * @return the predecessor
     */
    public Node getPredecessor() {
        return predecessor;
    }

    /**
     * @param predecessor the predecessor to set
     */
    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * @return the target
     */
    public Node getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Node target) {
        this.target = target;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @return the relation
     */
    public Relation getRelation() {
        return relation;
    }

    /**
     * @param relation the relation to set
     */
    public void setRelation(Relation relation) {
        this.relation = relation;
    }
    
    
}
