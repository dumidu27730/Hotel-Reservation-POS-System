package edu.icet.repository.custom.impl;

import edu.icet.entity.UserEntity;
import edu.icet.repository.custom.UserDao;
import edu.icet.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(UserEntity entity) {
        try {
            String SQL = "INSERT INTO users(position,name,nic_number,email,username,phone_number,password) VALUES (?,?,?,?,?,?,?)";
            return CrudUtil.execute(SQL,
            entity.getCmbPosition(),
            entity.getName(),
            entity.getNicNumber(),
            entity.getEmail(),
            entity.getUsername(),
            entity.getPhoneNumber(),
            entity.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public List<UserEntity> getAll() {
        return List.of();
    }
}
