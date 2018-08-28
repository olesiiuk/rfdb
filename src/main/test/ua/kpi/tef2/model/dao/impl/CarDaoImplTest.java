package ua.kpi.tef2.model.dao.impl;

import org.junit.Ignore;
import org.junit.Test;
import ua.kpi.tef2.model.dao.CarDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;
import ua.kpi.tef2.model.entity.Car;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;

import java.util.List;

import static org.junit.Assert.*;

public class CarDaoImplTest {

    CarDao carDao = DaoFactory.getInstance().createCarDao();

    @Ignore
    @Test
    public void testHasAvailable() {
        assertTrue(carDao.hasAvailableCars());
    }

    @Ignore
    @Test
    public void findAllAvailable() {
        List<Car> cars = carDao.findAllAvailable();
        cars.forEach(System.out::println);
    }

    @Ignore
    @Test
    public void save() throws UserAlreadyExistsException {
        Car car = new Car("s", "tesla", "standard", false);
        carDao.save(car);
    }

    @Ignore
    @Test
    public void testSetAvailable() {
        carDao.setAvailable(1, true);
    }

    @Ignore
    @Test
    public void setSetBusy() {
        carDao.setAvailable(1, false);
    }

}