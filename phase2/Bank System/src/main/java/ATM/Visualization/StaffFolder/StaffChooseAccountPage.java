package main.java.ATM.Visualization.StaffFolder;

import com.sun.xml.internal.bind.XmlAccessorFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;
import main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder.StaffCreditAccountPage;
import main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder.StaffOtherAccountsPage;
import main.java.ATM.Visualization.StaffFolder.StaffAccountsFolder.StaffUSAccountPage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class StaffChooseAccountPage{
    public static int pageNumber = 1;
    public static Account account;

    public Staff s = StaffLoginPage.s;

    private int getPageNumber(){
        int temp = s.account.getAccounts().size();
        if (temp % 3 != 0){
            return temp / 3 + 1;
        }
        else return temp/3;
    }

    public ArrayList<Account> getCorrespondingAccounts(){
        ArrayList<Account> accountBatch = new ArrayList<>();
        ArrayList<Integer> accountIndex = new ArrayList<>();
        accountIndex.add(pageNumber*3-3);
        accountIndex.add(pageNumber*3-2);
        accountIndex.add(pageNumber*3-1);
        for (int index:accountIndex){
            if (index <= s.account.getAccounts().size() - 1){
                accountBatch.add(s.account.getAccounts().get(index));
            }
        }
        return accountBatch;
    }
    @FXML
    Label account1type;
    @FXML
    Label account2type;
    @FXML
    Label account3type;
    @FXML
    Label account3number;
    @FXML
    Label account2number;
    @FXML
    Label account1number;
    @FXML
    Label account3date;
    @FXML
    Label account2date;
    @FXML
    Label account1date;
    @FXML
    Label account1balance;
    @FXML
    Label account2balance;
    @FXML
    Label account3balance;


    @FXML
    private void initialize() {

        ArrayList<Account> a = getCorrespondingAccounts();
        int numberAccounts = a.size();
        System.out.println(s.account.getAccounts());
        if (numberAccounts == 3){
            account1type.setText(a.get(0).userid);
            account2type.setText(a.get(1).userid);
            account3type.setText(a.get(2).userid);
            account1number.setText(a.get(0).accountNum);
            account2number.setText(a.get(1).accountNum);
            account3number.setText(a.get(2).accountNum);
            account1date.setText(a.get(0).getCreatedDate());
            account2date.setText(a.get(1).getCreatedDate());
            account3date.setText(a.get(2).getCreatedDate());
            account1balance.setText(Double.toString(a.get(0).display()));
            account2balance.setText(Double.toString(a.get(1).display()));
            account3balance.setText(Double.toString(a.get(2).display()));
        }
        if (numberAccounts == 2){
            account1type.setText(a.get(0).userid);
            account2type.setText(a.get(1).userid);
            account1number.setText(a.get(0).accountNum);
            account2number.setText(a.get(1).accountNum);
            account1date.setText(a.get(0).getCreatedDate());
            account2date.setText(a.get(1).getCreatedDate());
            account1balance.setText(Double.toString(a.get(0).display()));
            account2balance.setText(Double.toString(a.get(1).display()));
        }
        if (numberAccounts == 1){
            account1type.setText(a.get(0).userid);
            account1number.setText(a.get(0).accountNum);
            account1date.setText(a.get(0).getCreatedDate());
            account1balance.setText(Double.toString(a.get(0).display()));
        }

    }

    @FXML
    public void account1button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        account = a.get(0);
        if (account instanceof Credit){
            StaffCreditAccountPage.acc = a.get(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                    "StaffCreditAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else if (account instanceof Saving || account instanceof Chequing || account instanceof Linecredit){
            StaffOtherAccountsPage.acc = a.get(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                    "StaffOtherAccountsPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        } else {
            StaffUSAccountPage.acc = a.get(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                    "StaffUSAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }

    }

    @FXML
    public void account2button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        if (a.size() >= 2) {
            account = a.get(1);
            if (account instanceof Credit){
                StaffCreditAccountPage.acc = a.get(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffCreditAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            } else if (account instanceof Saving || account instanceof Chequing || account instanceof Linecredit){
                StaffOtherAccountsPage.acc = a.get(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffOtherAccountsPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            } else {
                StaffUSAccountPage.acc = a.get(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffUSAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You do not have this account");
            alert.setHeaderText("Error");
            alert.showAndWait();

        }
    }

    @FXML
    public void account3button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        if (a.size() == 3){
            account = a.get(2);
            if (account instanceof Credit){
                StaffCreditAccountPage.acc = a.get(2);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffCreditAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            } else if (account instanceof Saving || account instanceof Chequing || account instanceof Linecredit){
                StaffOtherAccountsPage.acc = a.get(2);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffOtherAccountsPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            } else {
                StaffUSAccountPage.acc = a.get(2);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffAccountsFolder/" +
                        "StaffUSAccountPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You do not have this account");
            alert.setHeaderText("Error");
            alert.showAndWait();
        }
    }

    public void lastPage(ActionEvent actionEvent) throws IOException {
       if (pageNumber != 1){
           pageNumber -= 1;
           FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffChooseAccountPage.fxml"));
           AnchorPane anchorPane = loader.load();
           BankSystem.root.getChildren().setAll(anchorPane);
       }
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("This is your first page, you do not have last page");
           alert.setHeaderText("Error");
           alert.showAndWait();
       }

    }

    public void nextPage(ActionEvent actionEvent) throws IOException {
        if (pageNumber != getPageNumber()){
            pageNumber += 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffChooseAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is your last page, you do not have next page");
            alert.setHeaderText("Error");
            alert.showAndWait();
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}

