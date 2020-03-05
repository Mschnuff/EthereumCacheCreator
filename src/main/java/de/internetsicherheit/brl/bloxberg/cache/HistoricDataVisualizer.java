package de.internetsicherheit.brl.bloxberg.cache;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class HistoricDataVisualizer extends Application {

    private static final String OUTPUTDIRECTORY = System.getProperty("user.dir") + "/output/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        DataBlockSummerizer dbs = initDataBlockSummerizer();
        BlockGroup[] bgA = dbs.summerizeData(0, 5342081, 100000);
        primaryStage.setTitle("Historic Data Visualizer");

        //Group root = new Group();
        primaryStage.setScene(generateLineChart(bgA));
        //primaryStage.setScene(generateSimpleLineChart());
        primaryStage.show();

    }
    private Scene generateSimpleLineChart() {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        return scene;

    }
    private Scene generateLineChart(BlockGroup[] bgA) {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Blocknumbers");
        xAxis.setLabel("Transactions");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Transaction in Blockchain history");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Number of Transactions per 100.000 blocks");
        //populating the series with data
        for(int i = 0; i < bgA.length -1; i++) {
            series.getData().add(new XYChart.Data(bgA[i].getStart(), bgA[i].getSum()));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        return scene;

    }
    private DataBlockSummerizer initDataBlockSummerizer() {
        Path workDir= Path.of((OUTPUTDIRECTORY) + "ExtractedData.txt");
        return new DataBlockSummerizer(workDir);
    }

}
