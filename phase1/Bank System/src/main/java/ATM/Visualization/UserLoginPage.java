package main.java.ATM.Visualization;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.User;


import java.io.IOException;

public class UserLoginPage {
    @FXML
    TextField idInput;

    @FXML
    PasswordField passwordInput;


    public void registrationButton(ActionEvent actionEvent) {
    }

    public void loginButton(ActionEvent actionEvent) throws IOException {
        String id = idInput.getText();
        String password = passwordInput.getText();
        Users users = new Users();
        if (users.login(id, password) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("We cannot find you, please contact the bank managers");
            alert.setHeaderText("authorization failed");
            alert.showAndWait();
        }
        else{
            User user = users.login(id, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }
}
