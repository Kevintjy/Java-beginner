package main.java.ATM.Visualization.StaffFolder;

import Model.Staffs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Staff;
import main.java.ATM.Visualization.BankSystem;


import java.io.IOException;

public class StaffLoginPage {

    public static Staff s;
    @FXML
    private TextField idInput;

    @FXML
    private PasswordField passwordInput;


    public void loginButton(ActionEvent actionEvent) throws IOException {
        String id = idInput.getText();
        String password = passwordInput.getText();
        Staffs staffs = new Staffs();
        if (staffs.login(id, password) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ID and password does not match. Please contact the bank manager.");
            alert.setHeaderText("authorization failed");
            alert.showAndWait();
        }
        else{
            s = staffs.login(id, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
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
