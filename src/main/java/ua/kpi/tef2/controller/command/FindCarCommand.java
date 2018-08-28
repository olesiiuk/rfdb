package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.exceptions.LoginAndPasswordException;
import ua.kpi.tef2.model.service.AddressService;
import ua.kpi.tef2.model.service.PriceService;
import ua.kpi.tef2.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static ua.kpi.tef2.controller.PageNames.LOGIN_PAGE;
import static ua.kpi.tef2.controller.PageNames.REDIRECT_PREFIX;
import static ua.kpi.tef2.controller.PageNames.USER_HOME_PAGE;

public class FindCarCommand implements Command {

    private final String CAR_TYPE_PARAM = "carType";
    private final String ADDRESS_FROM_PARAM = "addressFrom";
    private final String ADDRESS_TO_PARAM = "addressTo";
    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String INVALID_ADDRESS_MESSAGE = "message.invalid.address";
    private final String LOGIN_PARAM = "login";

    private AddressService addressService;

    private UserService userService;

    private PriceService priceService;

    public FindCarCommand(AddressService addressService, UserService userService, PriceService priceService) {
        this.addressService = addressService;
        this.userService = userService;
        this.priceService = priceService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String addressFrom = request.getParameter(ADDRESS_FROM_PARAM);
        String addressTo = request.getParameter(ADDRESS_TO_PARAM);
        String carType = request.getParameter(CAR_TYPE_PARAM);

        if (isNotValid(addressFrom, addressTo)) {
            request.setAttribute(ERROR_MESSAGE_PARAM, INVALID_ADDRESS_MESSAGE);
            return USER_HOME_PAGE;
        }

        int distance = addressService.getDistance(Integer.parseInt(addressFrom), Integer.parseInt(addressTo));

        Optional<User> currentUserOptional = getCurrentUser(request);
        User currentUser;
        if (currentUserOptional.isPresent()) {
            currentUser = currentUserOptional.get();
        } else {
            return REDIRECT_PREFIX + LOGIN_PAGE;
        }

        int price = priceService.getPrice(distance, carType, currentUser);


        return null;
    }

    private Optional<User> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(LOGIN_PARAM);
        return userService.findUserByEmail(login);
    }

    private boolean isNotValid(String addressFrom, String addressTo) {
        return addressFrom.equals(addressTo);
    }
}
