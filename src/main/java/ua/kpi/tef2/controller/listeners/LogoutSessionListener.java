package ua.kpi.tef2.controller.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

@WebListener
public class LogoutSessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashMap<String, String> loggedUsers = (HashMap<String, String>)
                se.getSession().getServletContext().getAttribute("loggedUsers");

        String login = (String) se.getSession().getAttribute("login");
        loggedUsers.remove(login);

        se.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
