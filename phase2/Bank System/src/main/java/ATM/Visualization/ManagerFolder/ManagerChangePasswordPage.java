package main.java.ATM.Visualization.ManagerFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class ManagerChangePasswordPage {

    @FXML
    PasswordField inputNew;

    @FXML
    PasswordField repeatNew;

    @FXML
    public void enterButton(ActionEvent actionEvent) {
        String newPassword = inputNew.getText();
        String confirmPassword = repeatNew.getText();
        if (newPassword.length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter your new password.");
            alert.setHeaderText("Invalid input");
            alert.showAndWait();
        } else if(!newPassword.equals(confirmPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Two password do not match.");
            alert.setHeaderText("Failed");
            alert.showAndWait();
        } else {
            ManagerLoginPage.m.changePassword(newPassword);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your password has been changed.");
            alert.setHeaderText("Success");
            alert.showAndWait();


        }
    }

    public void cancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
