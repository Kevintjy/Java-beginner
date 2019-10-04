package main.java.ATM.Visualization.UserFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class SendRequestPage {
    public main.java.ATM.User User = UserLoginPage.u;

    public void ChequingButton(ActionEvent actionEvent) {
        User.sendRequest("Chequing");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Chequing account creation request sent.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public void CreditButton(ActionEvent actionEvent) {
        User.sendRequest("Credit");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Credit account creation request sent.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public void SavingButton(ActionEvent actionEvent) {
        User.sendRequest("Saving");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Saving account creation request sent.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public void LineCreditButton(ActionEvent actionEvent) {
        User.sendRequest("Line Credit");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Line Credit account creation request sent.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public void USButton(ActionEvent actionEvent) {
        User.sendRequest("US Account");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("US Account creation request sent.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public void CancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
