package com.example.randomnessanalyzer.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Ui {

    public void start(Stage stage) {

        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: #0f1117;");

        // ── TOP ──────────────────────────────────────────────
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 28, 18, 28));
        topBar.setStyle("-fx-background-color: #1a1d27; -fx-border-color: #2a2d3e; -fx-border-width: 0 0 1 0;");

        Label title = new Label("Randomness Analyzer");
        title.setStyle("-fx-text-fill: #e8eaf0; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Courier New';");

        Label version = new Label("v0.0.1");
        version.setStyle("-fx-text-fill: #5b6390; -fx-font-size: 12px; -fx-font-family: 'Courier New'; -fx-padding: 0 0 0 10;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusDot = new Label("● READY");
        statusDot.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 11px; -fx-font-family: 'Courier New';");

        topBar.getChildren().addAll(title, version, spacer, statusDot);

        // ── LEFT PANEL ───────────────────────────────────────
        VBox leftPanel = new VBox(20);
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setPadding(new Insets(24, 16, 24, 16));
        leftPanel.setPrefWidth(200);
        leftPanel.setStyle("-fx-background-color: #1a1d27; -fx-border-color: #2a2d3e; -fx-border-width: 0 1 0 0;");

        Label controlsTitle = new Label("CONTROLS");
        controlsTitle.setStyle("-fx-text-fill: #5b6390; -fx-font-size: 10px; -fx-font-family: 'Courier New'; -fx-letter-spacing: 2px;");

        // Sample size slider
        VBox sampleBox = buildSliderBox("SAMPLE SIZE", 100, 10000, 1000, "%.0f");
        // Speed slider
        VBox speedBox = buildSliderBox("SPEED %", 0, 100, 50, "%.0f%%");

        Button generateBtn = new Button("▶  GENERATE");
        generateBtn.setMaxWidth(Double.MAX_VALUE);
        generateBtn.setStyle("""
            -fx-background-color: #6366f1;
            -fx-text-fill: white;
            -fx-font-family: 'Courier New';
            -fx-font-size: 13px;
            -fx-font-weight: bold;
            -fx-padding: 12 0;
            -fx-cursor: hand;
            -fx-background-radius: 6;
        """);

        leftPanel.getChildren().addAll(controlsTitle, sampleBox, speedBox, generateBtn);

        // ── CENTER ───────────────────────────────────────────
        VBox centerBox = new VBox(12);
        centerBox.setPadding(new Insets(24));
        centerBox.setStyle("-fx-background-color: #0f1117;");

        Label chartTitle = new Label("FREQUENCY DISTRIBUTION");
        chartTitle.setStyle("-fx-text-fill: #9ca3c8; -fx-font-size: 11px; -fx-font-family: 'Courier New';");

        BarChart<String, Number> barChart = buildBarChart();
        VBox.setVgrow(barChart, Priority.ALWAYS);

        centerBox.getChildren().addAll(chartTitle, barChart);

        // ── RIGHT PANEL ──────────────────────────────────────
        VBox rightPanel = new VBox(14);
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setPadding(new Insets(24, 16, 24, 16));
        rightPanel.setPrefWidth(180);
        rightPanel.setStyle("-fx-background-color: #1a1d27; -fx-border-color: #2a2d3e; -fx-border-width: 0 0 0 1;");

        Label statsTitle = new Label("STATISTICS");
        statsTitle.setStyle("-fx-text-fill: #5b6390; -fx-font-size: 10px; -fx-font-family: 'Courier New';");

        rightPanel.getChildren().addAll(
                statsTitle,
                buildStatCard("MIN", "—"),
                buildStatCard("MAX", "—"),
                buildStatCard("MEAN", "—"),
                buildStatCard("STD DEV", "—"),
                buildStatCard("χ² TEST", "—")
        );

        // ── BOTTOM ───────────────────────────────────────────
        HBox bottomBar = new HBox();
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setPadding(new Insets(10, 28, 10, 28));
        bottomBar.setStyle("-fx-background-color: #1a1d27; -fx-border-color: #2a2d3e; -fx-border-width: 1 0 0 0;");

        Label bottomText = new Label("No data generated yet — press GENERATE to start");
        bottomText.setStyle("-fx-text-fill: #5b6390; -fx-font-size: 11px; -fx-font-family: 'Courier New';");
        bottomBar.getChildren().add(bottomText);

        // ── ASSEMBLE ─────────────────────────────────────────
        bp.setTop(topBar);
        bp.setLeft(leftPanel);
        bp.setCenter(centerBox);
        bp.setRight(rightPanel);
        bp.setBottom(bottomBar);

        Scene scene = new Scene(bp, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Randomness Analyzer v0.0.1");
        stage.centerOnScreen();
        stage.show();
    }

    private VBox buildSliderBox(String labelText, double min, double max, double value, String fmt) {
        VBox box = new VBox(6);

        HBox header = new HBox();
        Label lbl = new Label(labelText);
        lbl.setStyle("-fx-text-fill: #9ca3c8; -fx-font-size: 10px; -fx-font-family: 'Courier New';");

        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);

        Label valLabel = new Label(String.format(fmt, value));
        valLabel.setStyle("-fx-text-fill: #6366f1; -fx-font-size: 10px; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");

        header.getChildren().addAll(lbl, sp, valLabel);

        Slider slider = new Slider(min, max, value);
        slider.setMaxWidth(Double.MAX_VALUE);
        slider.setStyle("-fx-control-inner-background: #2a2d3e; -fx-accent: #6366f1;");

        HBox tickBox = new HBox();
        tickBox.setMaxWidth(Double.MAX_VALUE);
        String startTick = min == 0 ? "0%" : String.format("%.0f", min);
        String endTick   = max == 100 ? "100%" : String.format("%.0f", max);
        Label minLbl = new Label(startTick);
        minLbl.setStyle("-fx-text-fill: #3d4166; -fx-font-size: 9px; -fx-font-family: 'Courier New';");
        Label maxLbl = new Label(endTick);
        maxLbl.setStyle("-fx-text-fill: #3d4166; -fx-font-size: 9px; -fx-font-family: 'Courier New';");
        Region tickSpacer = new Region();
        HBox.setHgrow(tickSpacer, Priority.ALWAYS);
        tickBox.getChildren().addAll(minLbl, tickSpacer, maxLbl);

        slider.valueProperty().addListener((obs, o, n) ->
                valLabel.setText(String.format(fmt, n.doubleValue()))
        );

        box.getChildren().addAll(header, slider, tickBox);
        return box;
    }

    private VBox buildStatCard(String label, String value) {
        VBox card = new VBox(4);
        card.setPadding(new Insets(10, 12, 10, 12));
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle("-fx-background-color: #0f1117; -fx-background-radius: 6; -fx-border-color: #2a2d3e; -fx-border-radius: 6; -fx-border-width: 1;");

        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: #5b6390; -fx-font-size: 9px; -fx-font-family: 'Courier New';");

        Label val = new Label(value);
        val.setStyle("-fx-text-fill: #e8eaf0; -fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");

        card.getChildren().addAll(lbl, val);
        return card;
    }

    private BarChart<String, Number> buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Bucket");
        xAxis.setStyle("-fx-tick-label-fill: #5b6390; -fx-font-family: 'Courier New'; -fx-font-size: 10px;");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Frequency");
        yAxis.setStyle("-fx-tick-label-fill: #5b6390; -fx-font-family: 'Courier New'; -fx-font-size: 10px;");

        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setStyle("-fx-background-color: transparent; -fx-plot-background-color: #13161f;");
        chart.setLegendVisible(false);
        chart.setAnimated(true);
        chart.setBarGap(2);
        chart.setCategoryGap(4);

        // Placeholder data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        int[] sample = {45, 62, 58, 71, 49, 83, 55, 67, 60, 52};
        for (int i = 0; i < sample.length; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i * 10), sample[i]));
        }
        chart.getData().add(series);

        // Style bars after adding
        chart.lookupAll(".bar-chart .data0.chart-bar").forEach(n ->
                n.setStyle("-fx-bar-fill: #6366f1;")
        );

        return chart;
    }
}