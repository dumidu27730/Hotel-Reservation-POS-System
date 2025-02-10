package edu.icet.controller.guest;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.Guest;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.GuestService;
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

public class GuestFormController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGuestId;

    @FXML
    private TableColumn<?, ?> colGuestRequest;

    @FXML
    private TableColumn<?, ?> colLoyaltyPoint;

    @FXML
    private TableColumn<?, ?> colNICNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableView<Guest> tblGuests;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGuestId;

    @FXML
    private TextField txtGuestRequest;

    @FXML
    private TextField txtLoyaltyPoint;

    @FXML
    private TextField txtNICNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSerchByPhoneNumber;

    GuestService service = ServiceFactory.getInstance().getServiceType(ServiceType.GUEST);

    @FXML
    void btnAddNewGuestOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/guest/add_new_guest_form.fxml"))));
        stage.show();

    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
       Guest guest= service.searchGuest(txtSerchByPhoneNumber.getText());
        if (guest != null) {
            setTextToValues(guest);
        } else {
            new Alert(Alert.AlertType.WARNING, "Guest Not Found!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGuestRequest.setCellValueFactory(new PropertyValueFactory<>("guestRequest"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colLoyaltyPoint.setCellValueFactory(new PropertyValueFactory<>("loyalatyPoint"));


        colAction.setCellFactory(param -> new TableCell() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {

                    Guest guest = new Guest(
                            Integer.parseInt(txtGuestId.getText()),
                            txtName.getText(),
                            txtNICNumber.getText(),
                            txtAddress.getText(),
                            txtGuestRequest.getText(),
                            txtEmail.getText(),
                            txtPhoneNumber.getText()
                    );
                    if (service.updateGuest(guest)){
                        new Alert(Alert.AlertType.INFORMATION,"Guest Updated!!").show();
                        loadTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Guest Not Updated!!").show();

                    }


                    Guest guest1 = (Guest) getTableView().getItems().get(getIndex());
                    setTextToValues(guest);

                });

                // Delete Button Action (Remove customer from list)
                btnDelete.setOnAction(event -> {
                    Guest guest = (Guest) getTableView().getItems().get(getIndex());
                    boolean isDeleted = service.deleteGuest(String.valueOf(guest.getId()));
                    System.out.println("Delete before");
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Guest Deleted!").show();
                        loadTable(); // Refresh table after deletion
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Guest Room!").show();
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

        tblGuests.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Guest) newValue);
            }
        });
    }
    private void setTextToValues(Guest newValue) {
        txtGuestId.setText(String.valueOf(newValue.getId()));
        txtName.setText(newValue.getName());
        txtNICNumber.setText(newValue.getNicNumber());
        txtAddress.setText(newValue.getAddress());
        txtGuestRequest.setText(newValue.getGuestRequest());
        txtEmail.setText(newValue.getEmail());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
        txtLoyaltyPoint.setText(String.valueOf(newValue.getLoyalatyPoint()));

    }
    private void loadTable(){
        ObservableList<Guest> all = service.getAll();
        tblGuests.setItems(all);
    }
}
