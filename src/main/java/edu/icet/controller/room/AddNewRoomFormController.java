package edu.icet.controller.room;

import edu.icet.dto.Room;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.RoomService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewRoomFormController implements Initializable {


    @FXML
    public ComboBox cmbRoomType;

    @FXML
    private ComboBox<String> txtAvailableStatus;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtPricePerNight;

    @FXML
    private TextField txtRoomNumber;

    RoomService service = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> roomType = FXCollections.observableArrayList();
        roomType.add("Single Room ");
        roomType.add("Double Room");
        roomType.add("Twin Room");
        roomType.add("Triple Room");
        roomType.add("Quad Room ");
        roomType.add("Deluxe Room");
        roomType.add("Executive Room");
        roomType.add("Suite");
        roomType.add("Junior Suite");
        roomType.add("Presidential Suite");
        roomType.add("Family Room");
        roomType.add("Connecting Room");
        roomType.add("Accessible Room");
        cmbRoomType.setItems(roomType);

        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Available");
        status.add("Occupied");
        txtAvailableStatus.setItems(status);

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Room room = new Room(
                txtRoomNumber.getText(),
                cmbRoomType.getValue().toString(),
                txtDescription.getText(),
                Float.parseFloat(txtPricePerNight.getText()),
                txtAvailableStatus.getValue().toString()
        );

        if(service.addRoom(room)){
            new Alert(Alert.AlertType.INFORMATION,"Room Add Success").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Room Not Add").show();
        }

    }


}
