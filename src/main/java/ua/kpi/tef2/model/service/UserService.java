package ua.kpi.tef2.model.service;

import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;

import java.util.Optional;

public interface UserService {

    //TODO in implementation try to get one by email rom db then save or throw exception
    Optional<User> saveNewUser(User user) throws UserAlreadyExistsException;

}
