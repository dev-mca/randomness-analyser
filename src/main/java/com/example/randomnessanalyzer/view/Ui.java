package com.example.randomnessanalyzer.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ui {

    public void start(Stage stage) {
        BorderPane bp = new BorderPane();

        Label label = new Label("Hallo JavaFX!");
        bp.setCenter(label);

        Scene scene = new Scene(bp, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Randomness Analyzer");
        stage.show();
    }
}