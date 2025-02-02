package edu.icet.repository.custom;

import edu.icet.entity.GuestEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface GuestDao extends CrudDao<GuestEntity,String> {
    List<String> getIds();
}
