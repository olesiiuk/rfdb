package ua.kpi.tef2.model.service;

import ua.kpi.tef2.model.entity.User;

public interface PriceService {

    int getPrice(int distance, String carType, User user);

}
