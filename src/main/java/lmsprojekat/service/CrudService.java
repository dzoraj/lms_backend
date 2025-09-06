package lmsprojekat.service;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T dto);
    T findById(ID id);
    List<T> findAll();
    T update(ID id, T dto);
    void delete(ID id);
}
