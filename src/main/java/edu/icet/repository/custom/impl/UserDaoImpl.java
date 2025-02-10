package edu.icet.repository.custom.impl;

import edu.icet.entity.UserEntity;
import edu.icet.repository.custom.UserDao;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
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
        String SQL = "SELECT * FROM users WHERE nic_number=? || phone_number=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, s,s);

            if(resultSet.next()) {
                return new UserEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(7)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE FROM users WHERE user_id = ?";
        try {
            return CrudUtil.execute(SQL,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        String SQL = "UPDATE users SET  position=? , name=?,nic_number =? ,email = ?, username= ? ,phone_number=? WHERE user_id=? ";
        try {
            return CrudUtil.execute(SQL,
                    entity.getCmbPosition(),
                    entity.getName(),
                    entity.getNicNumber(),
                    entity.getEmail(),
                    entity.getUsername(),
                    entity.getPhoneNumber(),
                    entity.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserEntity> getAll() {
        ObservableList<UserEntity> userEntityObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(7)
                );
                userEntityObservableList.add(userEntity);
            }
            return userEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
