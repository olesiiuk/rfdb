package ua.kpi.tef2.controller;

import ua.kpi.tef2.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.kpi.tef2.controller.PageNames.*;


@WebServlet("/")
public class MainServlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("registration", new RegistrationCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("user/home", new UserHomePageCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getRequestURI().replaceAll(CONTEXT_PATH_REG_EX, "");
        Command command = commands.getOrDefault(commandName, (def) -> HOME_PAGE);

        String path = command.execute(request);
        if (isPageToRedirect(path)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + cutRedirectPrefix(path));
            return;
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    private String cutRedirectPrefix(String path) {
        return path.replace(REDIRECT_PREFIX, "");
    }

    private boolean isLoginPage(String pageName) {
        return pageName.equals(LOGIN_PAGE);
    }

    private boolean isPageToRedirect(String pageName) {
        return pageName.startsWith(REDIRECT_PREFIX);
    }
}
