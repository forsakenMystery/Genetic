/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLIDINGPUZZLE;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Hamed Khashehchi
 */
public class FXML_DIAGNOSISController implements Initializable {

    int xAxis[] = {4, 5};
    @FXML
    private StackPane anchor;
    @FXML
    private LineChart<String, Number> Diagram;
    @FXML
    private Button line;
    @FXML
    private Button linebar;
    @FXML
    private Button exit;
    @FXML
    private Slider avg;
    @FXML
    private Slider max;
    private ArrayList<Float> allMeanPopulation;
    private ArrayList<Float> allBestPopulation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allBestPopulation = FXML_SLIDINGController.allBestPopulation;
        allMeanPopulation = FXML_SLIDINGController.allMeanPopulation;
    }

    @FXML
    private void barActivated(MouseEvent event) {
    }

    @FXML
    private void COSTCLICKED(MouseEvent event) {
        int mx = (int) (max.getValue());
        xAxis = IntStream.range(4, mx + 1).toArray();
        Diagram.getData().clear();
        double yAxisMax[];
        double yAxisAVG[];
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        FXML_SLIDINGController.sex.close();
    }

}
