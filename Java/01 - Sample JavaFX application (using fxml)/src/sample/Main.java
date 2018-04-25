package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Initialize the JavaFX application and set the primary stage
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculator-view.fxml"));
        primaryStage.setTitle("CalculatorModel");
        primaryStage.setScene(new Scene(root, 530, 256));
        primaryStage.show();
    }

    /**
     * Other environments (particularly IDEs) are not aware of the JavaFX startup process,
     * and expect the class you are executing to have a public static void main(String[] args) method,
     * like any standard Java application class. To support these environments,
     * it is common for your Application subclass to define a main(...) method which simply calls launch(...)
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
