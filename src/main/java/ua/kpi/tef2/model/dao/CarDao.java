package ua.kpi.tef2.model.dao;

import ua.kpi.tef2.model.entity.Car;

import java.util.List;

public interface CarDao extends GenericDao<Car> {

    List<Car> findAllAvailable();

    void setAvailable(int id, boolean available);

    boolean hasAvailableCars();

}
