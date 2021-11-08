/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NQUEEN;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Hamed Khashehchi
 */
public class N_Queen {

    private int[] answer;
    private int n;
    private ArrayList<int[]> all;
    private ArrayList<Long> time;
    private long start;
    private long end;
    
    public N_Queen(int n) {
        this.answer = new int[n];
        this.n = n;
        all = new ArrayList<>();
        time = new ArrayList<>();
        start = System.nanoTime();
        placeQueens(0, n);
    }
    public long getWholeAnswerTime(){
        long sum=0;
        sum = time.stream().map((l) -> l).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }
    private boolean canPlace(int x, int y) {

        for (int i = 0; i < x; i++) {
            if ((answer[i] == y) || (Math.abs(i - x) == Math.abs(answer[i] - y))) {
                return false;
            }
        }
        return true;
    }

    private void placeQueens(int x, int size) {
        for (int i = 0; i < size; i++) {
            if (canPlace(x, i)) {
                answer[x] = i; 
                if (x == size - 1) {
                    end = System.nanoTime();
                    time.add(end-start);
                    all.add(answer.clone());
                    start = System.nanoTime();
                }
                placeQueens(x + 1, size);
            }
        }
    }

    public ArrayList<int[]> getAll() {
        return all;
    }

    public ArrayList<Long> getTime() {
        return time;
    }
    
    

}
