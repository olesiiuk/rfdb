package ua.kpi.tef2.model.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String login) {
        super(String.format("login [ %s ] already busy", login));
    }

}
