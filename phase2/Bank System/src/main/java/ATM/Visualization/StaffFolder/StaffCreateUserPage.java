package main.java.ATM.Visualization.StaffFolder;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Staff;
import main.java.ATM.User;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class StaffCreateUserPage {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField idInput;

    @FXML
    private PasswordField passwordInput;

    public Staff s = StaffLoginPage.s;


    public void enterButton(ActionEvent actionEvent) {
        String name = nameInput.getText();
        String id = idInput.getText();
        String password = passwordInput.getText();
        if (s.getPassword().equals(password)) {
            if (s.createUser(name, id, "1234")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User" + id + "is created.");
                alert.setHeaderText("Success");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User id claimed. Please try another one.");
                alert.setHeaderText("Invalid.");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect Password. Please enter your password again.");
            alert.setHeaderText("Authorization Failed");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }


    public void cancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
