package ua.kpi.tef2.controller.validators;

public class InputValidator {

    private final static String EMAIL_REG_EX = "^[\\w.]{3,15}@[a-zA-Z.]{4,15}";
    private final static String PASSWORD_REG_EX = "^[\\w.^@#$%&\\d]{6,20}$";

    public static boolean isNotValidEmailAndPassword(String email, String password, String confirmPassword) {

        return !(email != null && password != null && password.equals(confirmPassword)
                && email.matches(EMAIL_REG_EX) && password.matches(PASSWORD_REG_EX));

    }

    public static boolean isNotValidEmailAndPassword(String email, String password) {

        return !(email != null && password != null &&
                email.matches(EMAIL_REG_EX) && password.matches(PASSWORD_REG_EX));
    }

}
