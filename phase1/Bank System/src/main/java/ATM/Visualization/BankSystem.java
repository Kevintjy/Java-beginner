package main.java.ATM.Visualization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BankSystem extends Application {

    public static BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseIdentityPage.fxml"));
        Scene scene = new Scene(root, 600, 400);
        root.setCenter(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
