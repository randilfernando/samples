package com.alternate.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("com/alternate/sample/fxml/Calculator.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent, 530, 256);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
