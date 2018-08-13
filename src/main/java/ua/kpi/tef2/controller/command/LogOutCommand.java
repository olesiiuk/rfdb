package ua.kpi.tef2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.tef2.controller.PageNames.REDIRECT_PREFIX;
import static ua.kpi.tef2.controller.PageNames.HOME_PAGE;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return REDIRECT_PREFIX + HOME_PAGE;
    }
}
