package ua.kpi.tef2.model.service.impl;

import ua.kpi.tef2.model.dao.AddressDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;
import ua.kpi.tef2.model.entity.Address;
import ua.kpi.tef2.model.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;

    public AddressServiceImpl() {
        this.addressDao = DaoFactory.getInstance().createAddressDao();
    }

    @Override
    public List<Address> getAll() {
        return addressDao.findAll();
    }

    @Override
    public int getDistance(int idFrom, int idTo) {
        return addressDao.findDistance(idFrom, idTo);
    }
}
