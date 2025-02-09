package edu.icet.controller.user;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.UserService;
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

public class UserFormController implements Initializable {

    public TextField txtSearchById;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNICNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNICNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtUserName;

    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void btnAddNewUserOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/user/add_new_user_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        User user = service.searchUser(txtSearchById.getText());
        if (user != null) {
            setTextToValues(user);
        } else {
            new Alert(Alert.AlertType.WARNING, "User Not Found!").show();
        }
    }
    private void loadTable(){
        ObservableList<User> all = service.getAll();
        tblUsers.setItems(all);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("cmbPosition"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));



        colAction.setCellFactory(param -> new TableCell() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {


                    User user = new User(
                            txtId.getText(),
                            txtName.getText(),
                            txtNICNumber.getText(),
                            txtEmail.getText(),
                            txtUserName.getText(),
                            txtPhoneNumber.getText(),
                            null

                    );
                    if (service.updateUser(user)){
                        new Alert(Alert.AlertType.INFORMATION,"User Updated!!").show();
                        loadTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"User Not Updated!!").show();

                    }


                    User user1 = (User) getTableView().getItems().get(getIndex());
                    setTextToValues(user);

                });

                // Delete Button Action (Remove customer from list)
                btnDelete.setOnAction(event -> {
                    User user = (User) getTableView().getItems().get(getIndex());
                    boolean isDeleted = service.deleteUser(String.valueOf(user.getId()));

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                        loadTable(); // Refresh table after deletion
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Delete Customer!").show();
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

        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((User) newValue);
            }
        });
    }
    private void setTextToValues(User newValue) {

        txtPosition.setText(newValue.getCmbPosition());
        txtName.setText(newValue.getName());
        txtNICNumber.setText(newValue.getNicNumber());
        txtEmail.setText(newValue.getEmail());
        txtUserName.setText(newValue.getUsername());
        txtPhoneNumber.setText(newValue.getPhoneNumber());

    }

}
