package edu.icet.service.custom.impl;

import edu.icet.dto.Booking;
import edu.icet.dto.Room;
import edu.icet.entity.BookingEntity;
import edu.icet.entity.RoomEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.BookingDao;
import edu.icet.repository.custom.EmployeeDao;
import edu.icet.service.custom.BookingService;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class BookingServiceImpl implements BookingService {


    private static BookingServiceImpl instance;

    private BookingServiceImpl() {}

    public static BookingServiceImpl getInstance() {
        return instance == null ? instance = new BookingServiceImpl() : instance;
    }


    private final ModelMapper modelMapper = new ModelMapper();
    BookingDao bookingDao = DaoFactory.getInstance().getDaoType(DaoType.BOOKING);

    @Override
    public ObservableList<Booking> getAll() {
        List<BookingEntity> bookingEntityDetails = bookingDao.getAll();

        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        for (BookingEntity entity : bookingEntityDetails) {
            Booking booking = modelMapper.map(entity, Booking.class);
            bookings.add(booking);
        }
        return bookings;
    }

    @Override
    public boolean addBooking(Booking booking) {
        BookingEntity entity = new ModelMapper().map(booking,BookingEntity.class);
        return bookingDao.save(entity);
    }

    @Override
    public boolean updateBooking(Booking booking) {
        BookingEntity entity = modelMapper.map(booking,BookingEntity.class);
        return bookingDao.update(entity);
    }

    @Override
    public boolean deleteBooking(String bookingId) {
        return bookingDao.delete(bookingId);
    }

    @Override
    public Booking searchBooking(String bookingId) {
        BookingEntity bookingEntity = bookingDao.search(bookingId);

        if (bookingEntity != null) {
            return modelMapper.map(bookingEntity,Booking.class);
        }
        return null;
    }
}
