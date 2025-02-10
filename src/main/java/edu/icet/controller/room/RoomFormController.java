package edu.icet.controller.room;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.Room;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.RoomService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    public TableView <Room>tblRooms;

    @FXML
    public TextField txtRoomNo;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAvailabilityStatus;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPricePerNight;

    @FXML
    private TableColumn<?, ?> colRoomNo;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TextField txtAvailabilityStatus;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPricePerNight;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtSerchByRoomNo;

    RoomService service = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);

    @FXML
    void btnAddNewRoomOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/room/add_new_room_form.fxml"))));
        stage.show();
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Room room = service.searchRoom(txtSerchByRoomNo.getText());
        if (room != null) {
            setTextToValues(room);
        } else {
            new Alert(Alert.AlertType.WARNING, "Room Not Found!").show();
        }
    }

    private void setTextToValues(Room newValue) {
        txtRoomNo.setText(newValue.getRoomNumber());
        txtRoomType.setText(newValue.getRoomType());
        txtDescription.setText(newValue.getDescription());
        txtPricePerNight.setText(String.valueOf(newValue.getPricePerNight()));
        txtAvailabilityStatus.setText(newValue.getAvailableStatus());
    }
    public void loadTable(){
        ObservableList<Room> all = service.getAll();
        tblRooms.setItems(all);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colAvailabilityStatus.setCellValueFactory(new PropertyValueFactory<>("availableStatus"));


        colAction.setCellFactory(param -> new TableCell() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {


                    Room room = new Room(
                            txtRoomNo.getText(),
                            txtRoomType.getText(),
                            txtDescription.getText(),
                            Float.parseFloat(txtPricePerNight.getText()),
                            txtAvailabilityStatus.getText()
                    );
                    if (service.updateRoom(room)){
                        new Alert(Alert.AlertType.INFORMATION,"Room Updated!!").show();
                        loadTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Room Not Updated!!").show();
                    }


                    Room room1 = (Room) getTableView().getItems().get(getIndex());
                    setTextToValues(room);

                });

                // Delete Button Action (Remove customer from list)
                btnDelete.setOnAction(event -> {
                    Room room = (Room) getTableView().getItems().get(getIndex());
                    boolean isDeleted = service.deleteRoom(String.valueOf(room.getRoomNumber()));

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Room Deleted!").show();
                        loadTable(); // Refresh table after deletion
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Delete Room!").show();
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

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Room) newValue);
            }
        });
    }
}
