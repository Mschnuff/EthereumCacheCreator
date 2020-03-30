package de.internetsicherheit.brl.bloxberg.cache;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HistoricDataVisualizer extends Application {

    private static final String OUTPUTDIRECTORY = System.getProperty("user.dir") + "/output/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        DataBlockSummerizer dbs = initDataBlockSummerizer();
        // max value in file: 5342081
        BlockGroup[] bgA = dbs.summerizeData(0, 5342081, 5342080);
        primaryStage.setTitle("Historic Data Visualizer");

        //Group root = new Group();
        primaryStage.setScene(generateLineChart(bgA));
        //primaryStage.setScene(generateSimpleLineChart());
        primaryStage.show();

    }

    private Scene generateLineChart(BlockGroup[] bgA) {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis(bgA[0].getStart().doubleValue(),
                bgA[bgA.length-1].getStart().doubleValue() + bgA[bgA.length-1].getRange(), bgA[bgA.length-1].getRange());
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Blocknumbers");
        yAxis.setLabel("Transactions");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Transaction in Blockchain history");
        //defining a series
        XYChart.Series series = new XYChart.Series();

        series.setName("Number of Transactions per " + bgA[bgA.length-1].getRange() + " blocks");
        //populating the series with data  (length -1  ?)

        for(int i = 0; i < bgA.length; i++) {


                series.getData().add(new XYChart.Data(bgA[i].getStart(), bgA[i].getSum()));

        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        return scene;

    }
    private DataBlockSummerizer initDataBlockSummerizer() {

        Path workDir= Paths.get((OUTPUTDIRECTORY) + "ExtractedData.txt");
        return new DataBlockSummerizer(workDir);
    }

}
