package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.model.service.AddressService;
import javax.servlet.http.HttpServletRequest;

import static ua.kpi.tef2.controller.PageNames.USER_HOME_PAGE;

public class UserHomePageCommand implements Command {

    private AddressService addressService;

    public UserHomePageCommand(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("addressList", addressService.getAll());

        return USER_HOME_PAGE;
    }
}
