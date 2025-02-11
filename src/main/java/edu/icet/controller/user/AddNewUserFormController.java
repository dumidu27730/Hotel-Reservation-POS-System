package edu.icet.controller.user;

import edu.icet.db.DBConnection;
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
import org.jasypt.util.text.BasicTextEncryptor;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        if (txtPassword.getText().equals(txtConfirmPassword.getText())){
            String Key="1234";

            BasicTextEncryptor basicTextEncryptor= new BasicTextEncryptor();
            basicTextEncryptor.setPassword(Key);

            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE username=" + "'" + txtUsername.getText() + "'");
            System.out.println(resultSet);
            if (!resultSet.next()){
                User user = new User(
                        cmbPosition.getValue().toString(),
                        txtName.getText(),
                        txtNICNumber.getText(),
                        txtEmail.getText(),
                        txtUsername.getText(),
                        txtPhoneNumber.getText(),
                        basicTextEncryptor.encrypt(txtPassword.getText())
                );

                if (service.addUser(user)){
                    new Alert(Alert.AlertType.INFORMATION,"User Added !!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"User Not Added !!").show();
                }

            }else{
                System.out.println(true);
            }

        }else {
            System.out.println(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> position = FXCollections.observableArrayList();
        position.add("Admin");
        position.add("Receptionist");
        cmbPosition.setItems(position);
    }

    public void btncancelOnAction(ActionEvent actionEvent) {

    }
}
