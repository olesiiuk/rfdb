package ua.kpi.tef2.model.dao.impl;

import ua.kpi.tef2.model.dao.AddressDao;
import ua.kpi.tef2.model.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private final String SELECT_ALL = "SELECT * FROM address";
    private final String SELECT_DISTANCE = "SELECT distance FROM address_address WHERE a1_id = ? AND a2_id = ?";

    private final String ADDRESS_ID_FIELD_NAME = "id";
    private final String ADDRESS_STREET_FIELD_NAME = "street";
    private final String ADDRESS_NUMBER_FIELD_NAME = "number";
    private final String DISTANCE_FIELD_NAME = "distance";

    private Connection connection;

    public AddressDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Address> findAll() {

        List<Address> addressList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address address = compileAddress(rs);
                addressList.add(address);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addressList;
    }

    private Address compileAddress(ResultSet rs) {

        Address address = new Address();
        try {
            address.setId(rs.getInt(ADDRESS_ID_FIELD_NAME));
            address.setStreet(rs.getString(ADDRESS_STREET_FIELD_NAME));
            address.setNumber(rs.getInt(ADDRESS_NUMBER_FIELD_NAME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @Override
    public int findDistance(int idFrom, int idTo) {
        int distance = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_DISTANCE);
            statement.setInt(1, idFrom);
            statement.setInt(2, idTo);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(DISTANCE_FIELD_NAME);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return distance;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
