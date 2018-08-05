package ua.kpi.tef2.controller.command.validators;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidatorTest {

    @Test
    public void testValidateGoodEmail() {
        assertTrue(InputValidator.isValidEmailAndPassword( "test@email.com.ua", "password", "password"));
        assertTrue(InputValidator.isValidEmailAndPassword( "test@email.com", "21341234^sd_s.", "21341234^sd_s."));
        assertTrue(InputValidator.isValidEmailAndPassword( "te.s_t@email.com", "Ldsf@#dfsfds", "Ldsf@#dfsfds"));
    }

    @Test
    public void testValidateBadEmail() {
        assertFalse(InputValidator.isValidEmailAndPassword( "t/e.s_t@email.com", "asdfjklgh", "asdfjklgh"));
        assertFalse(InputValidator.isValidEmailAndPassword( "te.s_t@ema il.com", "askdjfhsk", "askdjfhsk"));
        assertFalse(InputValidator.isValidEmailAndPassword( "tes t@ema il.com", "sldakfhlaskd", "sldakfhlaskd"));
        assertFalse(InputValidator.isValidEmailAndPassword( "test@email.com", "sldakfhlaskd", "sldakfaskd"));
    }



}