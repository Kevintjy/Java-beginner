package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.java.ATM.Account;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.Optional;

public class UserDepositChequePage {

    public static Account acc;

    @FXML
    Text accountNumber;

    @FXML
    TextField chequeInput;

    @FXML
    public void initialize() {
        accountNumber.setText(acc.getAccountNum());
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void enterButton(ActionEvent actionEvent) throws IOException {
        if (amountFormatCheckFail(chequeInput.getText())) {
            Alert formatAlert = new Alert(Alert.AlertType.ERROR);
            formatAlert.setContentText("Please enter a valid amount. Try again.");
            formatAlert.setHeaderText("Input Unrecognizable");
            formatAlert.setTitle("Invalid input");
            formatAlert.showAndWait();
        } else {
            double amount = Double.parseDouble(chequeInput.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Cheque amount " + amount + " will be deposit to " + acc.getAccountNum() + ".\n" +
                    "Do you wish to proceed?");
            alert.setHeaderText("Please Confirm the following information");
            alert.setTitle("Confirmation needed");

            ButtonType yes = new ButtonType("Yes, proceed.");
            ButtonType no = new ButtonType("No, modify.");

            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(yes, no);
            Optional<ButtonType> chosen = alert.showAndWait();

            if (chosen.isPresent()) {
                if (chosen.get() == yes) {
                    acc.add(amount);
                    successfulProcedure();
                }
            }
        }
    }

    //Helper
    private boolean amountFormatCheckFail(String text) {
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private void successfulProcedure() throws IOException {
        Alert successfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
        successfulAlert.setTitle("Successful");
        successfulAlert.setHeaderText("Cheque Successfully Deposited");
        successfulAlert.setContentText("The new balance of account " + acc.getAccountNum() + " is " +
                acc.display() + ".\n" +
                "Do you wish to deposit another cheque to this account?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        successfulAlert.getButtonTypes().clear();
        successfulAlert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = successfulAlert.showAndWait();

        if (option.isPresent()) {
            if (option.get() == yes) {
                chequeInput.setText(null);
            } else if (option.get() == no) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        }
    }
}
