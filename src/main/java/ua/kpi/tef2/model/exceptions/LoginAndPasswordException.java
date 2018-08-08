package ua.kpi.tef2.model.exceptions;

public class LoginAndPasswordException extends Exception {
    public LoginAndPasswordException() {
        super("Invalid login or password");
    }
}
