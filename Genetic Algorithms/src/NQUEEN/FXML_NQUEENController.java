/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NQUEEN;

import genetic.algorithms.FXMLDocumentController;
import static genetic.algorithms.FXMLDocumentController.se;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamed Khashehchi
 */
public class FXML_NQUEENController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button exit;
    @FXML
    private Button diagnosis;
    @FXML
    private Slider N;
    @FXML
    private Button configuration;
    private static int slide = 0;
    @FXML
    private Label lbl;
    public static Stage sex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        N_Queen backtrack = new N_Queen(4);
//        backtrack.getAll().forEach((s) -> {
//            System.out.println("backtrack = " + Arrays.toString(s));
//        });
//        System.out.println("backtrack = " + backtrack.getTime());
//        Genetic_n_Queen.set(8, 1000);
//        Genetic_n_Queen.ses(3, 3, 0.8f, 5, 200);
//        Genetic_n_Queen genetic = new Genetic_n_Queen(1000);
        Text t = new Text(250, 250, "Chess Board");
        pane.getChildren().add(t);
        lbl.setText("0/0");
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        FXMLDocumentController.se.close();
    }

    @FXML
    private void showClicked(MouseEvent event) {
        int n = (int) (N.getValue());
        Genetic_n_Queen.set(n, n * 500);
        Genetic_n_Queen.ses(3, 3, 0.8f, 5, n * 25);
        Genetic_n_Queen genetic = new Genetic_n_Queen(n * 250);
        ArrayList<Pheno> best = genetic.getBest();
        pane.getChildren().clear();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Rectangle r = new Rectangle(i * (500 / n), j * (500 / n), (500 / n), (500 / n));
                r.setFill(Paint.valueOf("Black"));
                if ((i % 2 == 0 && j % 2 == 1) || (j % 2 == 0 && i % 2 == 1)) {
                    r.setFill(Paint.valueOf("white"));
                }
                pane.getChildren().add(r);
            }
        }
        lbl.setText(((slide% best.size())+1)+ "/" + best.size());
        best.forEach((p) -> {
            p.show();
        });

        Pheno get = best.get(slide% best.size());
        int[] phen = get.getPhen();
        Image img = new Image(getClass().getResourceAsStream("queen.png"), (500 / n), (500 / n), false, false);
        ArrayList<ImageView> queens = new ArrayList<>();
        for (int l = 0; l < phen.length; l++) {
            ImageView imageView = new ImageView(img);

            imageView.setX(l * (500 / n));
            imageView.setY(phen[l] * (500 / n));
            queens.add(imageView);
            pane.getChildren().add(imageView);
        }
        Image image = new Image(getClass().getResourceAsStream("right.png"));
        Button right = new Button();
        right.setGraphic(new ImageView(image));
        right.setLayoutX(450);
        right.setLayoutY(200);
        right.setOpacity(0.8);
        pane.getChildren().add(right);

        image = new Image(getClass().getResourceAsStream("left.png"));
        Button left = new Button();
        left.setGraphic(new ImageView(image));
        left.setLayoutX(0);
        left.setLayoutY(200);
        left.setOpacity(0.8);
        pane.getChildren().add(left);
        right.setOnMouseClicked((eve) -> {
            slide++;
            lbl.setText((Math.floorMod(slide, best.size())+1)+"/"+best.size());
            Platform.runLater(() -> {
                queens.forEach((v) -> {
                    pane.getChildren().remove((ImageView) v);
                });
                queens.clear();
                Pheno ses = best.get((Math.floorMod(slide, best.size())));
                int[] sen = ses.getPhen();
                for (int l = 0; l < sen.length; l++) {
                    ImageView imageView = new ImageView(img);

                    imageView.setX(l * (500 / n));
                    imageView.setY(sen[l] * (500 / n));
                    queens.add(imageView);
                    pane.getChildren().add(imageView);
                }
                pane.getChildren().remove(right);
                pane.getChildren().add(right);
                pane.getChildren().remove(left);
                pane.getChildren().add(left);
            });
        });
        left.setOnMouseClicked((eve) -> {
            slide--;

            lbl.setText((Math.floorMod(slide, best.size())+1)+"/"+best.size());
            Platform.runLater(() -> {
                queens.forEach((v) -> {
                    pane.getChildren().remove(v);
                });
                queens.clear();
//                System.out.println(slide);
                Pheno ses = best.get((Math.floorMod(slide, best.size())));
                int[] sen = ses.getPhen();
                for (int l = 0; l < sen.length; l++) {
                    ImageView imageView = new ImageView(img);

                    imageView.setX(l * (500 / n));
                    imageView.setY(sen[l] * (500 / n));
                    queens.add(imageView);
                    pane.getChildren().add(imageView);
                    pane.getChildren().remove(right);
                    pane.getChildren().add(right);
                    pane.getChildren().remove(left);
                    pane.getChildren().add(left);
                }
            });
        });
    }

    @FXML
    private void diagnosisClicked(MouseEvent event) throws IOException {
        sex = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/NQUEEN/FXML_DIAGNOSIS.fxml"));

        Scene scene = new Scene(root);
        sex.setTitle("Charts");
        sex.getIcons().add(new Image("chart.png"));
        sex.setScene(scene);
        sex.show();
    }

    @FXML
    private void configurationClicked(MouseEvent event) {
    }

}
