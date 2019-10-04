package main.java.ATM.Visualization.UserFolder;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.User;
import main.java.ATM.Visualization.BankSystem;


import java.io.IOException;

public class UserLoginPage {
    public static User u;
    @FXML
    private TextField idInput;

    @FXML
    private PasswordField passwordInput;


    public void registrationButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You may reach manager during the normal business hour at manager's office.");
        alert.setHeaderText("Manager Information");
        alert.showAndWait();
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
            u = users.login(id, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ChooseIdentityPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
