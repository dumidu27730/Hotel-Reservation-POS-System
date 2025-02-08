package edu.icet.controller.employee;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.Employee;
import edu.icet.dto.Room;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.EmployeeService;
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

public class EmployeeFormController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colNICNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<Employee> tblEmployees;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtJobRole;

    @FXML
    private TextField txtNICNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSerchByNICNumber;

    EmployeeService service = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @FXML
    void btnAddNewEmployeeOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/employee/add_new_employee_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Employee employee = service.searchEmployee(txtSerchByNICNumber.getText());
        if (employee != null) {
            setTextToValues(employee);
        } else {
            new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
        }
    }
    private void setTextToValues(Employee newValue) {
        txtEmployeeId.setText(String.valueOf(newValue.getEmployeeId()));
        txtJobRole.setText(newValue.getJobRole());
        txtName.setText(newValue.getName());
        txtNICNumber.setText(newValue.getNicNumber());
        txtAddress.setText(newValue.getAddress());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }

    private void loadTable(){
        ObservableList<Employee> all = service.getAll();
        tblEmployees.setItems(all);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));



        colAction.setCellFactory(param -> new TableCell() {
            private final JFXButton btnUpdate = new JFXButton("Update");
            private final JFXButton btnDelete = new JFXButton("Delete");
            private final HBox actionButtons = new HBox(10, btnUpdate, btnDelete); // HBox with spacing

            {

                btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

                btnUpdate.setOnAction(event -> {


                    Employee employee = new Employee(
                            Integer.parseInt(txtEmployeeId.getText()),
                            txtJobRole.getText(),
                            txtName.getText(),
                            txtNICNumber.getText(),
                            txtAddress.getText(),
                            txtPhoneNumber.getText(),
                            Float.parseFloat(txtSalary.getText())
                    );
                    if (service.updateEmployee(employee)){
                        new Alert(Alert.AlertType.INFORMATION,"Employee Updated!!").show();
                        loadTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Employee Not Updated!!").show();

                    }

                    Employee employee1= (Employee) getTableView().getItems().get(getIndex());
                    setTextToValues(employee);

                });

                // Delete Button Action (Remove customer from list)
                btnDelete.setOnAction(event -> {
                    Employee employee = (Employee) getTableView().getItems().get(getIndex());
                    boolean isDeleted = service.deleteEmployee(String.valueOf(employee.getEmployeeId()));

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Employee Deleted!").show();
                        loadTable(); // Refresh table after deletion
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Employee Room!").show();
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

        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Employee) newValue);
            }
        });
    }
}
