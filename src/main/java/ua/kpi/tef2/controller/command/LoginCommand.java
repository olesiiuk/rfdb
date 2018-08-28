package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.controller.validators.InputValidator;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.LoginAndPasswordException;
import ua.kpi.tef2.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.HashSet;
import java.util.Set;

import static ua.kpi.tef2.controller.PageNames.*;

public class LoginCommand implements Command {

    private UserService userService;

    private final String EMAIL_ATR = "email";
    private final String PASSWORD_ATR = "password";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL_ATR);
        String password = request.getParameter(PASSWORD_ATR);

        if (InputValidator.isNotValidUserData(email, password)) {
            request.setAttribute("errorData", "Invalid login or password");
            return LOGIN_PAGE;
        }

        User user;
        try {
            user = userService.CheckUserLoginAndPassword(email, password).orElseThrow(LoginAndPasswordException::new);
        } catch (LoginAndPasswordException e) {
            request.setAttribute("errorData", "Invalid login or password");
            return LOGIN_PAGE;
        }

        if (isAlreadyLogged(user, request)) {
            request.setAttribute("errorData", "Account already in use");
            return LOGIN_PAGE;
        }

        //TODO add specific data for each page
        String role = user.getRole();
        if (role.equals("USER")) {
            addUserToServletContextAndSession(user, request);
            return REDIRECT_PREFIX + USER_HOME_REQUEST;
        } else if (role.equals("ADMIN")) {
            addUserToServletContextAndSession(user, request);
            return REDIRECT_PREFIX + ADMIN_HOME_PAGE;
        }

        return ERROR_PAGE;
    }

    //TODO ask if this method should be refactored (2 lines are duplicated)
    private boolean isAlreadyLogged(User user, HttpServletRequest request) {

        ServletContext context = request.getServletContext();
        Set<String> loggedUsers = (Set<String>) context.getAttribute("loggedUsers");

        if (loggedUsers != null) {
            return loggedUsers.contains(user.getLogin());
        }

        return false;
    }

    private void addUserToServletContextAndSession(User user, HttpServletRequest request) {
        addUserToSession(user, request);
        addUserToServletContext(user, request);
    }

    private void addUserToServletContext(User user, HttpServletRequest request) {

        ServletContext context = request.getServletContext();
        Set<String> loggedUsers = (Set<String>) context.getAttribute("loggedUsers");

        if (loggedUsers == null) {
            loggedUsers = new HashSet<>();
        }

        loggedUsers.add(user.getLogin());
        context.setAttribute("loggedUsers", loggedUsers);
    }

    private void addUserToSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("name", user.getName());
        session.setAttribute("role", user.getRole());
    }

}
