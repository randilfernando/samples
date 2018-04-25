package com.alternate.sample.controllers;

import com.alternate.sample.services.CalculatorService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField textNum1;
    @FXML
    private TextField textNum2;
    @FXML
    private Label labelTotal;

    private CalculatorService calculatorService = new CalculatorService();

    public void buttonAddClicked() {
        Integer total = calculatorService.add(Integer.parseInt(textNum1.getText()), Integer.parseInt(textNum2.getText()));
        labelTotal.setText(String.valueOf(total));
    }
}
