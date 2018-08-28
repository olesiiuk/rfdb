package ua.kpi.tef2.model.service.impl;

import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.service.PriceService;

public class StandardPriceService implements PriceService {

    private final int BASE_FEE = 30;
    private final int STANDARD_RATE = 3;
    private final int PREMIUM_RATE = 5;

    private final int SUM_FOR_MAX_DISCOUNT = 15000;
    private final int SUM_FOR_MEDIUM_DISCOUNT = 5000;
    private final int SUM_FOR_MIN_DISCOUNT = 1000;

    private final int MAX_DISCOUNT = 15;
    private final int MEDIUM_DISCOUNT = 10;
    private final int MIN_DISCOUNT = 5;

    @Override
    public int getPrice(int distance, String carType, User user) {
        int discountRate = getDiscountRate(user);
        int price = BASE_FEE + getRate(carType) * distance;
        int discount = (int)Math.round((double) price / 100 * discountRate);

        return price - discount;
    }

    //TODO make carType ENUM
    private int getRate(String carType) {
        return carType.equals("standard") ? STANDARD_RATE : PREMIUM_RATE;
    }

    public int getDiscountRate(User user) {

        int sum = user.getSum();
        if (sum >= SUM_FOR_MAX_DISCOUNT) {
            return MAX_DISCOUNT;
        } else if (sum >= SUM_FOR_MEDIUM_DISCOUNT) {
            return MEDIUM_DISCOUNT;
        } else if (sum >= SUM_FOR_MIN_DISCOUNT) {
            return MIN_DISCOUNT;
        }

        return 0;
    }
}
