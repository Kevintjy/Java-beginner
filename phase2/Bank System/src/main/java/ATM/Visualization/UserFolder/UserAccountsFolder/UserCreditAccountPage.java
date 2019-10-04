package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Staff;
import main.java.ATM.Transaction;
import main.java.ATM.Visualization.BankSystem;
import main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder.StaffDepositPage;
import main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder.StaffWithdrawPage;

import java.io.IOException;
import java.util.ArrayList;

public class UserCreditAccountPage {

    public static Account cAccount;

    public void UserCreditBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void UserCreditDepositButton(ActionEvent actionEvent) throws IOException {
        UserDepositPage.account = cAccount;
        UserDepositChequePage.acc = cAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void UserCreditWithdrawButton(ActionEvent actionEvent) throws IOException {
        UserWithdrawPage.account = cAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserWithdrawPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    @FXML
    Label UserCreditAmount1;
    @FXML
    Label UserCreditAmount2;
    @FXML
    Label UserCreditAmount3;
    @FXML
    Label UserCreditRe1;
    @FXML
    Label UserCreditRe2;
    @FXML
    Label UserCreditRe3;
    @FXML
    Label UserCreditNum;
    @FXML
    Label UserCreditBalance;


    @FXML
    private void initialize() {

        UserCreditNum.setText(cAccount.getAccountNum());
        UserCreditBalance.setText(String.valueOf(cAccount.display()));
        ArrayList<Transaction> trans = cAccount.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3){
            UserCreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserCreditAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            UserCreditAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            UserCreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            UserCreditRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            UserCreditRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2){
            UserCreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserCreditAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            UserCreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            UserCreditRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1){
            UserCreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            UserCreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }
}
