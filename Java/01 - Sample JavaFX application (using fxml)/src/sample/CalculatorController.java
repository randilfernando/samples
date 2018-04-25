package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    /**
     * Injecting elements to the controller
     * These elements will inject at the fxml loading state
     */
    @FXML
    private TextField textNum1;
    @FXML
    private TextField textNum2;
    @FXML
    private Label labelTotal;

    /**
     * Instancce of calculator model
     */
    private CalculatorModel calculator;

    /**
     * Run at the initialization state of the controller
     * This method run after fxml file loader and elements are injected
     * Constructor method of the controller called at the start of fxml loading (see fx:controller)
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculator = new CalculatorModel();
    }

    /**
     * Method that will execute when user clicked add button
     * method will injected at the initialization stage
     */
    @FXML
    private void buttonAddClicked(){
        int num1 = Integer.valueOf(textNum1.getText());
        int num2 = Integer.valueOf(textNum2.getText());
        int result = calculator.add(num1, num2);
        labelTotal.setText(String.valueOf(result));
    }
}
