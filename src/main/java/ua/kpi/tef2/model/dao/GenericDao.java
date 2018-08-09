package ua.kpi.tef2.model.dao;

import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    void save(T entity) throws UserAlreadyExistsException;

    Optional<T> findOneById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);


}
