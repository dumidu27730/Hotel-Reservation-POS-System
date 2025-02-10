package edu.icet.repository.custom.impl;

import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.custom.EmployeeDao;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(EmployeeEntity entity) {
        try {
            String SQL = "INSERT INTO employees(employee_id,job_role,name,nic_number,address,phone_number,salary) VALUES (?,?,?,?,?,?,?)";
            return CrudUtil.execute(SQL,
                    entity.getEmployeeId(),
                    entity.getJobRole(),
                    entity.getName(),
                    entity.getNicNumber(),
                    entity.getAddress(),
                    entity.getPhoneNumber(),
                    entity.getSalary()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeEntity search(String s) {
        String SQL = "SELECT * FROM employees WHERE nic_number=? || phone_number=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, s,s);

            if(resultSet.next()) {
                return new EmployeeEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getFloat(7)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE FROM employees WHERE employee_id = ?";
        try {
            return CrudUtil.execute(SQL,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        String SQL = "UPDATE employees SET  job_role=? , name=?,address =? ,nic_number= ?,phone_number=?,salary=? WHERE employee_id=? ";
        try {
            return CrudUtil.execute(SQL,
                    entity.getJobRole(),
                    entity.getName(),
                    entity.getNicNumber(),
                    entity.getAddress(),
                    entity.getPhoneNumber(),
                    entity.getSalary(),
                    entity.getEmployeeId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        List<EmployeeEntity> employeeList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employees");
            while (resultSet.next()) {
                employeeList.add(new EmployeeEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getFloat(7)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}
