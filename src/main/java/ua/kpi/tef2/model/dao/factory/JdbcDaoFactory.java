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

    private final static String propertiesFile = "Connection.properties";

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    private Connection getConnection() {
//        Properties prop = loadConnectionProperties();
//        String url = prop.get("connectionUrl").toString();

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/rfdb", "root", "root");
//            return DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new RuntimeException("Could not establish connection to db");
        }
    }

    public Properties loadConnectionProperties() {
        Properties prop = new Properties();

        try (InputStream in = new FileInputStream("Connection.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
