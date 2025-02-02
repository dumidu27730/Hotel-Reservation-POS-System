package edu.icet.repository.custom.impl;

import edu.icet.entity.ReservationEntity;
import edu.icet.repository.custom.ReservationDao;

import java.util.List;


public class ReservationDaoImpl implements ReservationDao {

    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(ReservationEntity entity) {
        return false;
    }

    @Override
    public ReservationEntity search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(ReservationEntity entity) {
        return false;
    }

    @Override
    public List<ReservationEntity> getAll() {
        return List.of();
    }
}
