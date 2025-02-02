package edu.icet.service;

import edu.icet.repository.SuperDao;
import edu.icet.repository.custom.impl.UserDaoImpl;
import edu.icet.service.custom.impl.GuestServiceImpl;
import edu.icet.service.custom.impl.ReservationServiceImpl;
import edu.icet.service.custom.impl.RoomServiceImpl;
import edu.icet.service.custom.impl.UserServiceImpl;
import edu.icet.util.ServiceType;

public class ServiceFactory {

    private static ServiceFactory instance;
    private ServiceFactory(){}
    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }
    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case USER:return (T) UserServiceImpl.getInstance();
            case GUEST:return (T) GuestServiceImpl.getInstance();
            case ROOM:return (T) RoomServiceImpl.getInstance();
            case RESERVATION: return (T) ReservationServiceImpl.getInstance();

        }
        return null;
    }

}
