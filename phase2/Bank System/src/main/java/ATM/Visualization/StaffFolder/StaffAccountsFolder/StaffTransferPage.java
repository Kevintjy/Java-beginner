package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class StaffTransferPage {

    public static Account transferOut;

    @FXML
    TextField StaffTransferAmountInput;

    @FXML
    TextField StaffTransferAccountInput;

    public void StaffTransfer(ActionEvent actionEvent) {

        Double amount = Double.parseDouble(StaffTransferAmountInput.getText());
        String transferIn = StaffTransferAccountInput.getText();
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
                }
            }
        }
    }

    public void StaffTransferBack(ActionEvent actionEvent) throws IOException {

        if (transferOut instanceof Linecredit || transferOut instanceof Saving || transferOut instanceof Chequing) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffOtherAccountsPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffUSAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }
}
