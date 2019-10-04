package main.java.ATM.Visualization.ManagerFolder;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.Transaction;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerUndoTransactionAccountPage {
    public static int page = 0;
    public static Transaction t;
    public static ArrayList<Transaction> transactions = ManagerUndoTransactionUserPage.account.pastTransaction;
    @FXML
    Label transactionField;

    @FXML
    private void initialize() {
        if(transactions.size()> page){ t = transactions.get(page); transactionField.setText(t.toString());}
        else{  t = null;transactionField.setText("No more transactions."); }
    }
    public void undoButton(ActionEvent actionEvent)throws IOException {if(t != null){
        if(t.undoTrans()){
        transactions.remove(t);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);}else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Trans-in account has no money for refund.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}}else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no transaction.");
        alert.setHeaderText("Failed");
        alert.showAndWait();}
    }

    public void backASButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionUserPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void lastTButton(ActionEvent actionEvent) throws IOException{
        if(page > 0){ page -= 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the first transaction.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }

    public void nextTButton(ActionEvent actionEvent) throws IOException{
        int max = transactions.size()-1;
        if(page < max){ page += 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUndoTransactionAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the last transaction.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }
}
