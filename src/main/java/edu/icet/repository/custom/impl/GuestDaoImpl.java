package edu.icet.repository.custom.impl;

import edu.icet.entity.GuestEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.custom.GuestDao;
import edu.icet.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GuestDaoImpl implements GuestDao {
    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(GuestEntity entity) {
        try {
            String SQL = "INSERT INTO guests(name,nic_number,address,guest_request,email,phone_number) VALUES (?,?,?,?,?,?)";
            return CrudUtil.execute(SQL,
                    entity.getName(),
                    entity.getNicNumber(),
                    entity.getAddress(),
                    entity.getGuestRequest(),
                    entity.getEmail(),
                    entity.getPhoneNumber()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GuestEntity search(String s) {
        String SQL = "SELECT * FROM guests WHERE phone_number = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, s);
            if (resultSet.next()) {
                return new GuestEntity(
                        resultSet.getString("guest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic_number"),
                        resultSet.getString("address"),
                        resultSet.getString("guest_request"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(GuestEntity entity) {
        return false;
    }

    @Override
    public List<GuestEntity> getAll() {
        return List.of();
    }
}
