package edu.icet.repository;

import edu.icet.repository.custom.impl.GuestDaoImpl;
import edu.icet.repository.custom.impl.ReservationDaoImpl;
import edu.icet.repository.custom.impl.RoomDaoImpl;
import edu.icet.repository.custom.impl.UserDaoImpl;
import edu.icet.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }
    public <T extends SuperDao>T getDaoType(DaoType daoType){
        switch (daoType){
            case USER: return (T) new UserDaoImpl();
            case GUEST: return (T) new GuestDaoImpl();
            case ROOM: return (T) new RoomDaoImpl();
            case RESERVATION: return (T) new ReservationDaoImpl();
        }
        return null;
    }
}
