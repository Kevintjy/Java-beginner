package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class StaffOtherAccountsPage {

    public static Account acc;


    public void StaffOtherAccountBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void StaffOtherAccountTransfer(ActionEvent actionEvent) throws IOException {
        StaffTransferPage.transferOut = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffTransferPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void StaffOtherAccountPayBill(ActionEvent actionEvent) throws IOException {
        StaffPayBillPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffPayBillPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void StaffOtherAccountDeposit(ActionEvent actionEvent) throws IOException {
        StaffDepositPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDepositPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void StaffOtherAccountWithdraw(ActionEvent actionEvent) throws IOException {
        StaffWithdrawPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffWithdrawPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    @FXML
    Label AccountAmount1;
    @FXML
    Label AccountAmount2;
    @FXML
    Label AccountAmount3;
    @FXML
    Label AccountRe1;
    @FXML
    Label AccountRe2;
    @FXML
    Label AccountRe3;
    @FXML
    Label AccountNum;
    @FXML
    Label AccountBalance;
    @FXML
    Label StaffAccountType;


    @FXML
    private void initialize() {

        if (acc instanceof Chequing) {
            StaffAccountType.setText("Chequing");
        } else if (acc instanceof Saving) {
            StaffAccountType.setText("Saving");
        } else {
            StaffAccountType.setText("Line Credit");
        }
        AccountNum.setText(acc.getAccountNum());
        AccountBalance.setText(String.valueOf(acc.display()));
        ArrayList<Transaction> trans = acc.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3){
            AccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            AccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            AccountAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            AccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            AccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            AccountRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2){
            AccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            AccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            AccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            AccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1){
            AccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            AccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }
}
