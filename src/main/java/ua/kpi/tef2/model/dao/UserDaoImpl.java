package ua.kpi.tef2.model.dao;

import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final String USER_ID_FIELD_NAME = "id";
    private final String USER_LOGIN_FIELD_NAME = "login";
    private final String USER_PASSWORD_FIELD_NAME = "password";
    private final String USER_ROLE_FIELD_NAME = "role";
    private final String USER_NAME_FIELD = "name";

    private final String INSERT_USER_QUERY = "INSERT INTO users (login, password, role, name) VALUES (?, ?, ?, ?)";
    private final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE login = ?";

    private final String SELECT_ALL_USERS = "SELECT * FROM users";
    private final String UPDATE_USER = "UPDATE users SET login = ?, password = ?, name = ? role = ? WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User entity) throws UserAlreadyExistsException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY);

            statement.setString(1, entity.getLogin());
            statement.setString(2 ,entity.getPassword());
            statement.setString(3, entity.getRole());
            statement.setString(4, entity.getName());

            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new UserAlreadyExistsException(entity.getLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findOneById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return compileUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Optional<User> compileUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt(USER_ID_FIELD_NAME));
            user.setLogin(rs.getString(USER_LOGIN_FIELD_NAME));
            user.setPassword(rs.getString(USER_PASSWORD_FIELD_NAME));
            user.setName(rs.getString(USER_NAME_FIELD));
            user.setRole(rs.getString(USER_ROLE_FIELD_NAME));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = compileUser(rs).get();
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getRole());
            statement.setInt(5, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return compileUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

}
