package ua.kpi.tef2.model.dao;

import ua.kpi.tef2.model.entity.Address;

import java.util.List;

public interface AddressDao extends AutoCloseable{

    List<Address> findAll();

    int findDistance(int idFrom, int idTo);

}
