/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NQUEEN;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Hamed Khashehchi
 */
public class FXML_DIAGNOSISController implements Initializable {

    int xAxis[] = {4, 5};
    XYChart.Series firstGenetic, firstBacktrack;
    XYChart.Series wholeGenetic, wholeBacktrack;
    XYChart.Series GeneticBestCost, GeneticAVGCost;

    @FXML
    private StackPane anchor;
    @FXML
    private Button line;
    @FXML
    private Button exit;
    @FXML
    private LineChart<String, Number> Diagram;
    @FXML
    private Slider avg;

    int Data[];
    @FXML
    private Slider max;
    @FXML
    private Button linebar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void barActivated(MouseEvent event) {
        int mx = (int) (max.getValue());
        xAxis = IntStream.range(4, mx + 1).toArray();
        Diagram.getData().clear();
        double yAxisFirstGenetic[];
        double yAxisFirstBacktrack[];
        double yAxisWholeGenetic[];
        double yAxisWholeBacktrack[];

        yAxisFirstGenetic = new double[xAxis.length];
        yAxisFirstBacktrack = new double[xAxis.length];
        yAxisWholeBacktrack = new double[xAxis.length];
        yAxisWholeGenetic = new double[xAxis.length];

        firstGenetic = new XYChart.Series<>();
        firstBacktrack = new XYChart.Series<>();
        wholeBacktrack = new XYChart.Series<>();
        wholeGenetic = new XYChart.Series<>();

        for (int i = 0; i < xAxis.length; i++) {
            long sum = 0;
            long sus = 0;
            long sur = 0;
            long sud = 0;
            for (int j = 0; j < avg.getValue(); j++) {
                Genetic_n_Queen.set(xAxis[i], xAxis[i] * 500);
                Genetic_n_Queen.ses(3, 3, 0.8f, 5, xAxis[i] * 25);
                Genetic_n_Queen genetic = new Genetic_n_Queen(xAxis[i] * 250);
                sum += genetic.getFirstAnswerTime();
                N_Queen queen = new N_Queen(xAxis[i]);
                sus += queen.getTime().get(0);
                sur += queen.getWholeAnswerTime();
                sud += genetic.getWholeAnswerTime();

            }
            double average = sum / avg.getValue();
            double averge = sus / avg.getValue();
            double avere = sur / avg.getValue();
            double ave = sud / avg.getValue();

            yAxisFirstGenetic[i] = average;
            yAxisFirstBacktrack[i] = averge;
            yAxisWholeBacktrack[i] = avere;
            yAxisWholeGenetic[i] = ave;
        }
        firstGenetic.setName("F G");
        firstGenetic.setName("F B");
        wholeBacktrack.setName("W B");
        wholeGenetic.setName("W G");

        for (int i = 0; i < xAxis.length; i++) {
            firstGenetic.getData().add(new XYChart.Data<>(Integer.toString(xAxis[i]), Math.log(yAxisFirstGenetic[i])));
            firstBacktrack.getData().add(new XYChart.Data<>(Integer.toString(xAxis[i]), Math.log(yAxisFirstBacktrack[i])));
            wholeBacktrack.getData().add(new XYChart.Data<>(Integer.toString(xAxis[i]), Math.log(yAxisWholeBacktrack[i])));
            wholeGenetic.getData().add(new XYChart.Data<>(Integer.toString(xAxis[i]), Math.log(yAxisWholeGenetic[i])));
        }
        Diagram.getData().add(firstGenetic);
        Diagram.getData().add(firstBacktrack);
        Diagram.getData().add(wholeBacktrack);
        Diagram.getData().add(wholeGenetic);
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        FXML_NQUEENController.sex.close();
    }

    @FXML
    private void COSTCLICKED(MouseEvent event) {
        int mx = (int) (max.getValue());
        xAxis = IntStream.range(4, mx + 1).toArray();
        Diagram.getData().clear();
        double yAxisMax[];
        double yAxisAVG[];

        GeneticAVGCost = new XYChart.Series<>();
        GeneticBestCost = new XYChart.Series<>();
        ArrayList<Double> ava = new ArrayList<>();
        ArrayList<Long> ma = new ArrayList<>();
        for (int i = 0; i < avg.getValue(); i++) {
            Genetic_n_Queen.set((int) max.getValue(), (int) max.getValue() * 500);
            Genetic_n_Queen.ses(3, 3, 0.8f, 5, (int) max.getValue() * 25);
            Genetic_n_Queen genetic = new Genetic_n_Queen((int) max.getValue() * 250);
            if (i == 0) {
                ava.addAll(genetic.getAvgCost());
                ma.addAll(genetic.getMaxCost());
                System.out.println("ma = " + ma);
//                System.out.println("ava = " + ava);
            } else {
                for (int j = 0; j < ava.size(); j++) {
                    ava.set(j, ava.get(j) + genetic.getAvgCost().get(j));
                    ma.set(j, ma.get(j) + genetic.getMaxCost().get(j));
                }
            }
        }

        yAxisMax = new double[ava.size()];
        yAxisAVG = new double[ava.size()];
        for (int j = 0; j < ava.size(); j++) {
            ava.set(j, ava.get(j) / avg.getValue());
            yAxisMax[j] = (ma.get(j) / (double) avg.getValue());
//            System.out.println(ava.get(j));
            yAxisAVG[j] = ava.get(j);
        }
        GeneticAVGCost.setName("G avg");
        GeneticBestCost.setName("G worst");
        for (int i = 0; i < ava.size(); i++) {
            if (i % 2 == 0) {
                GeneticAVGCost.getData().add(new XYChart.Data<>(Integer.toString(i), (yAxisAVG[i])));
                GeneticBestCost.getData().add(new XYChart.Data<>(Integer.toString(i), (yAxisMax[i])));
            }
        }
        Diagram.getData().add(GeneticAVGCost);
        Diagram.getData().add(GeneticBestCost);

    }

}
