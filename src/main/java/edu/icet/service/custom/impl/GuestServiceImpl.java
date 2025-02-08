package edu.icet.service.custom.impl;

import edu.icet.dto.Guest;
import edu.icet.dto.Room;
import edu.icet.entity.GuestEntity;
import edu.icet.entity.RoomEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.GuestDao;
import edu.icet.repository.custom.UserDao;
import edu.icet.service.custom.GuestService;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class GuestServiceImpl implements GuestService {

    private static GuestServiceImpl instance;

    private GuestServiceImpl() {}

    public static GuestServiceImpl getInstance() {
        return instance == null ? instance = new GuestServiceImpl() : instance;
    }


    private final ModelMapper modelMapper = new ModelMapper();
    GuestDao guestDao = DaoFactory.getInstance().getDaoType(DaoType.GUEST);



    @Override
    public ObservableList<Guest> getAll() {
        List<GuestEntity> guestEntityDetails = guestDao.getAll();

        ObservableList<Guest> guests = FXCollections.observableArrayList();
        for (GuestEntity entity : guestEntityDetails) {
            Guest guest = modelMapper.map(entity, Guest.class);
            guests.add(guest);
        }
        return guests;
    }

    @Override
    public boolean addGuest(Guest guest) {
        GuestEntity entity = new ModelMapper().map(guest, GuestEntity.class);
        return guestDao.save(entity);
    }

    @Override
    public boolean updateGuest(Guest guest) {
        GuestEntity entity = modelMapper.map(guest, GuestEntity.class);
        return guestDao.update(entity);
    }

    @Override
    public boolean deleteGuest(String guestId) {
        return guestDao.delete(guestId);
    }

    @Override
    public Guest searchGuest(String guestId) {
        GuestEntity guestEntity = guestDao.search(guestId);

        if (guestEntity != null) {
            return modelMapper.map(guestEntity, Guest.class);
        }
        return null;
    }
    public ObservableList<String> getguestIds(){
        ObservableList<String> guestIdsList = FXCollections.observableArrayList();
        getAll().forEach(Guest ->
                guestIdsList.add(Guest.getPhoneNumber()) );

        return guestIdsList;
    }
}
