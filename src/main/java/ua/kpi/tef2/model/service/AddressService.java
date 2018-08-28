package ua.kpi.tef2.model.service;

import ua.kpi.tef2.model.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAll();

    int getDistance(int idFrom, int idTo);
}
