package ru.scur.orderapp.payload.request;

import lombok.Data;
import ru.scur.orderapp.annotations.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    private String username;

    @NotEmpty(message = "Please enter your login")
    private String login;

    @NotEmpty(message = "Please enter your address")
    private String address;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;

    private String confirmPassword;

}
