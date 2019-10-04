package main.java.ATM.Visualization.ManagerFolder;

import Model.Requests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.*;
import main.java.ATM.Visualization.BankSystem;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ManagerCheckRequestPage {
    public static int page = 0;
    public static Request r;
    @FXML
    Label requestField;

    @FXML
    private void initialize() {
        ArrayList<Request> requests = Requests.requests;
        if(requests.size()> page){ r = Requests.requests.get(page); requestField.setText(r.toString());}
        else{  r = null;requestField.setText("No more request."); }
    }

    public void approveButton(ActionEvent actionEvent) throws IOException { if(r != null){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCreateAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);}else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no request.");
        alert.setHeaderText("Failed");
        alert.showAndWait();}
    }

    public void rejectButton(ActionEvent actionEvent)throws IOException{if(r != null){
        Requests.requests.remove(r);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckRequestPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);}else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no request.");
        alert.setHeaderText("Failed");
        alert.showAndWait();}
    }

    public void backMButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void lastRButton(ActionEvent actionEvent) throws IOException{
        if(page > 0){ page -= 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckRequestPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the first request.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }

    public void nextRButton(ActionEvent actionEvent) throws IOException{
        int max = Requests.requests.size() - 1;
        if(page < max){ page += 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckRequestPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the last request.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }





}
