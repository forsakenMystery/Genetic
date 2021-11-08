/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Hamed Khashehchi
 */
public class Pheno implements Comparable<Pheno>{

    private Node node;
    private Double cost;
    private static ArrayList<Data> data;
    private static final int FUNCTION_NUMBER = 7;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Pheno p = new Pheno(FUNCTION_NUMBER);
        p.setNode(this.getNode().duplicate());
        return p; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public void setNode(Node node) {
        this.node = node;
        fitting();
    }

    public Node getNode() {
        return node;
    }

    public Double getCost() {
        return cost;
    }
    static{
        ReadData rd = new ReadData();
        data = rd.getData();
    }

    public Pheno(Node node) {
        this.node = node;
        fitting();
    }
    
    
    public Pheno(int maxDepth) {
        
        boolean functionMask[] = new boolean[FUNCTION_NUMBER];
        Random r = new Random(System.nanoTime());

//        boolean functionMask[] = {true, false, false, false, false, true};
        boolean flag = false;
        for (int i = 0; i < FUNCTION_NUMBER; i++) {
            functionMask[i] = r.nextBoolean();
            if(functionMask[i]){
                flag=true;
            }
        }
        if(!flag){
            functionMask[r.nextInt(FUNCTION_NUMBER)] = true;
        }
        this.node = RandomNode.getR().randomFunction(functionMask);
        this.node.addRandomKids(maxDepth, 2, functionMask, this.node);
//        this.node.symbolicEval();
//        System.out.println("");
        fitting();
    }

    private void fitting() {
        cost = 0d;
        for (Data d : data) {
//            System.out.println("=============");
            double x[] = {d.getX1(), d.getX2()};
//            System.out.println("x = " + Arrays.toString(x));
            double f = node.numericalEval(x);
//            System.out.println("f = " + f);
            double err = (f - d.getY()) * (f - d.getY());
//            System.out.println("err = " + err);
            if (Double.isNaN(err) || Double.isInfinite(err)) {
                this.cost = Double.MAX_VALUE;
                break;
            } else {
                this.cost += err;
            }
        }
//        System.out.println("this.cost = " + this.cost);
    }

    @Override
    public int compareTo(Pheno t) {
        return Double.compare(this.cost, t.cost);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.node);
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
        final Pheno other = (Pheno) obj;
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        this.node.symbolicEval();
        return this.node+" -> "+ this.getCost()+" "; //To change body of generated methods, choose Tools | Templates.
    }
    
}
