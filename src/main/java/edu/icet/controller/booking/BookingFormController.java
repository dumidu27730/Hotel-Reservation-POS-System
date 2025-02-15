package edu.icet.controller.booking;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.Booking;
import edu.icet.dto.Room;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.BookingService;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingFormController implements Initializable {
    @FXML
    public TableView<Booking> tblBookings;
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
    private TableView<Booking> tblRooms;

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

    BookingService service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOKING);


    @FXML
    void btnCreateNewBookingOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/booking/add_newbooking_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Booking booking = service.searchBooking(txtSerchByRoomNo.getText());
        if (booking != null) {
            setTextToValues(booking);
        } else {
            new Alert(Alert.AlertType.WARNING, "Booking Not Found!").show();
        }
    }
    private void setTextToValues(Booking newValue) {
        txtBookingId.setText(String.valueOf(newValue.getBookingId()));
        txtUserId.setText(String.valueOf(newValue.getUserId()));
        txtGuestId.setText(String.valueOf(newValue.getGuestId()));
        txtRoomNumber.setText(String.valueOf(newValue.getRoomNumber()));
        txtCheckInDate.setText(String.valueOf(newValue.getCheckInDate()));
        txtCheckInTime.setText(newValue.getCheckInTime());
        txtDays.setText(String.valueOf(newValue.getDays()));
        txtCheckOutDate.setText(String.valueOf(newValue.getCheckOutDate()));
        txtGuestCount.setText(String.valueOf(newValue.getGuestCount()));
        txtTotalAmount.setText(String.valueOf(newValue.getTotalAmount()));
        txtReservationStatus.setText(newValue.getReservationStatus());
    }
    public void loadTable(){
        ObservableList<Booking> all = service.getAll();
        tblBookings.setItems(all);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckInTime.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        colCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colGuestCount.setCellValueFactory(new PropertyValueFactory<>("guestCount"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colReservationStatus.setCellValueFactory(new PropertyValueFactory<>("reservationStatus"));


        colAction.setCellFactory(param -> new TableCell() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {
                    Booking booking = new Booking(
                            Integer.parseInt(txtBookingId.getText()),
                            Integer.parseInt(txtUserId.getText()),
                            Integer.parseInt(txtGuestId.getText()),
                            txtRoomNumber.getText(),
                            LocalDate.parse(txtCheckInDate.getText()),
                            txtCheckInTime.getText(),
                            Integer.parseInt(txtDays.getText()),
                            LocalDate.parse(txtCheckOutDate.getText()),
                            Integer.parseInt(txtGuestCount.getText()),
                            Float.parseFloat(txtTotalAmount.getText()),
                            txtReservationStatus.getText()
                    );
                    if (service.updateBooking(booking)){
                        new Alert(Alert.AlertType.INFORMATION,"Booking Updated!!").show();
                        loadTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Booking Not Updated!!").show();
                    }


                    Booking booking1 = (Booking) getTableView().getItems().get(getIndex());
                    setTextToValues(booking);

                });

                // Delete Button Action (Remove customer from list)
                btnDelete.setOnAction(event -> {
                    Booking booking = (Booking) getTableView().getItems().get(getIndex());
                    boolean isDeleted = service.deleteBooking(String.valueOf(booking.getRoomNumber()));

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Booking Deleted!").show();
                        loadTable(); // Refresh table after deletion
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Booking Room!").show();
                    }
                });
            }

            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionButtons);
                }
            }
        });

        loadTable();

        tblBookings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Booking) newValue);
            }
        });
    }
}
