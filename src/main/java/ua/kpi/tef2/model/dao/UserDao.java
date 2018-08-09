package ua.kpi.tef2.model.dao;

import ua.kpi.tef2.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findOneByEmail(String email);
}
