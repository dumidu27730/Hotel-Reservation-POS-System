package edu.icet.repository.custom;

import edu.icet.entity.RoomEntity;
import edu.icet.repository.CrudDao;

import java.util.List;

public interface RoomDao extends CrudDao<RoomEntity,String> {
    List<RoomEntity> getRoomType();
}
