/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Hamed Khashehchi
 */
public class Card extends Pane {

    private Text t;
    private Rectangle r;
    private final int size = 160;

    public Card(int board) {
        String s = "";
        if (board != 0) {
            s += board;
        }
        r = new Rectangle(size, size, Paint.valueOf("white"));
        t = new Text(s);
        
        super.getChildren().add(r);
        super.getChildren().add(t);
    }
    
    public void setX(double a){
        super.setLayoutX(a);
        r.setX(a);
        t.setX(a+size/2);
    }
    
    public void setY(double a){
        super.setLayoutY(a);
        r.setY(a);
        t.setY(a+size/2);
    }
    
}
