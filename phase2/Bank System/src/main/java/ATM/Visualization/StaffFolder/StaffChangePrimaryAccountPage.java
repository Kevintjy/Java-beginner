package main.java.ATM.Visualization.StaffFolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Staff;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
public class StaffChangePrimaryAccountPage {
    @FXML
    public TextField accountInput;
    @FXML
    Label account;
    public Staff s = StaffLoginPage.s;

    @FXML
    public void initialize(){
        if(s.account.getDefaultAccount() != null ){
            account.setText((s.account.getDefaultAccount().getAccountNum()));
        }
        else{
            account.setText("You need to set default account");
        }
    }
    public void ChangePrimaryAccountButton(ActionEvent actionEvent)throws IOException {
        String newaccount = accountInput.getText();
        boolean flag = true;
        for (Account account: s.account.getAccounts()){
            if(account.getAccountNum().equals(newaccount)){
                s.changePrimaryAccount(newaccount);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("the new primary account is" +"  " + newaccount);
                alert.setHeaderText("change primary account success");
                alert.showAndWait();
                flag = false;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
                AnchorPane anchorPane = loader.load();
                BankSystem.root.getChildren().setAll(anchorPane);
            }
        }
        if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This account is not valid");
            alert.setHeaderText("Not valid account");
            alert.showAndWait();
        }


    }

    public void CancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
