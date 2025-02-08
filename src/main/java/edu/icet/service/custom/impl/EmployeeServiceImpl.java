package edu.icet.service.custom.impl;

import edu.icet.dto.Employee;
import edu.icet.dto.Room;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.RoomEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.EmployeeDao;
import edu.icet.repository.custom.GuestDao;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl() {}

    public static EmployeeServiceImpl getInstance() {
        return instance == null ? instance = new EmployeeServiceImpl() : instance;
    }


    private final ModelMapper modelMapper = new ModelMapper();
    EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);


    @Override
    public ObservableList<Employee> getAll() {
        List<EmployeeEntity> emplpoyeeEntityDetails = employeeDao.getAll();

        ObservableList<Employee> employees = FXCollections.observableArrayList();
        for (EmployeeEntity entity : emplpoyeeEntityDetails) {
            Employee employee = modelMapper.map(entity, Employee.class);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.save(entity);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = modelMapper.map(employee, EmployeeEntity.class);
        return employeeDao.update(entity);
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        return employeeDao.delete(employeeId);
    }

    @Override
    public Employee searchEmployee(String employeeId) {
        EmployeeEntity employeeEntity = employeeDao.search(employeeId);

        if (employeeEntity != null) {
            return modelMapper.map(employeeEntity, Employee.class);
        }
        return null;
    }

    public ObservableList<String> getEmployeeIds(){
        ObservableList<String> employeeIdsList = FXCollections.observableArrayList();
        getAll().forEach(Employee ->
                employeeIdsList.add(String.valueOf(Employee.getEmployeeId())) );

        return employeeIdsList;
    }
}
