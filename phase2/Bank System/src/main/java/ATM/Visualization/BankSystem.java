package main.java.ATM.Visualization;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.ATM.Account;
import main.java.ATM.Saving;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankSystem extends Application {
    private Users users = new Users();
    private Staffs staffs = new Staffs();
    private Accounts accounts = new Accounts();
    private ATMs atms = new ATMs();
    private Managers managers = new Managers();
    private Requests requests = new Requests();
    private Deposits deposits = new Deposits();

    public void toInitial(){
        managers.loadManager();
        staffs.loadStaffs();
        atms.loadATMs();
        users.loadUsers();
        requests.loadRequest();
        deposits.loadDeposits();
        accounts.loadAccounts();
        update_saving();
    }

    public void eventExit(){
        users.writeUsersBack();
        staffs.writeStaffsBack();
        managers.writeBackToFile();
        atms.writeBackToFile();
        accounts.writeAccountsBack();
        requests.writeBackToFile();
    }

    public void update_saving() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        if (day.equals("01")){
            try {
                BufferedReader in = new BufferedReader(new FileReader("data/config_file"));
                String line = in.readLine();
                String configYear = line.split("-")[0];
                String configMonth = line.split("-")[1];
                in.close();
                if ((configYear.equals(year) && Integer.parseInt(month) == Integer.parseInt(configMonth) + 1) ||
                        ( Integer.parseInt(year) == Integer.parseInt(configYear) + 1
                                && Integer.parseInt(configMonth) == 12 && Integer.parseInt(month) == 1)){
                    try{
                        FileWriter writer = new FileWriter("data/config_file");
                        writer.write(date);
                        writer.close();}
                    catch (Exception e){e.printStackTrace();}
                    for (Account a: Accounts.accounts){
                        if (a instanceof Saving){
                            ((Saving) a).MonthlyIncrease();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }}
    }


    public static BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        toInitial();
        root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseIdentityPage.fxml"));
        Scene scene = new Scene(root, 600, 400);
        root.setCenter(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
