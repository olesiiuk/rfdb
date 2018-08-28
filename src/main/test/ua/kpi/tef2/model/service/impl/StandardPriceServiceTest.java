package ua.kpi.tef2.model.service.impl;

import org.junit.Test;
import ua.kpi.tef2.model.entity.User;

import static org.junit.Assert.*;

public class StandardPriceServiceTest {

    private StandardPriceService priceService = new StandardPriceService();
    private User user = new User();

    @Test
    public void getMaxDiscount() {
        user.setSum(15000);
        int actual = priceService.getDiscountRate(user);
        assertEquals(15, actual);
    }

    @Test
    public void getMediumDiscount() {
        user.setSum(5000);
        int actual = priceService.getDiscountRate(user);
        assertEquals(10, actual);
    }

    @Test
    public void getMediumDiscountOF10000() {
        user.setSum(10000);
        int actual = priceService.getDiscountRate(user);
        assertEquals(10, actual);
    }

    @Test
    public void getMinDiscount() {
        user.setSum(1900);

        int actual = priceService.getDiscountRate(user);
        assertEquals(5, actual);
    }

    @Test
    public void getZeroDiscount() {
        user.setSum(900);

        int actual = priceService.getDiscountRate(user);
        assertEquals(0, actual);
    }

    @Test
    public void getPrice() {
        user.setSum(50);

        int actual = priceService.getPrice(100, "standard", user);
        assertEquals(330, actual);
    }

    @Test
    public void getPriceStandardCarMinDiscount() {
        user.setSum(1000);

        int actual = priceService.getPrice(100, "standard", user);
        assertEquals(313, actual);
    }

    @Test
    public void getPricePremiumCarMaxDiscount() {
        user.setSum(15000);

        int actual = priceService.getPrice(100, "premium", user);
        assertEquals(450, actual);
    }

}