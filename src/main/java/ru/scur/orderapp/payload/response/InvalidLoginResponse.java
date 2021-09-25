package ru.scur.orderapp.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    private final String login;
    private final String password;

    public InvalidLoginResponse() {
        this.login = "Invalid Login";
        this.password = "Invalid Password";
    }
}
