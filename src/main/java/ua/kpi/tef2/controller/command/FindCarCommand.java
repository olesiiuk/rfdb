package ua.kpi.tef2.controller.command;

import ua.kpi.tef2.model.entity.Car;
import ua.kpi.tef2.model.entity.User;
import ua.kpi.tef2.model.service.AddressService;
import ua.kpi.tef2.model.service.CarService;
import ua.kpi.tef2.model.service.PriceService;
import ua.kpi.tef2.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static ua.kpi.tef2.controller.PageNames.*;

public class FindCarCommand implements Command {

    private final String CAR_TYPE_PARAM = "carType";
    private final String ADDRESS_FROM_PARAM = "addressFrom";
    private final String ADDRESS_TO_PARAM = "addressTo";
    private final String LOGIN_PARAM = "login";
    private final String PRICE_PARAM = "price";
    private final String TIME_PARAM = "timeToArrive";
    private final String INVALID_ADDRESS_MESSAGE_PARAM = "errorMessage";
    private final String NO_AVAILABLE_CARS_MESSAGE_PARAM = "noCarsMessage";

    private final String NO_AVAILABLE_CARS_MESSAGE = "message.noAvailableCars";
    private final String INVALID_ADDRESS_MESSAGE = "message.invalid.address";

    private AddressService addressService;

    private UserService userService;

    private PriceService priceService;

    private CarService carService;

    public FindCarCommand(AddressService addressService, UserService userService,
                          PriceService priceService, CarService carService) {

        this.addressService = addressService;
        this.userService = userService;
        this.priceService = priceService;
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String addressFrom = request.getParameter(ADDRESS_FROM_PARAM);
        String addressTo = request.getParameter(ADDRESS_TO_PARAM);
        String carType = request.getParameter(CAR_TYPE_PARAM);

        if (isNotValid(addressFrom, addressTo)) {
            request.setAttribute(INVALID_ADDRESS_MESSAGE_PARAM, INVALID_ADDRESS_MESSAGE);
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

        if (carService.hasAvailableCars()) {
            request.setAttribute(PRICE_PARAM, price);
            request.setAttribute(TIME_PARAM, carService.timeToArrive(new Car()));
        } else {
            request.setAttribute(NO_AVAILABLE_CARS_MESSAGE_PARAM, NO_AVAILABLE_CARS_MESSAGE);
        }

        return USER_RESULT_PAGE;
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
