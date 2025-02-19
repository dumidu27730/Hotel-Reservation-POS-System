package edu.icet.util;

import com.google.inject.AbstractModule;
import edu.icet.repository.custom.*;
import edu.icet.repository.custom.impl.*;
import edu.icet.service.custom.*;
import edu.icet.service.custom.impl.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(BookingService.class).to(BookingServiceImpl.class);
        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
        bind(GuestService.class).to(GuestServiceImpl.class);
        bind(ReservationService.class).to(ReservationServiceImpl.class);
        bind(RoomService.class).to(RoomServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);

        bind(BookingDao.class).to(BookingDaoImpl.class);
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
        bind(GuestDao.class).to(GuestDaoImpl.class);
        bind(ReservationDao.class).to(ReservationDaoImpl.class);
        bind(RoomDao.class).to(RoomDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
    }
}
