package main.java.ATM.Visualization.UserFolder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Visualization.BankSystem;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class UserChangePasswordPage {

    @FXML
    PasswordField passwordInput;

    @FXML
    PasswordField newPasswordInput;

    @FXML
    public void enterPassword(ActionEvent actionEvent) {
        String password = passwordInput.getText();
    }


    public void enterButton(javafx.event.ActionEvent actionEvent) throws IOException{
        String password = passwordInput.getText();
        String newpassword = newPasswordInput.getText();
        if(UserLoginPage.u.getPassword().equals(password)){
            if(newpassword.length()>=4){
                UserLoginPage.u.changePassword(newpassword);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("the new password is" + newpassword);
                alert.setHeaderText("change password success");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);}
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("At least 4 digits for password");
                alert.setHeaderText("Not valid password");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The password is wrong");
            alert.setHeaderText("authorization failed");
            alert.showAndWait();
        }

    }

    public void cancelButton(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
