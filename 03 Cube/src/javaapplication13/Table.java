/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Hamed Khashehchi
 */
public class Table {

    private Stack<Cube> s1 = new Stack<>();
    private Stack<Cube> s2 = new Stack<>();
    private ArrayList<Cube> table = new ArrayList<>();
    private ArrayList<Cube> answer = new ArrayList<>();
    private static String ans = "khajenasir";
    private boolean corrct;
    private boolean finish;
    private boolean eval;

    public boolean isCorrct() {
        return corrct;
    }

    public static String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    public Table() {
        Random r = new Random();
        corrct = false;
        finish = false;
        eval = false;
        String sss = shuffleString(ans);
//        System.out.println("sss = " + sss);
        int nextInt = r.nextInt(ans.length() - 1) + 1;
//        System.out.println("nextInt = " + nextInt);
        String s11 = sss.substring(0, nextInt);
//        System.out.println("s11 = " + s11);
        String s22 = sss.substring(nextInt, ans.length());
//        System.out.println("s22 = " + s22);
        for (int i = 0; i < s11.length(); i++) {
            s1.add(new Cube(s11.charAt(i)));
        }
        for (int i = 0; i < s22.length(); i++) {
            s2.add(new Cube(s22.charAt(i)));
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Table t = new Table();
        t.s1 = (Stack<Cube>) s1.clone();
        t.s2 = (Stack<Cube>) s2.clone();
        corrct = false;
        eval = false;
        finish = false;
        return t; //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isFinish() {
        return finish;
    }

    public void move(String move, char c) {
//        System.out.println("=================");
        if (!finish) {
            if (move.equals("push")) {
                Cube peek = null, peek1 = null;
                if (!s1.empty()) {
                    peek = s1.peek();
                }
                if (!s2.empty()) {
                    peek1 = s2.peek();
                }
//            System.out.println(peek);
//            System.out.println(peek1);

//            System.out.println(c);
                if (!s1.empty() && peek.getC() == c) {
                    answer.add(s1.pop());
                } else if (!s2.empty() && peek1.getC() == c) {
                    answer.add(s2.pop());
                } else if (table.contains(new Cube(c))) {
                    answer.add(new Cube(c));
                    table.remove(new Cube(c));
                }
            } else if (move.equals("pop")) {
                Cube peek = null, peek1 = null;
                if (!s1.empty()) {
                    peek = s1.peek();
                }
                if (!s2.empty()) {
                    peek1 = s2.peek();
                }

//            System.out.println(peek);
//            System.out.println(peek1);
//            System.out.println(c);
                if (!s1.empty() && peek.getC() == c) {
                    table.add(s1.pop());
                } else if (!s2.empty() && peek1.getC() == c) {
                    table.add(s2.pop());
                }
            }
            if (s1.empty() && s2.empty() && table.isEmpty()) {
                finish = true;
            }
        }
        if (finish && !eval) {
            eval = true;
            String sos = "";
            for (Cube cc : answer) {
                sos += cc.getC();
            }
//            System.out.println("sos = " + sos);
//            System.out.println("ans = " + ans);
            corrct = sos.equals(ans);
//            System.out.println("correct = " + corrct);
        }
    }

    public static String getAns() {
        return ans;
    }

    @Override
    public String toString() {
        return "first stack= " + s1.toString() + ", second stack= " + s2.toString() + ", table= " + table.toString() + ", answer= " + answer; //To change body of generated methods, choose Tools | Templates.
    }

}
