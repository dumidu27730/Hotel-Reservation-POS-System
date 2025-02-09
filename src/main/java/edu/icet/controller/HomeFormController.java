package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;



public class HomeFormController {

    public AnchorPane anchorPaneHome;
    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnShowUserFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../../view/user/user_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowBookingFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/booking/booking_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowRoomFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/room/room_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowEmployeeFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/employee/emplyee_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowReportFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/report/report_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowDashboardFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/dashboard/dashboard_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnShowGuestFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../../view/guest/guest_form.fxml");

        assert  resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

       public void btnLogoutOnAction(ActionEvent actionEvent) {

           Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
           logoutAlert.setTitle("Hotel Store");
           logoutAlert.setContentText("Do you want to logout?");
           Optional<ButtonType> buttonType = logoutAlert.showAndWait();
           if (buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
               Stage stage = new Stage();
               try {
                   stage.setScene(new Scene(
                           FXMLLoader.load(getClass().getResource("../../../view/login_form.fxml"))));
                   stage.setTitle("Login");
                   stage.setResizable(false);
                   stage.show();
                   anchorPaneHome.getScene().getWindow().hide();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
    }
}
