package ua.kpi.tef2.controller.validators;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidatorTest {

    @Test
    public void testValidateGoodEmail() {
        assertTrue(InputValidator.isNotValidEmailAndPassword( "test@email.com.ua", "password", "password"));
        assertTrue(InputValidator.isNotValidEmailAndPassword( "test@email.com", "21341234^sd_s.", "21341234^sd_s."));
        assertTrue(InputValidator.isNotValidEmailAndPassword( "te.s_t@email.com", "Ldsf@#dfsfds", "Ldsf@#dfsfds"));
    }

    @Test
    public void testValidateBadEmail() {
        assertFalse(InputValidator.isNotValidEmailAndPassword( "t/e.s_t@email.com", "asdfjklgh", "asdfjklgh"));
        assertFalse(InputValidator.isNotValidEmailAndPassword( "te.s_t@ema il.com", "askdjfhsk", "askdjfhsk"));
        assertFalse(InputValidator.isNotValidEmailAndPassword( "tes t@ema il.com", "sldakfhlaskd", "sldakfhlaskd"));
        assertFalse(InputValidator.isNotValidEmailAndPassword( "test@email.com", "sldakfhlaskd", "sldakfaskd"));
    }



}