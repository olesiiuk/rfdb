package ua.kpi.tef2.model.dao.impl;

import ua.kpi.tef2.model.dao.CarDao;
import ua.kpi.tef2.model.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private final String ID_FIELD_NAME = "id";
    private final String MODEL_FIELD_NAME = "model";
    private final String BRAND_FIELD_NAME = "brand";
    private final String TYPE_FIELD_NAME = "type";
    private final String IS_AVAILABLE_FIELD_NAME = "is_available";
    private final String TOTAL_COUNT = "total";

    private final String SELECT_ONE_BY_ID = "SELECT * FROM car WHERE id = ?";
    private final String SELECT_ALL_AVAILABLE_CARS = "SELECT * FROM car WHERE is_available = TRUE";
    private final String SELECT_ALL_CARS = "SELECT * FROM car";
    private final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private final String SAVE_CAR = "INSERT INTO car (model, brand, type, is_available) VALUES (?, ?, ?,?)";
    private final String UPDATE_CAR = "UPDATE car SET model = ?, brand = ?, type = ?, is_available = ? WHERE id = ?";
    private final String SET_BUSY = "UPDATE car SET is_available = ? WHERE id = ?";
    private final String SELECT_COUNT = "SELECT COUNT(*) as total FROM car WHERE is_available = true";

    private Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Car> findOneById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ONE_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return compileCar(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Optional<Car> compileCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getInt(ID_FIELD_NAME));
        car.setModel(resultSet.getString(MODEL_FIELD_NAME));
        car.setBrand(resultSet.getString(BRAND_FIELD_NAME));
        car.setType(resultSet.getString(TYPE_FIELD_NAME));
        car.setAvailable(resultSet.getBoolean(IS_AVAILABLE_FIELD_NAME));

        return Optional.of(car);
    }

    @Override
    public List<Car> findAllAvailable() {
        return collectFromQuery(SELECT_ALL_AVAILABLE_CARS);
    }

    @Override
    public void setAvailable(int id, boolean available) {
        try {
            PreparedStatement statement = connection.prepareStatement(SET_BUSY);
            statement.setBoolean(1, available);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasAvailableCars() {
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_COUNT);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(TOTAL_COUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public List<Car> findAll() {
        return collectFromQuery(SELECT_ALL_CARS);
    }

    private List<Car> collectFromQuery(String query) {
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Optional<Car> car = compileCar(rs);
                car.ifPresent(cars::add);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public void save(Car entity) {
        executeUpdateQuery(SAVE_CAR, entity);
    }

    @Override
    public void update(Car entity) {
        executeUpdateQuery(UPDATE_CAR, entity);
    }

    private void executeUpdateQuery(String query, Car entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getModel());
            statement.setString(2, entity.getBrand());
            statement.setString(3, entity.getType());
            statement.setBoolean(4, entity.isAvailable());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Car entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_BY_ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
