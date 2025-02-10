package edu.icet.service.custom;

import edu.icet.dto.Room;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface RoomService extends SuperService {

    ObservableList<Room> getAll();

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(String roomId);

    Room searchRoom(String roomId);

}
