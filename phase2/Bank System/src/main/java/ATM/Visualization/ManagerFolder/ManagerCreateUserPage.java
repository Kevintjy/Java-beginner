package main.java.ATM.Visualization.ManagerFolder;

import Model.Staffs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.User;
import Model.Users;
import main.java.ATM.Visualization.BankSystem;
import java.io.IOException;

public class ManagerCreateUserPage {
    public User u;
    @FXML
    private TextField idInput;
    @FXML
    private PasswordField pwInput;
    @FXML
    private TextField nameInput;


    public void enterButton(ActionEvent actionEvent) {
        String name = nameInput.getText();
        String id = idInput.getText();
        String password = pwInput.getText();
        if (Users.UserIdExists(id) != null|| Staffs.StaffIdExists(id)!= null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The User id already exist.");
            alert.setHeaderText("Authorization Failed.");
            alert.showAndWait();
        }
        else if(!ManagerLoginPage.m.getPassword().equals(password)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The password is incorrect.");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();}
        else{ u = new User(name,id,"1234"); Users.addUser(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The user has been created.");
        alert.setHeaderText("Authorization Success");
        alert.showAndWait();
        }

    }


    public void backButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
