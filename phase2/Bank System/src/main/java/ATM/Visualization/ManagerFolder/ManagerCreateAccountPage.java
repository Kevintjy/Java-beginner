package main.java.ATM.Visualization.ManagerFolder;

import Model.Accounts;
import Model.Requests;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class ManagerCreateAccountPage {
    @FXML
    private TextField acnumInput;
    @FXML
    private PasswordField pwInput;
    private boolean created = false;


    public void createButton(ActionEvent actionEvent) {
        if(created){ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You already created the account for the request.Please press cancel to go back.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
        else{
        String accountNum = acnumInput.getText();
        String password = pwInput.getText();
        Accounts accounts = new Accounts();
        if (Accounts.foundAccountNum(accountNum) != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The account number already exist.");
            alert.setHeaderText("Authorization Failed.");
            alert.showAndWait();
        }
        else if(!ManagerLoginPage.m.getPassword().equals(password)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The password is incorrect.");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();}
        else{
            Request r = ManagerCheckRequestPage.r;
            User u = r.getUser();
        String type = r.getAccountType();
        String uId = u.getId();
        if(type.equals("Credit")){
        u.account.addAccounts(new Credit(accountNum,uId));}
        else if(type.equals("Line Credit")){
            u.account.addAccounts(new Linecredit(accountNum,uId));}
        else if(type.equals("Saving")){
            u.account.addAccounts(new Saving(accountNum,uId));}
        else if(type.equals("Chequing")){
            u.account.addAccounts(new Chequing(accountNum,uId));}
        else if(type.equals("US Account")){
            u.account.addAccounts(new USaccount(accountNum,uId));}
        created = true;
        Requests.requests.remove(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The account has been created, please press cancel to go back to the check requests page.");
            alert.setHeaderText("Success");
            alert.showAndWait();
        }
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckRequestPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }



}
