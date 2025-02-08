package edu.icet.repository.custom;

import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.GuestEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface EmployeeDao extends CrudDao<EmployeeEntity,String> {
    List<String> getIds();
}
