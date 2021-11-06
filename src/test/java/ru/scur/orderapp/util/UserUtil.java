package ru.scur.orderapp.util;

import ru.scur.orderapp.model.User;

import java.util.Collections;

public class UserUtil {

    public static User getUser() {
        return new User(
                1L,
                "username",
                "login",
                "address",
                "password", Collections.emptyList());
    }
}
