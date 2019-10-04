package main.java.ATM.Visualization.UserFolder.UserAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class UserDepositPage {

    public static Account account;

    public void depositChequeButton(ActionEvent actionEvent) throws IOException {
        UserDepositChequePage.acc = account;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDepositChequePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void depositCashButton(ActionEvent actionEvent) throws IOException {
        UserDepositCashPage.acc = account;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDepositCashPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        UserDepositChequePage.acc = account;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UserChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
