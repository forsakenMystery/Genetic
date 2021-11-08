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
public class Pair {

    private Integer key;

    public Integer getKey() {
        return key;
    }

    private Pheno value;

    public Pheno getValue() {
        return value;
    }

    public Pair(Integer key, Pheno value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=>" + value;
    }

    @Override
    public int hashCode() {

        return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (key != null ? !key.equals(pair.key) : pair.key != null) {
                return false;
            }
            if (value != null ? !value.equals(pair.value) : pair.value != null) {
                return false;
            }
            return true;
        }
        return false;
    }
}
