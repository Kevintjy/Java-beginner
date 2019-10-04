package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Transaction;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class StaffUSAccountPage {

    public static Account acc;

    public void USAccountBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void USAccountTransfer(ActionEvent actionEvent) throws IOException {
        StaffTransferPage.transferOut = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffTransferPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }


    @FXML
    Label USAccountAmount1;
    @FXML
    Label USAccountAmount2;
    @FXML
    Label USAccountAmount3;
    @FXML
    Label USAccountRe1;
    @FXML
    Label USAccountRe2;
    @FXML
    Label USAccountRe3;
    @FXML
    Label USAccountNum;
    @FXML
    Label USAccountBalance;


    @FXML
    private void initialize() {

        USAccountNum.setText(acc.getAccountNum());
        USAccountBalance.setText(String.valueOf(acc.display()));

        ArrayList<Transaction> trans = acc.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3){
            USAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USAccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            USAccountAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            USAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            USAccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            USAccountRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2){
            USAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USAccountAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            USAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            USAccountRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1){
            USAccountAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USAccountRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }
}
