package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import Model.ATMs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.java.ATM.ATM;
import main.java.ATM.Account;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class StaffDepositCashPage {

    public static Account acc;

    @FXML
    Text accountNumber;

    @FXML
    private TextField fiveDollarInput;

    @FXML
    private TextField tenDollarInput;

    @FXML
    private TextField twentyDollarInput;

    @FXML
    private TextField fiftyDollarInput;

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void enterButton(ActionEvent actionEvent) throws IOException {
        if (amountFormatCheckFail(fiveDollarInput.getText()) && amountFormatCheckFail(tenDollarInput.getText()) &&
        amountFormatCheckFail(twentyDollarInput.getText()) && amountFormatCheckFail(fiftyDollarInput.getText())) {
            Alert formatAlert = new Alert(Alert.AlertType.ERROR);
            formatAlert.setContentText("Please enter a valid amount. Try again.");
            formatAlert.setHeaderText("Input Unrecognizable");
            formatAlert.setTitle("Invalid input");
            formatAlert.showAndWait();
        }else if(ATMs.workATMs.size() == 0){
            Alert noWorkingATMAlert = new Alert(Alert.AlertType.ERROR);
            noWorkingATMAlert.setTitle("Error");
            noWorkingATMAlert.setHeaderText("No working ATM.");
            noWorkingATMAlert.setContentText("Please Contact the Bank Manager.");
            noWorkingATMAlert.showAndWait();
        }else {
            int fiveDollar = Integer.parseInt(fiveDollarInput.getText());
            int tenDollar = Integer.parseInt(tenDollarInput.getText());
            int twentyDollar = Integer.parseInt(twentyDollarInput.getText());
            int fiftyDollar = Integer.parseInt(fiftyDollarInput.getText());
            double amount = 5 * fiveDollar + 10 * tenDollar + 20 * twentyDollar + 50 * fiftyDollar;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(fiveDollar + " five dollar bill, \n" + tenDollar + " ten dollar bill, \n " +
                    twentyDollar + " twenty dollar bill, and \n" + fiftyDollar + " fifty dollar bill will be deposited" +
                    " to your account " + acc.getAccountNum() + ". \n"
                    + "Please choose one of the ATM to proceed. Click cancel if you want to modify.");
            alert.setHeaderText("Please follow the instruction:");
            alert.setTitle("Confirmation Needed");
            alert.showAndWait();

            ArrayList<ButtonType> atmButton = new ArrayList<>();
            for (ATM atm: ATMs.workATMs){
                atmButton.add(new ButtonType(atm.id));

            }

            alert.getButtonTypes().clear();
            for (ButtonType bt: atmButton){
                alert.getButtonTypes().add(bt);
            }

            Optional<ButtonType> option = alert.showAndWait();
            if(option.isPresent()){
                int index = atmButton.indexOf(option.get());
                if (index > -1){
                    ATM chosenATM = ATMs.workATMs.get(index);
                    HashMap<String, Object> cash = new HashMap<>();
                    cash.put("fiveDollar", fiveDollar);
                    cash.put("tenDollar", tenDollar);
                    cash.put("twentyDollar", twentyDollar);
                    cash.put("fiftyDollar", fiftyDollar);
                    chosenATM.AddMoney(cash);
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


    //Helper
    private void successfulProcedure() throws IOException {
        Alert successfulAlert = new Alert(Alert.AlertType.CONFIRMATION);
        successfulAlert.setTitle("Successful");
        successfulAlert.setHeaderText("Cash Deposited success");
        successfulAlert.setContentText("The new balance of account " + acc.getAccountNum() + " is " +
                acc.display() + ".\n" +
                "Do you wish to make another cash deposit to this account?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        successfulAlert.getButtonTypes().clear();
        successfulAlert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = successfulAlert.showAndWait();

        if (option.isPresent()) {
            if (option.get() == yes) {
                fiveDollarInput.setText(null);
                tenDollarInput.setText(null);
                twentyDollarInput.setText(null);
                fiftyDollarInput.setText(null);
            } else if (option.get() == no) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        }
    }
}
