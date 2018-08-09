package ua.kpi.tef2.controller;

import ua.kpi.tef2.controller.command.Command;
import ua.kpi.tef2.controller.command.RegistrationCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.kpi.tef2.controller.PageNames.LOGIN_PAGE;


@WebServlet("/")
public class MainServlet extends HttpServlet {

    private final String CONTEXT_PATH_REG_EX = ".*/app/";
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("registration", new RegistrationCommand());
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
        Command command = commands.getOrDefault(commandName, (def) -> "index.jsp");

        String path = command.execute(request);
        if (isLoginPage(path)) {
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
            return;
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    private boolean isLoginPage(String pageName) {
        return pageName.equals(LOGIN_PAGE);
    }
}
