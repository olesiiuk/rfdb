package ua.kpi.tef2.model.service.impl;

import org.junit.Test;
import ua.kpi.tef2.model.entity.Car;

import static org.junit.Assert.*;

public class CarServiceImplTest {

    @Test
    public void getRand() {
        System.out.println(new CarServiceImpl().timeToArrive(new Car()));
    }

}