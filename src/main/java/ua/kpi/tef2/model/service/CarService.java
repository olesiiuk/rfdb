package ua.kpi.tef2.model.service;

import ua.kpi.tef2.model.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAllAvailableCars();

    void setBusy(Car car);

    void setAvailable(Car car);

    void addCar(Car car);

    void removeCar(Car car);

}
