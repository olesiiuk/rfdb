package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.controller.validators.InputValidator;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.LoginAndPasswordException;
import ua.kpi.tef2.model.service.UserService;
import ua.kpi.tef2.model.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.HashMap;
import java.util.Map;

import static ua.kpi.tef2.controller.PageNames.*;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    private final String EMAIL_ATR = "email";
    private final String PASSWORD_ATR = "password";

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL_ATR);
        String password = request.getParameter(PASSWORD_ATR);

        if (InputValidator.isNotValidUserData(email, password)) {
            return ERROR_PAGE;
        }

        User user;
        try {
            user = userService.findUserByEmail(email).orElseThrow(LoginAndPasswordException::new);
        } catch (LoginAndPasswordException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return LOGIN_PAGE;
        }
        //TODO define users role and send him to the correct page
        String role = user.getRole();
        if (role.equals("USER")) {
            addUserToServletContextAndSession(user, request);
            return REDIRECT_PREFIX + USER_HOME_PAGE;
        } else if (role.equals("ADMIN")) {
            addUserToServletContextAndSession(user, request);
            return REDIRECT_PREFIX + ADMIN_HOME_PAGE;
        }

        return ERROR_PAGE;
    }

    private void addUserToServletContextAndSession(User user, HttpServletRequest request) {
        addUserToSession(user, request);
        addUserToServletContext(user, request);
    }

    private void addUserToServletContext(User user, HttpServletRequest request) {
        ServletContext context = request.getServletContext();

        Map<String, String> users = (Map) context.getAttribute("loggedUsers");
        if (users == null) {
            users = new HashMap<>();
        }
        users.put(user.getLogin(), user.getRole());
        context.setAttribute("loggedUsers", users);
    }

    private void addUserToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("login", user.getLogin());
        session.setAttribute("role", user.getRole());
    }

}
