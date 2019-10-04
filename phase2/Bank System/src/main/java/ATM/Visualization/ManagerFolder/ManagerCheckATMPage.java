package main.java.ATM.Visualization.ManagerFolder;

import Model.ATMs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.ATM.ATM;
import main.java.ATM.Visualization.BankSystem;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerCheckATMPage {
    public static int page = 0;
    public static ATM a;
    private ArrayList<ATM> atms = ATMs.nonWorkATMs;
    @FXML
    Label alertField;

    @FXML
    private void initialize() {
        if(atms.size()> page){ a = atms.get(page); alertField.setText(a.toString());}
        else{  a = null;alertField.setText("No more ATMs."); }
    }

    public void addMButton(ActionEvent actionEvent)throws IOException {if(a != null){
        a.SetMoney();
        atms.remove(a);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckATMPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);}else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no ATM.");
        alert.setHeaderText("Failed");
        alert.showAndWait();}
    }

    public void backMButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerChoicePage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    public void lastAButton(ActionEvent actionEvent) throws IOException{
        if(page > 0){ page -= 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckATMPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the first ATM.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }

    public void nextAButton(ActionEvent actionEvent) throws IOException{
        int max = atms.size() - 1;
        if(page < max){ page += 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCheckATMPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);}
        else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("It's already the last ATM.");
            alert.setHeaderText("Failed");
            alert.showAndWait();}
    }


}
