package edu.icet.service.custom;

import edu.icet.dto.Reservation;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface ReservationService extends SuperService {
    ObservableList<Reservation> getAll();

    boolean addReservation(Reservation reservation);

    boolean updateReservation(Reservation reservation);

    boolean deleteReservation(Reservation reservationId);

    Reservation searchReservation(Reservation reservationId);
}
