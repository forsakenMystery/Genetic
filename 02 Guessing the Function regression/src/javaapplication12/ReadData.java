/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamed Khashehchi
 */
public class ReadData {

    private ArrayList<Data> data;

    public int lengthData() {
        return data.size();
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public Data getData(int i) {
        return data.get(i);
    }

    public ReadData() {
        BufferedReader br = null;
        try {
            data = new ArrayList<>();
            File file = new File("E:\\Code\\Java\\JavaApplication12\\src\\javaapplication12\\sample.txt");
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
//                System.out.println(st);
                String[] split = st.split(",");
                double x1 = Double.parseDouble(split[0]);
                double x2 = Double.parseDouble(split[1]);
                double y = Double.parseDouble(split[2]);
                Data a = new Data(x1, x2, y);
                data.add(a);
//                System.out.println("a = " + a);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
