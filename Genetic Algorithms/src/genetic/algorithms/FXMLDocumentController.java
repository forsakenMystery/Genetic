/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.algorithms;

import static NQUEEN.FXML_NQUEENController.sex;
import NQUEEN.Genetic_n_Queen;
import NQUEEN.N_Queen;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Hamed Khashehchi
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button N_QUEEN;
    @FXML
    private Button exit;
    public static Stage se;
    @FXML
    private Button SLIDING;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void nQueenClicked(MouseEvent event) throws IOException {
        se = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/NQUEEN/FXML_NQUEEN.fxml"));
        se.setTitle("NQUEEN");
        se.getIcons().add(new Image("queen.png"));
        Scene scene = new Scene(root);

        se.setScene(scene);
        se.show();

    }

    @FXML
    private void exitClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void slidingClicked(MouseEvent event) throws IOException {
        se = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/SLIDINGPUZZLE/FXML_SLIDING.fxml"));
        se.setTitle("Sliding Puzzle");
        se.getIcons().add(new Image("sliding_puzzle.png"));
        Scene scene = new Scene(root);

        se.setScene(scene);
        se.show();
    }

}
