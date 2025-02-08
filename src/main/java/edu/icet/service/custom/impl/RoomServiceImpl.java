package edu.icet.service.custom.impl;

import edu.icet.dto.Room;
import edu.icet.dto.User;
import edu.icet.entity.RoomEntity;
import edu.icet.entity.UserEntity;
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

    private final ModelMapper modelMapper = new ModelMapper();

    RoomDao roomDao = DaoFactory.getInstance().getDaoType(DaoType.ROOM);

    @Override
    public ObservableList<Room> getAll() {
        List<RoomEntity> roomEntityDetails = roomDao.getAll();

        ObservableList<Room> rooms = FXCollections.observableArrayList();
        for (RoomEntity entity : roomEntityDetails) {
            Room room = modelMapper.map(entity, Room.class);
            rooms.add(room);
        }
        return rooms;
    }


    @Override
    public boolean addRoom(Room room) {
        RoomEntity entity = new ModelMapper().map(room, RoomEntity.class);
        return roomDao.save(entity);
    }

    @Override
    public boolean updateRoom(Room room) {
        RoomEntity entity = modelMapper.map(room, RoomEntity.class);
        return roomDao.update(entity);
    }

    @Override
    public boolean deleteRoom(String roomId) {
        return roomDao.delete(roomId);
    }

    @Override
    public Room searchRoom(String roomId) {
        RoomEntity roomEntity = roomDao.search(roomId);

        if (roomEntity != null) {
            return modelMapper.map(roomEntity, Room.class);
        }
        return null;
    }
    public ObservableList<String> getRoomIds(){
        ObservableList<String> roomIdsList = FXCollections.observableArrayList();
        getAll().forEach(Room ->
                roomIdsList.add(Room.getRoomNumber()) );

        return roomIdsList;
    }

}
