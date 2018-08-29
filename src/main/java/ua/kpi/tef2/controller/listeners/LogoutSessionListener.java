package ua.kpi.tef2.controller.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@WebListener
public class LogoutSessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashSet<String> loggedUsers = (HashSet<String>)
                se.getSession().getServletContext().getAttribute("loggedUsers");

        String login = (String) se.getSession().getAttribute("login");
        loggedUsers.remove(login);

        se.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
