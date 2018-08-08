package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.controller.validators.InputValidator;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.LoginAndPasswordException;
import ua.kpi.tef2.model.service.UserService;
import ua.kpi.tef2.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.tef2.controller.PageNames.ERROR_PAGE;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    private final String EMAIL_ATR = "email";
    private final String PASSWORD_ATR = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL_ATR);
        String password = request.getParameter(PASSWORD_ATR);

        if (InputValidator.isNotValidEmailAndPassword(email, password)) {
            return ERROR_PAGE;
        }

        try {
            //TODO refactor with Optional
            User user = userService.findUserByEmail(email).get();
        } catch (LoginAndPasswordException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();


        return "";

    }
}
