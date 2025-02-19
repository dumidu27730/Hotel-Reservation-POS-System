package edu.icet.controller.booking;

import edu.icet.db.DBConnection;
import edu.icet.dto.Booking;
import edu.icet.dto.Guest;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.BookingService;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewBookingFormController implements Initializable {


    @FXML
    public ComboBox<String> cmbReservationStatus;

    @FXML
    public TextField txtCheckInTime;

    @FXML
    public TextField txtDays;
    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtGuestId;
    @FXML
    public TextField txtTotalAmount;

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
    BookingService service3 = ServiceFactory.getInstance().getServiceType(ServiceType.BOOKING);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roomTypeList = FXCollections.observableArrayList();
        service2.getAll().forEach(Room -> roomTypeList.add(Room.getRoomType()));
        cmbRoomType.setItems(roomTypeList);

        // Set default selection (optional)
        cmbRoomType.getSelectionModel().selectFirst(); // Ensures a value is selected initially

        // Handle ComboBox value change (onAction)
        cmbRoomType.setOnAction(event -> {
            String roomType = cmbRoomType.getValue();  // Get the selected room type
            if (roomType != null && !roomType.isEmpty()) {
                loadRoomNumbers(roomType);  // Fetch available rooms based on selected room type
            }
        });

        // Rest of the code to populate reservation status, etc.
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Check-IN");
        status.add("Check-OUT");
        status.add("Pending");
        status.add("Confirmed");
        status.add("Canceled");
        cmbReservationStatus.setItems(status);
    }

    private void loadRoomNumbers(String roomType) {
        ObservableList<String> roomNo = FXCollections.observableArrayList();

        String SQL = "SELECT room_number, price_per_night FROM rooms WHERE room_type = ? AND availability_status = 'Available'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, roomType);  // Safely insert the room type value into the query

            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear the list before adding new items
            roomNo.clear();

            while (resultSet.next()) {
                roomNo.add(resultSet.getString("room_number"));
                double pricePerNight = resultSet.getDouble("price_per_night");
                txtTotalAmount.setText(String.format("%.2f", pricePerNight));
            }


//
//            while (resultSet.next()) {
//                String roomNumber = resultSet.getString("room_number");
//                double pricePerNight = resultSet.getDouble("price_per_night");
//
//                // Add room number and price to the ComboBox in the format: "Room 101 - Price: 150.0"
//                roomNo.add(roomNumber + " - Price: " + pricePerNight);
//
//                // Now, calculate the total amount and set it in txtTotalAmount (if days are entered)
//                if (!txtDays.getText().isEmpty()) {
//                    int numberOfDays = Integer.parseInt(txtDays.getText());
//                    double totalAmount = pricePerNight * numberOfDays;
//                    txtTotalAmount.setText(String.format("%.2f", totalAmount));  // Format to 2 decimal places
//                }
//            }




            // Set the available room numbers to the combo box
            cmbRoom.setItems(roomNo);

            if (roomNo.isEmpty()) {
                System.out.println("No available rooms found.");
            } else {
                System.out.println("Available Room Numbers: " + roomNo);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void setCheckInTime(String time) {
        System.out.println(time);
        txtCheckInTime.setText(time);
    }

    public void setUserId(String userId) {

        txtUserId.setText(userId);
    }



    @FXML
    void btnAddBookingOnAction(ActionEvent event) {
        Guest guest1 = new Guest(
                txtName.getText(),
                txtNICNUmber.getText(),
                txtAddress.getText(),
                txtGuestComment.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText()
        );
        if(service1.addGuest(guest1)){
            new Alert(Alert.AlertType.INFORMATION,"Guest Add Success");
        }else{
            new Alert(Alert.AlertType.ERROR,"Guest Not Add");
        }
        Guest guest = service1.searchGuest(txtPhoneNumber.getText());
        txtGuestId.setText(String.valueOf(guest.getId()));

        Booking booking = new Booking(

                Integer.parseInt(txtUserId.getText()),
                Integer.parseInt(txtGuestId.getText()),
                cmbRoom.getValue(), // Room number selected
                dateCheckIn.getValue(),
                txtCheckInTime.getText(),
                Integer.parseInt(txtDays.getText()),
                dateCheckOut.getValue(),
                Integer.parseInt(txtGuestQty.getText()),
                Float.parseFloat(txtTotalAmount.getText()), // Total amount
                cmbReservationStatus.getValue()
        );
        if(service3.addBooking(booking)){
            new Alert(Alert.AlertType.INFORMATION,"Booking Add Success").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Booking Not Add").show();
        }
    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchByPhoneNumberOnAction(ActionEvent event) {
        Guest guest = service1.searchGuest(txtSearchByPhoneNumber.getText());

       if(guest==null){
           Guest guest1 = new Guest(
                   txtName.getText(),
                   txtNICNUmber.getText(),
                   txtAddress.getText(),
                   txtGuestComment.getText(),
                   txtEmail.getText(),
                   txtPhoneNumber.getText()
           );
           if(service1.addGuest(guest1)){
               new Alert(Alert.AlertType.INFORMATION,"Guest Add Success");
           }else{
               new Alert(Alert.AlertType.ERROR,"Guest Not Add");
               Guest guest2 = service1.searchGuest(txtPhoneNumber.getText());
               txtGuestId.setText(String.valueOf(guest2.getId()));
           }

       }else{
           setTextToValues(guest);
       }
    }

    private void setTextToValues(Guest newValue) {
        txtGuestId.setText(String.valueOf(newValue.getId()));
        txtName.setText(newValue.getName());
        txtNICNUmber.setText(newValue.getNicNumber());
        txtAddress.setText(newValue.getAddress());
        txtGuestComment.setText(newValue.getGuestRequest());
        txtEmail.setText(newValue.getEmail());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
    }

}
