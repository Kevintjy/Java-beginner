package main.java.ATM.Visualization.ManagerFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;


import java.io.IOException;
import java.util.ArrayList;

public class ManagerUndoTransactionUserPage {
    public static int pageNumber = 1;
    public static Account account;

    public User u = ManagerUndoTransactionPage.u;

    private int getPageNumber(){
        int temp = u.account.getAccounts().size();
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
            if (index <= u.account.getAccounts().size() - 1){
                accountBatch.add(u.account.getAccounts().get(index));
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
        System.out.println(u.account.getAccounts());
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


    public void account1button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        if(a.size()==0){ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is no account.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}else{
        account = a.get(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
    }

    public void account2button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        if (a.size() >= 2) {
            account = a.get(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is no account.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}

    }

    public void account3button(ActionEvent actionEvent) throws IOException {
        ArrayList<Account> a = getCorrespondingAccounts();
        if (a.size() == 3){
            account = a.get(2);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is no account.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }

    public void lastPage(ActionEvent actionEvent) throws IOException {
        if (pageNumber != 1){
            pageNumber -= 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionUserPage.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionUserPage.fxml"));
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

    public void backMButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
