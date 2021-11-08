/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import genetic.algorithms.FXMLDocumentController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Hamed Khashehchi
 */
public class FXML_SLIDINGController implements Initializable {

    private HashSet<Pheno> allTheAnswers;
    private Pheno theBestAnswer;
    private Board b;
    private int slide = 0;
    private Text[][] TT;
    private GeneticNSlidingPuzzle gc;
    private int move = 0;
    private Board playable;
    private boolean best;
    public static Stage sex;
    public static ArrayList<Float> allMeanPopulation;
    public static ArrayList<Float> allBestPopulation;
    @FXML
    private Label lbl;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button configuration;
    @FXML
    private Button diagnosis;
    @FXML
    private Button exit;
    @FXML
    private Slider N;
    @FXML
    private ImageView play;
    @FXML
    private ImageView step;
    @FXML
    private Button show;
    @FXML
    private Button show_best;
    @FXML
    private Button Run;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        play.setOnMouseEntered((MouseEvent event) -> {
            play.setOpacity(.5);
        });
        play.setOnMouseExited((MouseEvent event) -> {
            play.setOpacity(1);
        });
        step.setOnMouseEntered((MouseEvent event) -> {
            step.setOpacity(.5);
        });
        step.setOnMouseExited((MouseEvent event) -> {
            step.setOpacity(1);
        });
    }

    @FXML
    private void showClicked(MouseEvent event) throws CloneNotSupportedException {
        best = false;
        pane.getChildren().clear();
        System.out.println("b = " + b);
        move = 0;
        int[][] board = b.getBoard();
        Image img = new Image("wooden.jpg");
        ImageView im = new ImageView(img);
        im.setFitHeight(520);
        im.setFitWidth(520);
        pane.getChildren().add(im);
        TT = new Text[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                r.setX(j * 160 + 10 + j * 10);
                r.setY(i * 160 + 10 + i * 10);
                Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                t.setFont(new Font(27));
                TT[i][j] = t;
                t.setX(j * 160 + 85 + j * 10);
                t.setY(i * 160 + 85 + i * 10);
                pane.getChildren().add(r);
                pane.getChildren().add(t);
            }
        }
        lbl.setText(((slide % allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
        playable = (Board) b.clone();

        Image image = new Image(getClass().getResourceAsStream("right.png"));
        Button right = new Button();
        right.setGraphic(new ImageView(image));
        right.setLayoutX(450);
        right.setLayoutY(200);
        right.setOpacity(0.8);

        image = new Image(getClass().getResourceAsStream("left.png"));
        Button left = new Button();
        left.setGraphic(new ImageView(image));
        left.setLayoutX(0);
        left.setLayoutY(200);
        left.setOpacity(0.8);
        right.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    slide++;
                    move = 0;
                    pane.getChildren().clear();
                    lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                    System.out.println("wtf");
                    playable = (Board) b.clone();
                    int[][] board = playable.getBoard();
                    pane.getChildren().add(im);
                    TT = new Text[board.length][board.length];
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                            r.setX(j * 160 + 10 + j * 10);
                            r.setY(i * 160 + 10 + i * 10);
                            Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                            t.setFont(new Font(27));
                            TT[i][j] = t;
                            t.setX(j * 160 + 85 + j * 10);
                            t.setY(i * 160 + 85 + i * 10);
                            pane.getChildren().add(r);
                            pane.getChildren().add(t);
                        }
                    }
                    pane.getChildren().add(right);
                    pane.getChildren().add(left);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        left.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    slide--;
                    move = 0;
                    pane.getChildren().clear();
                    lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                    System.out.println("wtf");
                    playable = (Board) b.clone();
                    int[][] board = playable.getBoard();
                    pane.getChildren().add(im);
                    TT = new Text[board.length][board.length];
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                            r.setX(j * 160 + 10 + j * 10);
                            r.setY(i * 160 + 10 + i * 10);
                            Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                            t.setFont(new Font(27));
                            TT[i][j] = t;
                            t.setX(j * 160 + 85 + j * 10);
                            t.setY(i * 160 + 85 + i * 10);
                            pane.getChildren().add(r);
                            pane.getChildren().add(t);
                        }
                    }
                    pane.getChildren().add(right);
                    pane.getChildren().add(left);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pane.getChildren().add(left);
        pane.getChildren().add(right);

    }

    @FXML
    private void configurationClicked(MouseEvent event) {
    }

    @FXML
    private void diagnosisClicked(MouseEvent event) throws IOException {
        sex = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/SLIDINGPUZZLE/FXML_DIAGNOSIS.fxml"));

        Scene scene = new Scene(root);
        sex.setTitle("Charts");
        sex.getIcons().add(new Image("chart.png"));
        sex.setScene(scene);
        sex.show();
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        FXMLDocumentController.se.close();
    }

    @FXML
    private void playClicked(MouseEvent event) throws InterruptedException {
        System.out.println("play");
        Pheno p;
        if (best) {
            p = gc.getTheBestAnswer();
        } else {
            p = (Pheno) gc.getAllTheAnswers().toArray()[slide];
        }
        System.out.println("p = " + p);
        System.out.println("playable = " + playable);
        if (move < p.getAnswer().size()) {
            PauseTransition wait = new PauseTransition(Duration.millis(50));
            wait.setCycleCount(p.getAnswer().size() - move - 1);
            System.out.println(p.getAnswer().size());
            wait.setOnFinished((e) -> {
                playable.move(p.getAnswer().get(move++));
                System.out.println("move = " + move);
                pane.getChildren().clear();
                int[][] board = playable.getBoard();
                Image img = new Image("wooden.jpg");
                ImageView im = new ImageView(img);
                im.setFitHeight(520);
                im.setFitWidth(520);
                pane.getChildren().add(im);
                TT = new Text[board.length][board.length];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                        r.setX(j * 160 + 10 + j * 10);
                        r.setY(i * 160 + 10 + i * 10);
                        Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                        t.setFont(new Font(27));
                        TT[i][j] = t;
                        t.setX(j * 160 + 85 + j * 10);
                        t.setY(i * 160 + 85 + i * 10);
                        pane.getChildren().add(r);
                        pane.getChildren().add(t);
                    }
                }
                Image image = new Image(getClass().getResourceAsStream("right.png"));
                Button right = new Button();
                right.setGraphic(new ImageView(image));
                right.setLayoutX(450);
                right.setLayoutY(200);
                right.setOpacity(0.8);

                image = new Image(getClass().getResourceAsStream("left.png"));
                Button left = new Button();
                left.setGraphic(new ImageView(image));
                left.setLayoutX(0);
                left.setLayoutY(200);
                left.setOpacity(0.8);
                if (best) {
                    // do nothing
                } else {

                    right.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                slide++;
                                move = 0;
                                pane.getChildren().clear();
                                lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                                System.out.println("wtf");
                                playable = (Board) b.clone();
                                int[][] board = playable.getBoard();
                                pane.getChildren().add(im);
                                TT = new Text[board.length][board.length];
                                for (int i = 0; i < board.length; i++) {
                                    for (int j = 0; j < board[i].length; j++) {
                                        Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                                        r.setX(j * 160 + 10 + j * 10);
                                        r.setY(i * 160 + 10 + i * 10);
                                        Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                                        t.setFont(new Font(27));
                                        TT[i][j] = t;
                                        t.setX(j * 160 + 85 + j * 10);
                                        t.setY(i * 160 + 85 + i * 10);
                                        pane.getChildren().add(r);
                                        pane.getChildren().add(t);
                                    }
                                }
                                pane.getChildren().add(right);
                                pane.getChildren().add(left);
                            } catch (CloneNotSupportedException ex) {
                                Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    left.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                slide--;
                                move = 0;
                                pane.getChildren().clear();
                                lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                                System.out.println("wtf");
                                playable = (Board) b.clone();
                                int[][] board = playable.getBoard();
                                pane.getChildren().add(im);
                                TT = new Text[board.length][board.length];
                                for (int i = 0; i < board.length; i++) {
                                    for (int j = 0; j < board[i].length; j++) {
                                        Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                                        r.setX(j * 160 + 10 + j * 10);
                                        r.setY(i * 160 + 10 + i * 10);
                                        Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                                        t.setFont(new Font(27));
                                        TT[i][j] = t;
                                        t.setX(j * 160 + 85 + j * 10);
                                        t.setY(i * 160 + 85 + i * 10);
                                        pane.getChildren().add(r);
                                        pane.getChildren().add(t);
                                    }
                                }
                                pane.getChildren().add(right);
                                pane.getChildren().add(left);
                            } catch (CloneNotSupportedException ex) {
                                Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    pane.getChildren().add(right);
                    pane.getChildren().add(left);
                }
                if (move > p.getAnswer().size() - 1) {
                    wait.stop();
                    left.setDisable(false);
                    right.setDisable(false);
                } else {
                    wait.play();
                    left.setDisable(true);
                    right.setDisable(true);

                }
            });
            wait.play();
        }

    }

    @FXML
    private void stepClicked(MouseEvent event) {
//        Duration startDuration = Duration.ZERO;
//        Duration endDuration = Duration.seconds(0.5);
        Pheno p;
        if (best) {
            p = gc.getTheBestAnswer();
        } else {
            p = (Pheno) gc.getAllTheAnswers().toArray()[slide];
        }
        System.out.println("p = " + p);
        System.out.println("playable = " + playable);
        if (move == p.getAnswer().size()) {
            move--;
        }
        playable.move(p.getAnswer().get(move++));
        System.out.println("move = " + move);
//        for (Move m : p.getAnswer()) {
//            playable.move(m);
//        }
//        System.out.println("playable = " + playable);

        pane.getChildren().clear();
        int[][] board = playable.getBoard();
        Image img = new Image("wooden.jpg");
        ImageView im = new ImageView(img);
        im.setFitHeight(520);
        im.setFitWidth(520);
        pane.getChildren().add(im);
        TT = new Text[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                r.setX(j * 160 + 10 + j * 10);
                r.setY(i * 160 + 10 + i * 10);
                Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                t.setFont(new Font(27));
                TT[i][j] = t;
                t.setX(j * 160 + 85 + j * 10);
                t.setY(i * 160 + 85 + i * 10);
                pane.getChildren().add(r);
                pane.getChildren().add(t);
            }
        }
        if (best) {
            // do nothing
        } else {
            Image image = new Image(getClass().getResourceAsStream("right.png"));
            Button right = new Button();
            right.setGraphic(new ImageView(image));
            right.setLayoutX(450);
            right.setLayoutY(200);
            right.setOpacity(0.8);

            image = new Image(getClass().getResourceAsStream("left.png"));
            Button left = new Button();
            left.setGraphic(new ImageView(image));
            left.setLayoutX(0);
            left.setLayoutY(200);
            left.setOpacity(0.8);

            right.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        slide++;
                        move = 0;
                        pane.getChildren().clear();
                        lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                        System.out.println("ftw");
                        playable = (Board) b.clone();
                        int[][] board = playable.getBoard();
                        pane.getChildren().add(im);

                        TT = new Text[board.length][board.length];
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                                r.setX(j * 160 + 10 + j * 10);
                                r.setY(i * 160 + 10 + i * 10);
                                Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                                t.setFont(new Font(27));
                                TT[i][j] = t;
                                t.setX(j * 160 + 85 + j * 10);
                                t.setY(i * 160 + 85 + i * 10);
                                pane.getChildren().add(r);
                                pane.getChildren().add(t);
                            }
                        }
                        pane.getChildren().add(right);
                        pane.getChildren().add(left);
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            left.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        slide--;
                        move = 0;
                        pane.getChildren().clear();
                        lbl.setText((Math.floorMod(slide, allTheAnswers.size()) + 1) + "/" + allTheAnswers.size());
                        System.out.println("ftw");
                        playable = (Board) b.clone();
                        int[][] board = playable.getBoard();
                        pane.getChildren().add(im);
                        TT = new Text[board.length][board.length];
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                                r.setX(j * 160 + 10 + j * 10);
                                r.setY(i * 160 + 10 + i * 10);
                                Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                                t.setFont(new Font(27));
                                TT[i][j] = t;
                                t.setX(j * 160 + 85 + j * 10);
                                t.setY(i * 160 + 85 + i * 10);
                                pane.getChildren().add(r);
                                pane.getChildren().add(t);
                            }
                        }
                        pane.getChildren().add(right);
                        pane.getChildren().add(left);
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(FXML_SLIDINGController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            pane.getChildren().add(right);
            pane.getChildren().add(left);
        }
