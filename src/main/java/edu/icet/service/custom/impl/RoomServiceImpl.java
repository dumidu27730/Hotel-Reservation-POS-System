package edu.icet.service.custom.impl;

import edu.icet.dto.Room;
import edu.icet.entity.RoomEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.RoomDao;
import edu.icet.repository.custom.impl.RoomDaoImpl;
import edu.icet.service.custom.RoomService;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private static RoomServiceImpl instance;

    private RoomServiceImpl() {}

    public static RoomServiceImpl getInstance() {
        return instance == null ? instance = new RoomServiceImpl() : instance;
    }

    RoomDao roomDao = DaoFactory.getInstance().getDaoType(DaoType.ROOM);

    @Override
    public ObservableList<Room> getAll() {
        List<Room> roomList = new RoomDaoImpl().getAll(); // Fetch data from DAO
        return FXCollections.observableArrayList(roomList); // Convert to ObservableList and return
    }


    @Override
    public boolean addRoom(Room room) {
        RoomEntity entity = new ModelMapper().map(room, RoomEntity.class);
        return roomDao.save(entity);
    }

    @Override
    public boolean updateRoom(Room room) {
        return false;
    }

    @Override
    public boolean deleteRoom(String roomId) {
        return false;
    }

    @Override
    public Room searchRoom(String roomId) {
        return null;
    }
}
