package ua.kpi.tef2.model.service.impl;

import ua.kpi.tef2.model.dao.UserDao;
import ua.kpi.tef2.model.dao.factory.DaoFactory;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.LoginAndPasswordException;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;
import ua.kpi.tef2.model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final String ROLE_USER = "USER";

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().createUserDao();
    }

    @Override
    public void saveNewUser(User user) throws UserAlreadyExistsException {
        user.setRole(ROLE_USER);
        userDao.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findOneByEmail(email);
    }

    @Override
    public Optional<User> CheckUserLoginAndPassword(String email, String password) throws LoginAndPasswordException {
        Optional<User> userOptional = findUserByEmail(email);
        String userPassword = userOptional.orElseThrow(LoginAndPasswordException::new).getPassword();
        if (userPassword.equals(password)) {
            return userOptional;
        }
        throw new LoginAndPasswordException();
    }

}
