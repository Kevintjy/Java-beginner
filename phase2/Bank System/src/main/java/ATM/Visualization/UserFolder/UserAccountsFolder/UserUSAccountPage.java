package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

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

public class UserUSAccountPage {

    public static Account USAccount;

    public void UserUSAccountBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void UserUSAccountTransfer(ActionEvent actionEvent) throws IOException {
        UserTransferPage.transferOut = USAccount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserTransferPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    @FXML
    Label USAmount1;
    @FXML
    Label USAmount2;
    @FXML
    Label USAmount3;
    @FXML
    Label USRe1;
    @FXML
    Label USRe2;
    @FXML
    Label USRe3;
    @FXML
    Label USNum;
    @FXML
    Label USBalance;


    @FXML
    private void initialize() {

        USNum.setText(USAccount.getAccountNum());
        USBalance.setText(String.valueOf(USAccount.display()));

        ArrayList<Transaction> trans = USAccount.pastTransaction;
        int numberTrans = trans.size();
        if (numberTrans >= 3) {
            USAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            USAmount3.setText(String.valueOf(trans.get(numberTrans - 3).getAmount()));
            USRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            USRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
            USRe3.setText(trans.get(numberTrans - 3).getTransIn().getAccountNum());

        }
        if (numberTrans == 2) {
            USAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USAmount2.setText(String.valueOf(trans.get(numberTrans - 2).getAmount()));
            USRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
            USRe2.setText(trans.get(numberTrans - 2).getTransIn().getAccountNum());
        }
        if (numberTrans == 1) {
            USAmount1.setText(String.valueOf(trans.get(numberTrans - 1).getAmount()));
            USRe1.setText(trans.get(numberTrans - 1).getTransIn().getAccountNum());
        }

    }
}
