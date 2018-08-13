package ua.kpi.tef2.controller.validators;

import ua.kpi.tef2.model.entity.User;

public class InputValidator {

    private final static String EMAIL_REG_EX = "^[\\w.]{3,15}@[a-zA-Z.]{4,15}";
    private final static String PASSWORD_REG_EX = "^[\\w.^@#$%&\\d]{6,20}$";
    private final static String NAME_REG_EX = "^[\\w\\d]{1,20}";

    public static boolean isNotValidUserData(User user, String confirmPassword) {

        String email = user.getLogin();
        String password = user.getPassword();
        String userName = user.getName();

        boolean validEmail = email.matches(EMAIL_REG_EX);
        boolean validPass = password.matches(PASSWORD_REG_EX);
        boolean validName = userName.matches(NAME_REG_EX);

        return !(password.equals(confirmPassword)
                && validEmail && validPass
                && validName);
    }

    public static boolean isNotValidUserData(String email, String password) {

        return !(email != null && password != null &&
                email.matches(EMAIL_REG_EX) && password.matches(PASSWORD_REG_EX));
    }

}
