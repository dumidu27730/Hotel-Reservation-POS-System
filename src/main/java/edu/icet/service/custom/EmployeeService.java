package edu.icet.service.custom;

import edu.icet.dto.Employee;
import edu.icet.dto.Guest;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface EmployeeService extends SuperService {
    ObservableList<Employee> getAll();

    boolean addEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String employeeId);

    Employee searchEmployee(String employeeId);
}
