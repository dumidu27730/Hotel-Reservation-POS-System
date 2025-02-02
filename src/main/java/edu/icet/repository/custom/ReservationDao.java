package edu.icet.repository.custom;

import edu.icet.entity.ReservationEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface ReservationDao extends CrudDao<ReservationEntity,String> {
    List<String> getIds();
}
