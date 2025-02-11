package edu.icet.repository.custom;

import edu.icet.entity.BookingEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface BookingDao extends CrudDao<BookingEntity,String> {
    List<String> getIds();
}
