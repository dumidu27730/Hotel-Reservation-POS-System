package edu.icet.repository;

import java.util.List;

public interface CrudDao <T,ID> extends SuperDao {

    boolean save(T entity);
    T search(ID id);
    boolean delete(ID id);
    boolean update(T entity);
    List<T> getAll();
}
