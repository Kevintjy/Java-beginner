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
import main.java.ATM.Chequing;
import main.java.ATM.Linecredit;
import main.java.ATM.Saving;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.Optional;

public class UserPayBillPage {

    public static Account acc;

    @FXML
    Text accountNumber;

    @FXML
    Text accountType;

    @FXML
    private TextField amountInput;

    @FXML
    private TextField recipientInput;


    public void initialize(){
        accountNumber.setText(acc.getAccountNum());
        if(acc instanceof Saving){
            accountType.setText("Saving Account");}
        else if(acc instanceof Chequing){
            accountType.setText("Chequing Account");}
        else if(acc instanceof Linecredit){
            accountType.setText("Line Credit Account");
        }else{
            accountType.setText("US Dollar Account");
        }

    }

    public void enterButton(ActionEvent actionEvent) throws IOException {
        if (amountFormatCheckFail(amountInput.getText())) {
            Alert formatAlert = new Alert(Alert.AlertType.ERROR);
            formatAlert.setContentText("Please enter a valid amount. Try again.");
            formatAlert.setHeaderText("Amount Input Unrecognizable");
            formatAlert.setTitle("Invalid Input");
            formatAlert.showAndWait();
        } else {
            double amount = Double.parseDouble(amountInput.getText());
            String recipient = recipientInput.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Needed");
            alert.setHeaderText("Please Confirm the following information");
            alert.setContentText(accountType.getText() + " " + acc.getAccountNum() +
                    " pay bill amount " + amount + " to " + recipient + "\n" +
                    "Do you wish to proceed?");

            ButtonType yes = new ButtonType("Yes, proceed.");
            ButtonType no = new ButtonType("No, modify.");
            ButtonType cancelPayBill = new ButtonType("Cancel Pay Bill.");

            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(yes, no, cancelPayBill);
            Optional<ButtonType> chosen = alert.showAndWait();

            boolean check;
            if (chosen.isPresent()) {
                if (chosen.get() == yes) {
                    if (acc instanceof Saving) {
                        check = ((Saving) acc).payBill(recipient, amount);
                    } else if (acc instanceof Chequing) {
                        check = ((Chequing) acc).payBill(recipient, amount);
                    } else {
                        check = ((Linecredit) acc).payBill(recipient, amount);
                    }
                    payBillCheck(check);
                } else if(chosen.get() == cancelPayBill) {
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                    cancelAlert.setContentText("You will be redirect to the Account Selection Page");
                    cancelAlert.setHeaderText("Pay Bill Cancelled.");
                    cancelAlert.setTitle("Cancelled");
                    cancelAlert.showAndWait();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
                    AnchorPane anchorPane = loader.load();
                    BankSystem.root.getChildren().setAll(anchorPane);
                }
            }
        }
    }

    public void cancelButton(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    // Helper
    private boolean amountFormatCheckFail(String text){
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e){
            return true;
        }
        return false;
    }

    private void payBillCheck(boolean checking) throws IOException{
        if (checking){
            successfulProcedure();
        }else {
            Alert failedAlert = new Alert(Alert.AlertType.ERROR);
            failedAlert.setContentText(accountType.getText() + " " +accountNumber.getText() + " does not have enough money.");
            failedAlert.setHeaderText("Insufficient Funds.");
            failedAlert.setTitle("Failed");
            failedAlert.showAndWait();
        }
    }

    private void successfulProcedure() throws IOException{
        Alert successfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
        successfulAlert.setTitle("Successful");
        successfulAlert.setHeaderText("Pay Bill Successful.");
        successfulAlert.setContentText(accountType.getText() + " " + acc.getAccountNum() +
                " pay bill amount " + amountInput.getText() + " to " + recipientInput.getText() + ".\n" +
                "The new balance of this account " + acc.getAccountNum() + " is " + acc.display() + ".\n" +
                "Do you wish to pay another bill with this account?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        successfulAlert.getButtonTypes().clear();
        successfulAlert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = successfulAlert.showAndWait();

        if (option.isPresent()){
            if (option.get() == yes){
                amountInput.setText(null);
                recipientInput.setText(null);
            }else if(option.get() == no) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        }
    }


}
