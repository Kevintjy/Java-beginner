package main.java.ATM.Visualization.ManagerFolder;

import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Account;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;

public class ShareaccountPage {
    public PasswordField Userpassword;
    public TextField Sharedaccount;
    public TextField Sharedid;

    public void Sharebutton(ActionEvent actionEvent)throws IOException {
    Account acc = Accounts.foundAccountNum(Sharedaccount.getText());
    String id = Sharedid.getText();
    String pw = Userpassword.getText();
    if(acc != null){
        if(acc.getOwner().getPassword() .equals(pw)) {
            acc.shared(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Shared success.");
            alert.setHeaderText("Authorization Success");
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Join account failed");
            alert.setHeaderText("Authorization Failed");
            alert.showAndWait();
        }
    }
    else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Join account failed");
        alert.setHeaderText("Authorization Failed");
        alert.showAndWait();
    }
    }


    public void Cancelbutton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }
}
