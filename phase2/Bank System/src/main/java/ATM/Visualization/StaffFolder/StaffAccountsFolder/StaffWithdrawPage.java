package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import Model.ATMs;
import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class StaffWithdrawPage {

    public static Account acc;

    public void StaffWithdrawBack(ActionEvent actionEvent) throws IOException {
        if (acc instanceof Linecredit || acc instanceof Saving || acc instanceof Chequing) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffOtherAccountsPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffCreditAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
    }

    @FXML
    TextField StaffWithdrawInput;


    public void StaffWithdrawButton(ActionEvent actionEvent) throws IOException {

        int amount = Integer.parseInt(StaffWithdrawInput.getText());

        if (amount == 0 || amount % 5 != 0) {
            Alert formatAlert = new Alert(Alert.AlertType.ERROR);
            formatAlert.setContentText("Please enter a valid amount. Try again.");
            formatAlert.setHeaderText("Input Unrecognizable");
            formatAlert.setTitle("Invalid input");
            formatAlert.showAndWait();
        } else if (ATMs.workATMs.size() == 0) {
            Alert noWorkingATMAlert = new Alert(Alert.AlertType.ERROR);
            noWorkingATMAlert.setTitle("Error");
            noWorkingATMAlert.setHeaderText("No working ATM.");
            noWorkingATMAlert.setContentText("Please Contact the Bank Manager.");
            noWorkingATMAlert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(amount + " dollars will be took" + " from your account " + acc.getAccountNum()
                    + ". \n"
                    + "Please choose one of the ATM to proceed. Click cancel if you want to modify.");
            alert.setHeaderText("Please follow the instruction:");
            alert.setTitle("Confirmation Needed");
            alert.showAndWait();

            ArrayList<ButtonType> atmButton = new ArrayList<>();
            for (ATM atm : ATMs.workATMs) {
                atmButton.add(new ButtonType(atm.id));
            }

            alert.getButtonTypes().clear();
            for (ButtonType bt : atmButton) {
                alert.getButtonTypes().add(bt);
            }

            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent()) {
                int index = atmButton.indexOf(option.get());
                if (index > -1) {
                    ATM chosenATM = ATMs.workATMs.get(index);
                    processRequest(amount, chosenATM);
                }

            }
        }
    }


    private void processRequest(int amount, ATM atm) throws IOException{

        boolean withdrawable;

        if (acc instanceof Chequing) {
            withdrawable = ((Chequing) acc).withdraw(amount, atm);
        } else if (acc instanceof Saving) {
            withdrawable = ((Saving) acc).withdraw(amount, atm);
        } else if (acc instanceof Credit) {
            withdrawable = ((Credit) acc).withdraw(amount, atm);
        } else {
            withdrawable = ((Linecredit) acc).withdraw(amount, atm);
        }

        if (!withdrawable) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your input amount is out of your account balance.\n" + "Your current balance is "
                    + acc.display() + ".");
            alert.setHeaderText("Transaction Declined");
            alert.showAndWait();
        } else {
            successfulProcedure();
        }
    }


    private void successfulProcedure() throws IOException {
        Alert successfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
        successfulAlert.setTitle("Successful");
        successfulAlert.setHeaderText("Cash withdrawed success");
        successfulAlert.setContentText("The new balance of account " + acc.getAccountNum() + " is " +
                    acc.display() + ".\n" +
                    "Do you wish to make another cash withdraw from this account?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        successfulAlert.getButtonTypes().clear();
        successfulAlert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = successfulAlert.showAndWait();

        if (option.isPresent()) {
            if (option.get() == yes) {
                    StaffWithdrawInput.setText(null);
            } else if (option.get() == no) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        }
    }
}
