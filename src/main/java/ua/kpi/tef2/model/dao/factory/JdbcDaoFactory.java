package ua.kpi.tef2.model.dao.factory;

import ua.kpi.tef2.model.dao.UserDao;
import ua.kpi.tef2.model.dao.UserDaoImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
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
