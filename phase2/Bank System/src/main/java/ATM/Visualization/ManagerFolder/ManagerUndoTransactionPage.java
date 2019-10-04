package main.java.ATM.Visualization.ManagerFolder;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;


import java.io.IOException;

public class ManagerUndoTransactionPage {
    public static User u;
    @FXML
    private TextField idInput;
    @FXML
    private PasswordField pwInput;
    @FXML
    private PasswordField mpwInput;


    public void confirmButton(ActionEvent actionEvent) throws IOException {
        String id = idInput.getText();
        String password = pwInput.getText();
        String managerPassword = mpwInput.getText();
        Users users = new Users();
        if (users.login(id, password) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("We cannot find the user, please try again.");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();
        }
        else if(!ManagerLoginPage.m.getPassword().equals(managerPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Manager password incorrect.");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();
        }
        else{
            u = users.login(id, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionUserPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }
    public void cancelButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }


}
