package edu.icet.controller.guest;

import edu.icet.dto.Guest;
import edu.icet.dto.Room;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.GuestService;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNewGuestFormController {

    @FXML
    private TableView<Guest> tblGuests;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNICNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextArea txtRequest;

    GuestService service = ServiceFactory.getInstance().getServiceType(ServiceType.GUEST);


    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Guest guest = new Guest(
                txtName.getText(),
                txtNICNumber.getText(),
                txtAddress.getText(),
                txtRequest.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText()
        );

        if(service.addGuest(guest)){
            new Alert(Alert.AlertType.INFORMATION,"Guest Add Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Guest Not Add").show();
        }

    }
    private void loadTable(){
        if (service == null) {
            System.out.println("GuestService is null!");
            return;
        }
        if (tblGuests == null) {
            System.out.println("tblGuests is null!");
            return;
        }
        ObservableList<Guest> all = service.getAll();
        tblGuests.setItems(all);
    }

}
