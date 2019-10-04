package main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Staff;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class StaffDepositPage {

    public static Account acc;

    public void depositChequeButton(ActionEvent actionEvent) throws IOException{
        StaffDepositChequePage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDepositChequePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);

    }

    public void depositCashButton(ActionEvent actionEvent) throws IOException {
        StaffDepositCashPage.acc = acc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDepositCashPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StaffChooseAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
