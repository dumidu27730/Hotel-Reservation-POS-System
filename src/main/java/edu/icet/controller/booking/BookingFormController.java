package edu.icet.controller.booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingFormController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAvailabilityStatus2;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colCheckInDate;

    @FXML
    private TableColumn<?, ?> colCheckInTime;

    @FXML
    private TableColumn<?, ?> colCheckOutDate;

    @FXML
    private TableColumn<?, ?> colDays;

    @FXML
    private TableColumn<?, ?> colGuestCount;

    @FXML
    private TableColumn<?, ?> colGuestId;

    @FXML
    private TableColumn<?, ?> colReservationStatus;

    @FXML
    private TableColumn<?, ?> colRoomNumber;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<?> tblRooms;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtCheckInDate;

    @FXML
    private TextField txtCheckInTime;

    @FXML
    private TextField txtCheckOutDate;

    @FXML
    private TextField txtDays;

    @FXML
    private TextField txtGuestCount;

    @FXML
    private TextField txtGuestId;

    @FXML
    private TextField txtReservationStatus;

    @FXML
    private TextField txtRoomNumber;

    @FXML
    private TextField txtSerchByRoomNo;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnCreateNewBookingOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/booking/add_newbooking_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
