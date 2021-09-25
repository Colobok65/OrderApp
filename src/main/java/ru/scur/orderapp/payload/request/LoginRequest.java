package ru.scur.orderapp.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty(message = "Login can not be empty")
    private String login;

    @NotEmpty(message = "Password can not be empty")
    private String password;
}
