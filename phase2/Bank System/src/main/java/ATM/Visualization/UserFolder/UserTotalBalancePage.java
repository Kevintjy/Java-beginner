package main.java.ATM.Visualization.UserFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.User;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class UserTotalBalancePage {
    @FXML
    Label balanceData;
    public User User = UserLoginPage.u;

    @FXML
    private void initialize(){
        balanceData.setText(Double.toString(User.getTotalBalance()));
    }
    public void logOutButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserLoginPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void okButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void contactManagerButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You may reach manager during the normal business hour at manager's office.");
        alert.setHeaderText("Manager Information");
        alert.showAndWait();
    }
}
