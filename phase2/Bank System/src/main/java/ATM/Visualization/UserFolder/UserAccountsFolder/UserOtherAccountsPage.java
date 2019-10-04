package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Chequing;
import main.java.ATM.Saving;
import main.java.ATM.Transaction;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class UserOtherAccountsPage {

    public static Account otherAccount;

    public void UserAccountBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void UserAccountTransfer(ActionEvent actionEvent) throws IOException {
        UserTransferPage.transferOut = otherAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserTransferPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void UserAccountPayBill(ActionEvent actionEvent) throws IOException {
        UserPayBillPage.acc = otherAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPayBillPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void UserAccountDeposit(ActionEvent actionEvent) throws IOException {
        UserDepositPage.account = otherAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void UserAccountWithdraw(ActionEvent actionEvent) throws IOException {
        UserWithdrawPage.account = otherAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserWithdrawPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    @FXML
    Label UserAccountAmount1;
    @FXML
    Label UserAccountAmount2;
    @FXML
    Label UserAccountAmount3;
    @FXML
    Label UserAccountRe1;
    @FXML
    Label UserAccountRe2;
    @FXML
    Label UserAccountRe3;
    @FXML
    Label UserAccountNum;
    @FXML
    Label UserAccountBalance;
    @FXML
    Label UserAccountType;


    @FXML
    private void initialize() {

        if (otherAccount instanceof Chequing) {
            UserAccountType.setText("Chequing");
        } else if (otherAccount instanceof Saving) {
            UserAccountType.setText("Saving");
        } else {
            UserAccountType.setText("Line Credit");
        }
        UserAccountNum.setText(otherAccount.getAccountNum());
        UserAccountBalance.setText(String.valueOf(otherAccount.display()));
        ArrayList<Transaction> trans = otherAccount.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3){
            UserAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserAccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            UserAccountAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            UserAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            UserAccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            UserAccountRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2){
            UserAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserAccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            UserAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            UserAccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1){
            UserAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }

}
