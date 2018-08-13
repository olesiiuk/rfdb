package ua.kpi.tef2.controller.validators;

import org.junit.Test;
import ua.kpi.tef2.model.entity.User;

import static org.junit.Assert.*;

public class InputValidatorTest {

    @Test
    public void testValidateGoodEmail() {
        User user = new User("test@email.com.ua", "password", "name");
        assertFalse(InputValidator.isNotValidUserData(user, "password"));

        user.setLogin("test@email.com");
        user.setPassword("21341234^sd_s.");
        assertFalse(InputValidator.isNotValidUserData(user, "21341234^sd_s."));

        user.setLogin("te.s_t@email.com");
        user.setPassword("Ldsf@#dfsfds");
        assertFalse(InputValidator.isNotValidUserData(user, "Ldsf@#dfsfds"));
    }

    @Test
    public void testValidateBadEmail() {
        User user = new User("t/e.s_t@email.com", "asdfjklgh", "name");
        assertTrue(InputValidator.isNotValidUserData(user, "asdfjklgh"));

        user = new User("te.s_t@ema il.com", "askdjfhsk", "name");
        assertTrue(InputValidator.isNotValidUserData(user, "askdjfhsk"));

        user = new User("tes t@ema il.com", "sldakfhlaskd", "name");
        assertTrue(InputValidator.isNotValidUserData(user, "sldakfhlaskd"));

        user = new User("test@email.com", "sldakfhlaskd", "name");
        assertTrue(InputValidator.isNotValidUserData(user, "sldakfaskd"));
    }



}