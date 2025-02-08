package edu.icet.repository.custom.impl;

import edu.icet.entity.RoomEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.custom.RoomDao;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(RoomEntity entity) {
        try {
            String SQL = "INSERT INTO rooms(room_number,room_type,description,price_per_night,availability_status) VALUES (?,?,?,?,?)";
            System.out.println("entity price"+entity.getPricePerNight());
            return CrudUtil.execute(SQL,
                    entity.getRoomNumber(),
                    entity.getRoomType(),
                    entity.getDescription(),
                    entity.getPricePerNight(),
                    entity.getAvailableStatus()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RoomEntity search(String s) {
        String SQL = "SELECT * FROM rooms WHERE id=? || name=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, s,s);

            if(resultSet.next()) {
                return new RoomEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getFloat(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE FROM rooms WHERE room_number = ?";
        try {
            return CrudUtil.execute(SQL,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(RoomEntity entity) {
        String SQL = "UPDATE rooms SET  room_type=? , description=?,price_per_night =? ,availability_status= ? WHERE room_number=? ";
        try {
            return CrudUtil.execute(SQL,
                    entity.getRoomType(),
                    entity.getDescription(),
                    entity.getPricePerNight(),
                    entity.getAvailableStatus(),
                    entity.getRoomNumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RoomEntity> getAll() {
        List<RoomEntity> roomList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM rooms");
            while (resultSet.next()) {
                roomList.add(new RoomEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getFloat(4),
                        resultSet.getString(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }
}
