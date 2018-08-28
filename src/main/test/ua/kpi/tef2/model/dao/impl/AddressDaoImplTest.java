package ua.kpi.tef2.model.dao.impl;

import org.junit.Test;
import ua.kpi.tef2.model.dao.AddressDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;

import static org.junit.Assert.*;

public class AddressDaoImplTest {

    @Test
    public void findDistance() {
        AddressDao addressDao = DaoFactory.getInstance().createAddressDao();

        int actual = addressDao.findDistance(1, 3);

        assertEquals(15, actual);
    }
}