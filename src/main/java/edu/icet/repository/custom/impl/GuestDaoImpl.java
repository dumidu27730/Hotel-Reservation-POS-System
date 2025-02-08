package edu.icet.repository.custom.impl;

import edu.icet.entity.GuestEntity;
import edu.icet.entity.RoomEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.custom.GuestDao;
import edu.icet.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                        resultSet.getInt("guest_id"),
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
        String SQL = "DELETE FROM rooms WHERE guest_id = ?";
        try {
            return CrudUtil.execute(SQL,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(GuestEntity entity) {
        String SQL = "UPDATE guests SET  name=? , nic_number=?, address =? , guest_request= ?,email=?,phone_number=? WHERE guest_id=? ";
        try {
            return CrudUtil.execute(SQL,
                    entity.getName(),
                    entity.getNicNumber(),
                    entity.getAddress(),
                    entity.getGuestRequest(),
                    entity.getEmail(),
                    entity.getPhoneNumber(),
                    entity.getId()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GuestEntity> getAll() {
        List<GuestEntity> guetsList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM guests");
            while (resultSet.next()) {
                guetsList.add(new GuestEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getFloat(8)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guetsList;
    }
}
