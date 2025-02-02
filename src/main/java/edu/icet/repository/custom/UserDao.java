package edu.icet.repository.custom;

import edu.icet.entity.UserEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface UserDao  extends CrudDao<UserEntity,String> {
    List<String> getIds();
}
