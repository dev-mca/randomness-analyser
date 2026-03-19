package com.example.randomnessanalyzer;

import com.example.randomnessanalyzer.view.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        new Ui().start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}