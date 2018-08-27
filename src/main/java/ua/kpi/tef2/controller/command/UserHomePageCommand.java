package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.model.entity.Address;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ua.kpi.tef2.controller.PageNames.USER_HOME_PAGE;

public class UserHomePageCommand implements Command {

    //TODO make address DAO and service.

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("addressList", addressListMock());

        return USER_HOME_PAGE;
    }

    private List<Address> addressListMock() {
        List<Address> addressList = new ArrayList<>();
        Address address1 = new Address("Rabochaya", 152);
        address1.setId(1);

        Address address2 = new Address("Naberejnaya", 12);
        address2.setId(2);

        Address address3 = new Address("Street17", 11);
        address3.setId(3);

        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);
        return addressList;
    }
}
