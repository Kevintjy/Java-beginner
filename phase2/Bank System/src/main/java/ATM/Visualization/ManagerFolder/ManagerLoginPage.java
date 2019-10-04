package main.java.ATM.Visualization.ManagerFolder;

import Model.Managers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Manager;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class ManagerLoginPage {
    public static Manager m;
    @FXML
    private TextField idInput;
    @FXML
    private PasswordField pwInput;


    public void loginButton(ActionEvent actionEvent) throws IOException {
        String id = idInput.getText();
        String password = pwInput.getText();
        Managers managers = new Managers();
        if (managers.login(id, password) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("We cannot find you, please try again.");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();
        } else {
            m = managers.login(id, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ChooseIdentityPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
