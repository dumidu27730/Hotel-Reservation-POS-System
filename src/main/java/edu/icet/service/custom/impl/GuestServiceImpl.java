package edu.icet.service.custom.impl;

import edu.icet.dto.Guest;
import edu.icet.entity.GuestEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.GuestDao;
import edu.icet.repository.custom.UserDao;
import edu.icet.service.custom.GuestService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class GuestServiceImpl implements GuestService {

    private static GuestServiceImpl instance;

    private GuestServiceImpl() {}

    public static GuestServiceImpl getInstance() {
        return instance == null ? instance = new GuestServiceImpl() : instance;
    }

    GuestDao guestDao = DaoFactory.getInstance().getDaoType(DaoType.GUEST);

    @Override
    public ObservableList<Guest> getAll() {
        return null;
    }

    @Override
    public boolean addGuest(Guest guest) {
        GuestEntity entity = new ModelMapper().map(guest, GuestEntity.class);
        return guestDao.save(entity);
    }

    @Override
    public boolean updateGuest(Guest guest) {
        return false;
    }

    @Override
    public boolean deleteGuest(String guestId) {
        return false;
    }

    @Override
    public Guest searchGuest(String guestId) {
        GuestEntity entity = guestDao.search(guestId);
        return new ModelMapper().map(entity, Guest.class);
    }
}
