package edu.icet.controller.user;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.User;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.RoomService;
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

    public TextField txtSerchById;
    public TableView tblUsers;
    public TableColumn colNo;
    public TableColumn colPosition;
    public TableColumn colName;
    public TableColumn colNICNumber;
    public TableColumn colEmail;
    public TableColumn colUserName;
    public TableColumn colPhoneNumber;
    public TableColumn colAction;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public TextField txtPosition;
    public TextField txtName;
    public TextField txtNICNumber;
    public TextField txtPhoneNumber;
    public TextField txtUserName;
    public TextField txtEmail;


    UserService service = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void btnAddNewUserOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/user/add_new_user_form.fxml"))));
        stage.show();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        User user = service.searchUser(txtSerchById.getText());
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
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("cmbPosition"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumbe"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));



        colAction.setCellFactory(param -> new TableCell<>() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {


                    Stage stage = new Stage();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/user/update_user_form.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.show();

                    User user = new User(
                            txtSerchById.getText(),
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


    public void btnDeleteOnAction(ActionEvent actionEvent) {


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
                            User user = new User(
                            txtSerchById.getText(),
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
    }
}
