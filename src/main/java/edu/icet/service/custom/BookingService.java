package edu.icet.service.custom;

import edu.icet.dto.Booking;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface BookingService extends SuperService{
    ObservableList<Booking> getAll();

    boolean addBooking(Booking booking);

    boolean updateBooking(Booking booking);

    boolean deleteBooking(String bookingId);

    Booking searchBooking(String bookingId);
}
