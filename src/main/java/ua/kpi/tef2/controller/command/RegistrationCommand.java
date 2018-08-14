package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.controller.validators.InputValidator;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.UserAlreadyExistsException;
import ua.kpi.tef2.model.service.UserService;
import ua.kpi.tef2.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.tef2.controller.PageNames.*;


public class RegistrationCommand implements Command {
    private final String EMAIL_ATTR_NAME = "email";
    private final String PASSWORD_ATR_NAME = "password";
    private final String CONFIRM_PASSWORD_ATR_NAME = "confirmPassword";
    private final String ERROR_MESSAGE = "errorMessage";
    private final String USER_NAME_PARAM = "userName";

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL_ATTR_NAME);
        String password = request.getParameter(PASSWORD_ATR_NAME);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_ATR_NAME);
        String userName = request.getParameter(USER_NAME_PARAM);

        User newUser = new User(email, password, userName);
        if (InputValidator.isNotValidUserData(newUser, confirmPassword)) {
            return ERROR_PAGE;
        }

        try {
            userService.saveNewUser(newUser);
        } catch (UserAlreadyExistsException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            return REGISTRATION_PAGE;
        }

        return REDIRECT_PREFIX + LOGIN_PAGE;
    }
}
