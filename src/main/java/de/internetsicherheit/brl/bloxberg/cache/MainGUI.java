package de.internetsicherheit.brl.bloxberg.cache;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bloxberg Cache Creator");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml_example.fxml"));
        Scene mainScene = new Scene(root, 300, 300);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
