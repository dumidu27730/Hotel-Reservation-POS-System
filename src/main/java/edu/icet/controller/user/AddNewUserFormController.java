package edu.icet.controller.user;

import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
import edu.icet.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewUserFormController implements Initializable {

    public TextField txtNICNumber;
    @FXML
    private ComboBox<String> cmbPosition;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtUsername;

    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void btnSaveOnAction(ActionEvent event) {
       User user = new User(
               cmbPosition.getValue().toString(),
               txtName.getText(),
               txtNICNumber.getText(),
               txtEmail.getText(),
               txtPhoneNumber.getText(),
               txtUsername.getText(),
               txtPassword.getText()
       );

        if(service.addUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"User Add Success").show();
           }else{
            new Alert(Alert.AlertType.ERROR,"User Not Add").show();
        }
    }

    @FXML
    void btncancelOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> position = FXCollections.observableArrayList();
        position.add("Admin");
        position.add("Receptionist");
        cmbPosition.setItems(position);
    }
}
