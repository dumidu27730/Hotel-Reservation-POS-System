package edu.icet.controller.employee;

import edu.icet.dto.Employee;
import edu.icet.dto.Guest;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddNewEmployeeFormController {

    @FXML
    private TextField txtAddress;

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

    EmployeeService service = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);


    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

       Employee employee = new Employee(
                txtJobRole.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtNICNumber.getText(),
                txtPhoneNumber.getText(),
                Float.parseFloat(txtSalary.getText())
        );

        if(service.addEmployee(employee)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Add Success").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Employee Not Add").show();
        }
    }
}
