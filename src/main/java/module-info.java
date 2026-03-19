module com.example.randomnessanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.randomnessanalyzer to javafx.fxml;
    exports com.example.randomnessanalyzer;
}