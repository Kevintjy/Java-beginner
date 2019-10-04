package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Staff;
import main.java.ATM.Transaction;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class StaffCreditAccountPage {

    public static Account acc;

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void creditDepositButton(ActionEvent actionEvent) throws IOException {
        StaffDepositPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void creditWithdrawButton(ActionEvent actionEvent) throws IOException {
        StaffWithdrawPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffWithdrawPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }


    @FXML
    Label CreditAmount1;
    @FXML
    Label CreditAmount2;
    @FXML
    Label CreditAmount3;
    @FXML
    Label CreditRe1;
    @FXML
    Label CreditRe2;
    @FXML
    Label CreditRe3;
    @FXML
    Label CreditNum;
    @FXML
    Label CreditBalance;


    @FXML
    private void initialize() {

        CreditNum.setText(acc.getAccountNum());
        CreditBalance.setText(String.valueOf(acc.display()));
        ArrayList<Transaction> trans = acc.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3){
            CreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            CreditAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            CreditAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            CreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            CreditRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            CreditRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2){
            CreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            CreditAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            CreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            CreditRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1){
            CreditAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            CreditRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }

}
