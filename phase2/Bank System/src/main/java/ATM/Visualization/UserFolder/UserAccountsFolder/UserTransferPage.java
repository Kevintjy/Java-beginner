package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;
import main.java.ATM.Chequing;

import java.awt.*;
import java.io.IOException;

public class UserTransferPage {

    public static Account transferOut;

    public void UserTransferBack(ActionEvent actionEvent) throws IOException {

        if (transferOut instanceof Linecredit || transferOut instanceof Saving || transferOut instanceof Chequing) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserOtherAccountsPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserUSAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }

    @FXML
    TextField UserTransferAmountInput;

    @FXML
    TextField UserTransferAccountInput;


    public void UserTransfer(ActionEvent actionEvent) {

        Double amount = Double.parseDouble(UserTransferAmountInput.getText());
        String transferIn = UserTransferAccountInput.getText();
        boolean transferable;

        if (transferIn == null || amount.equals(0.0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Transaction");
            alert.setHeaderText("transfer failed");
            alert.showAndWait();
        } else {

            Account recipient = Accounts.foundAccountNum(transferIn);

            if (recipient == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Recipient");
                alert.setHeaderText("transfer failed");
                alert.showAndWait();

            } else {

                if (transferOut instanceof Chequing) {
                    transferable = ((Chequing) transferOut).transferMoney(transferIn, amount);
                } else if (transferOut instanceof Saving) {
                    transferable = ((Saving) transferOut).transferMoney(transferIn, amount);
                } else if (transferOut instanceof Linecredit) {
                    transferable = ((Linecredit) transferOut).transferMoney(transferIn, amount);
                } else {
                    transferable = ((USaccount) transferOut).transferMoney(transferIn, amount);
                }

                if (!transferable) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid Transaction");
                    alert.setHeaderText("transfer failed");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Transaction Confirmation");
                    alert.setContentText("Account " + transferOut.getAccountNum() +
                            " transfer " + amount + " dollars to Account " + transferIn);
                    alert.showAndWait();
                }
            }
        }

    }
}
