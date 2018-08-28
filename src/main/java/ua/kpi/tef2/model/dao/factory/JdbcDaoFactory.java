package ua.kpi.tef2.model.dao.factory;

import ua.kpi.tef2.model.dao.AddressDao;
import ua.kpi.tef2.model.dao.UserDao;
import ua.kpi.tef2.model.dao.impl.AddressDaoImpl;
import ua.kpi.tef2.model.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public AddressDao createAddressDao() {
        return new AddressDaoImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
