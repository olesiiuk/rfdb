package ua.kpi.tef2.controller.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kpi.tef2.controller.PageNames.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getRequestURI().replaceAll(CONTEXT_PATH_REG_EX, "");
        String userRole = (String) req.getSession().getAttribute("role");


        if (notAuthorized(path, userRole)) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            //TODO Do logout for this user and clean user data from context and kill session
            resp.sendRedirect(req.getContextPath() + LOGOUT_PAGE);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //TODO refactor this method
    private boolean notAuthorized(String path, String role) {
        if ((role == null || role.equals("")) &&
                (!path.startsWith("user") && !path.startsWith("admin") && !path.startsWith("logout"))) {

            return false;
        }
        if (role == null || role.equals("")) {
            return true;
        }
        if (path.startsWith("admin") && !role.equals("ADMIN") ||
            path.startsWith("user") && !role.equals("USER")) {
            return true;
        }
        return (role.equals("ADMIN") || role.equals("USER")) &&
                (path.endsWith("registration") || path.endsWith("login"));
    }


}
