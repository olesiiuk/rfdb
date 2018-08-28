package ua.kpi.tef2.model.service.impl;

import ua.kpi.tef2.model.dao.CarDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;
import ua.kpi.tef2.model.entity.Car;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;
import ua.kpi.tef2.model.service.CarService;

import java.util.List;

//TODO write code here
public class CarServiceImpl implements CarService {

    private CarDao carDao;

    public CarServiceImpl() {
        this.carDao = DaoFactory.getInstance().createCarDao();
    }

    @Override
    public List<Car> findAllAvailableCars() {
        return carDao.findAllAvailable();
    }

    @Override
    public void setBusy(Car car) {
        carDao.setAvailable(car.getId(), false);
    }

    @Override
    public void setAvailable(Car car) {
        carDao.setAvailable(car.getId(), true);
    }

    @Override
    public void addCar(Car car) {
        try {
            carDao.save(car);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCar(Car car) {
        carDao.delete(car);
    }

    @Override
    public int timeToArrive(Car car) {
        return (int)(Math.random() * 13) + 2;
    }

    @Override
    public boolean hasAvailableCars() {
        return carDao.hasAvailableCars();
    }
}
