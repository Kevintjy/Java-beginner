package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import Model.ATMs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class UserWithdrawPage {

    public static Account account;


    public void UserWithdrawBack(ActionEvent actionEvent) throws IOException {
        if (account instanceof Linecredit || account instanceof Saving || account instanceof Chequing) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserOtherAccountsPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCreditAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }

    @FXML
    TextField UserWithdrawInput;

    @FXML
    TextField UserWithdrawATMid;

    public void UserWithdrawButton(ActionEvent actionEvent) {

        int amount = Integer.parseInt(UserWithdrawInput.getText());
        String atmid = UserWithdrawATMid.getText();
        boolean withdrawable;

        if (amount == 0 || atmid == null || amount % 5 != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Transaction.");
            alert.setHeaderText("withdraw failed");
            alert.showAndWait();

        } else {

            ATM a = ATMs.findATM(atmid);

            if (a == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The ATM you enter does not exist.");
                alert.setHeaderText("withdraw failed");
                alert.showAndWait();

            } else {

                boolean status = a.closeWithdraw;

                if (status) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The ATM you chose is out of service");
                    alert.setHeaderText("withdraw failed");
                    alert.showAndWait();

                } else {

                    if (account instanceof Chequing) {
                        withdrawable = ((Chequing) account).withdraw(amount, a);
                    } else if (account instanceof Saving) {
                        withdrawable = ((Saving) account).withdraw(amount, a);
                    } else if (account instanceof Credit){
                        withdrawable = ((Credit) account).withdraw(amount, a);
                    } else {
                        withdrawable = ((Linecredit) account).withdraw(amount, a);
                    }

                    if (!withdrawable) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Transaction Declined");
                        alert.setHeaderText("withdraw failed");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Withdraw Confirmation");
                        alert.setContentText("Withdrawed " + amount + " dollars from account " + account.getAccountNum());
                        alert.showAndWait();
                    }
                }
            }
        }
    }
}
