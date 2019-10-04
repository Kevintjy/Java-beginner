package main.java.ATM.Visualization.StaffFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class StaffTotalBalancePage {

    @FXML
    Label balanceData;
    public main.java.ATM.Staff staff = StaffLoginPage.s;

    @FXML
    private void initialize(){
        balanceData.setText(Double.toString(staff.getTotalBalance()));
    }

    public void logOutButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffLoginPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void okButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
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