//        double textWidth = text.getLayoutBounds().getWidth();
//        KeyValue startKeyValue = new KeyValue(text.translateXProperty(), sceneWidth);
//        KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
//        KeyValue endKeyValue = new KeyValue(text.translateXProperty(), -1.0 * textWidth);
//        KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);
//         
//        // Create a Timeline
//        Timeline timeline = new Timeline(startKeyFrame, endKeyFrame);       
//        // Let the animation run forever
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        // Run the animation
//        timeline.play();
    }

    @FXML
    private void showBestClicked(MouseEvent event) throws CloneNotSupportedException {
        best = true;
        pane.getChildren().clear();
        System.out.println("b = " + b);
        move = 0;
        int[][] board = b.getBoard();
        Image img = new Image("wooden.jpg");
        ImageView im = new ImageView(img);
        im.setFitHeight(520);
        im.setFitWidth(520);
        pane.getChildren().add(im);
        TT = new Text[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Rectangle r = new Rectangle(160, 160, Paint.valueOf("white"));
                r.setX(j * 160 + 10 + j * 10);
                r.setY(i * 160 + 10 + i * 10);
                Text t = new Text("" + (board[i][j] == 0 ? "" : board[i][j]));
                t.setFont(new Font(27));
                TT[i][j] = t;
                t.setX(j * 160 + 85 + j * 10);
                t.setY(i * 160 + 85 + i * 10);
                pane.getChildren().add(r);
                pane.getChildren().add(t);
            }
        }
        lbl.setText("The Best Answer");
        playable = (Board) b.clone();

    }

    @FXML
    private void runClicked(MouseEvent event) {
        b = new Board();
        b.randomWalk((int) N.getValue());
        System.out.println("b = " + b);
        gc = new GeneticNSlidingPuzzle(b, 1024);
        allTheAnswers = gc.getAllTheAnswers();
        theBestAnswer = gc.getTheBestAnswer();
        allMeanPopulation = gc.getAllMeanPopulation();
        allBestPopulation = gc.getAllBestPopulation();
        show.setDisable(false);
        show_best.setDisable(false);

    }

}
