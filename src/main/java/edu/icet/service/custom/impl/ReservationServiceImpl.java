package edu.icet.service.custom.impl;

import edu.icet.dto.Reservation;
import edu.icet.entity.ReservationEntity;
import edu.icet.repository.DaoFactory;
import edu.icet.repository.custom.ReservationDao;
import edu.icet.service.custom.ReservationService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class ReservationServiceImpl implements ReservationService {

    private static ReservationServiceImpl instance;

    private ReservationServiceImpl() {}

    public static ReservationServiceImpl getInstance() {
        return instance == null ? instance = new ReservationServiceImpl() : instance;
    }

    ReservationDao reservationDao = DaoFactory.getInstance().getDaoType(DaoType.RESERVATION);

    @Override
    public ObservableList<Reservation> getAll() {
        return null;
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        ReservationEntity entity = new ModelMapper().map(reservation, ReservationEntity.class);
        return reservationDao.save(entity);
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation reservationId) {
        return false;
    }

    @Override
    public Reservation searchReservation(Reservation reservationId) {
        return null;
    }
}
