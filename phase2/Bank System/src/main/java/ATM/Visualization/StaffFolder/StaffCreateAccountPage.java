package main.java.ATM.Visualization.StaffFolder;

import Model.Requests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.ATM.Request;
import main.java.ATM.Staff;
import main.java.ATM.Visualization.BankSystem;
import java.io.IOException;
import java.util.Optional;

public class StaffCreateAccountPage {

    private Button refreshButton;

    private Button previousButton;

    private Button nextButton;

    public Staff staff = StaffLoginPage.s;

    public static int page = 1;

    private int maxPage = Requests.requests.size() / 5;

    private final int pageSize = 5;

    @FXML
    public GridPane gridPane;

    @FXML
    public void initialize() {

        for (int i = 0; i < Math.min(pageSize, Requests.requests.size() - pageSize * (page - 1)); i++) {

            final int index = i + 1;

            final Request request = Requests.requests.get(index - 1 + (page - 1) * pageSize);

            Label id = new Label(request.getUser().getId());
            gridPane.add(id, 0, index);
            gridPane.setMargin(id, new Insets(0, 0, 0, 25));

            Label type = new Label(request.getAccountType());
            gridPane.add(type, 1, index);
            gridPane.setMargin(type, new Insets(0, 0, 0, 50));

            Button userReview = new Button("Review");
            gridPane.add(userReview, 2, index);
            gridPane.setMargin(userReview, new Insets(0, 0, 0, 15));

            Button approve = new Button("Approve");
            gridPane.add(approve, 3, index);

            Button reject = new Button("Reject");
            gridPane.add(reject, 4, index);
            gridPane.setMargin(reject, new Insets(0, 0, 0, 5));

            Button ignore = new Button("Ignore");
            gridPane.add(ignore, 5, index);
            gridPane.setMargin(ignore, new Insets(0, 0, 0, 25));

            userReview.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    final Stage userWindow = new Stage();
                    Group windowRoot = new Group();
                    Scene scene = new Scene(windowRoot, 300, 200);
                    userWindow.setScene(scene);
                    userWindow.show();
                    VBox vBox = new VBox();

                    Label userInfo = new Label();
                    userInfo.setText("User Name: " + request.getUser().getName() + "\n"
                            + "User ID:" + request.getUser().getId() + "\n"
                            + "This user has " + request.getUser().account.getAccounts().size() + " account(s).");

                    vBox.getChildren().add(userInfo);
                    windowRoot.getChildren().add(vBox);
                }
            });

            approve.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert approveAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    approveAlert.setTitle("Reject Request");
                    approveAlert.setHeaderText("Confirmation Needed.");
                    approveAlert.setContentText("Are you sure to approve the " + request.getAccountType() +
                            " creation request from " + request.getUser().getId() + "?");

                    ButtonType yes = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");

                    approveAlert.getButtonTypes().clear();
                    approveAlert.getButtonTypes().addAll(yes, no);
                    Optional<ButtonType> option = approveAlert.showAndWait();

                    if (option.isPresent()) {
                        if (option.get() == yes) {
                            staff.createAccount(request);
                            gridPane.getChildren().remove(approve);
                            gridPane.getChildren().remove(reject);
                            gridPane.getChildren().remove(ignore);
                            gridPane.add(new Label("Approved"), 3, index);
                            disableButton();
                        }
                    }


                }
            });

            reject.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert rejectAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    rejectAlert.setTitle("Reject Request");
                    rejectAlert.setHeaderText("Confirmation Needed.");
                    rejectAlert.setContentText("Are you sure to reject the " + request.getAccountType() +
                            " creation request from " + request.getUser().getId() + "?");

                    ButtonType yes = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");

                    rejectAlert.getButtonTypes().clear();
                    rejectAlert.getButtonTypes().addAll(yes, no);
                    Optional<ButtonType> option = rejectAlert.showAndWait();

                    if (option.isPresent()) {
                        if (option.get() == yes) {
                            gridPane.getChildren().remove(approve);
                            gridPane.getChildren().remove(reject);
                            gridPane.getChildren().remove(ignore);
                            gridPane.add(new Label("Rejected"), 4, index);
                            Requests.requests.remove(index - 1 + (page - 1) * pageSize);
                            disableButton();
                        }
                    }
                }
            });

            ignore.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gridPane.getChildren().remove(approve);
                    gridPane.getChildren().remove(reject);
                    gridPane.getChildren().remove(ignore);
                    gridPane.add(new Label("Ignored"), 5, index);
                    Requests.requests.remove(index - 1 + (page - 1) * pageSize);
                    Requests.requests.add(request);
                    disableButton();
                }
            });

        }

    }


    public void previousButton(ActionEvent actionEvent) throws IOException {
        if(page > 1) {
            page--;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffCreateAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is the First Page.");
            alert.setHeaderText("Previous Page unavailable.");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        if (page < maxPage) {
            page++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffCreateAccountPage.fxml"));
            AnchorPane anchorPane = loader.load();
            BankSystem.root.getChildren().setAll(anchorPane);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is the Last Page.");
            alert.setHeaderText("Next Page unavailable.");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffFirstPage.fxml"));
        AnchorPane Pane = loader.load();
        BankSystem.root.getChildren().setAll(Pane);
    }

    public void refreshButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffCreateAccountPage.fxml"));
        AnchorPane anchorPane = loader.load();
        BankSystem.root.getChildren().setAll(anchorPane);
    }

    //Helper
    private void disableButton(){
        nextButton.setDisable(true);
        previousButton.setDisable(true);
    }
}