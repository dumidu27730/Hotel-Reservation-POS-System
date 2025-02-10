package edu.icet.controller.booking;

import edu.icet.dto.Guest;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.GuestService;
import edu.icet.service.custom.RoomService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewBookingFormController implements Initializable {

    public ComboBox cmbReservationStatus;
    @FXML
    private ComboBox<String> cmbRoom;

    @FXML
    private ComboBox<String> cmbRoomType;


    @FXML
    private DatePicker dateCheckIn;

    @FXML
    private DatePicker dateCheckOut;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtGuestComment;

    @FXML
    private TextField txtGuestQty;

    @FXML
    private TextField txtNICNUmber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearchByPhoneNumber;

    GuestService service1 = ServiceFactory.getInstance().getServiceType(ServiceType.GUEST);
    RoomService service2 = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> roomTypeList = FXCollections.observableArrayList();
        service2.getAll().forEach(Room ->
                roomTypeList.add(Room.getRoomType()) );
                cmbRoomType.setItems(roomTypeList);

 ObservableList<String> roomNoList = FXCollections.observableArrayList();
        service2.getAll().forEach(Room ->
                roomNoList.add(Room.getRoomNumber()) );
                cmbRoom.setItems(roomNoList);

        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Check-IN");
        status.add("Check-OUT");
        status.add("Pending");
        status.add("Confirmed");
        status.add("Canceled");
        cmbReservationStatus.setItems(status);
    }

    @FXML
    void btnAddBookingOnAction(ActionEvent event) {


        Guest guest = new Guest(
                txtName.getText(),
                txtNICNUmber.getText(),
                txtAddress.getText(),
                txtGuestComment.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText()
        );
        System.out.println("in controller"+guest);
        if(service1.addGuest(guest)){
            new Alert(Alert.AlertType.INFORMATION,"Guest Add Success").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Guest Not Add").show();
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchByPhoneNumberOnAction(ActionEvent event) {
        Guest guest = service1.searchGuest(txtSearchByPhoneNumber.getText());
        setTextToValues(guest);
    }

    private void setTextToValues(Guest newValue) {
        txtName.setText(newValue.getName());
        txtNICNUmber.setText(newValue.getNicNumber());
        txtAddress.setText(newValue.getAddress());
        txtGuestComment.setText(newValue.getGuestRequest());
        txtEmail.setText(newValue.getEmail());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
    }

}
