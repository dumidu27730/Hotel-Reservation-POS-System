package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashbordController {

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
}
