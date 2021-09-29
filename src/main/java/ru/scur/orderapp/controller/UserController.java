package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.scur.orderapp.converter.UserConverter;
import ru.scur.orderapp.dto.UserDTO;
import ru.scur.orderapp.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/order_app/user")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService,
                          UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping()
    public UserDTO getCurrentUser(Principal principal) {
        return userConverter.toUserDTO(userService.getCurrentUser(principal));
    }

    @PutMapping()
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDto, Principal principal) {
        return userConverter.toUserDTO(userService.updateUser(userDto, principal));
    }
}
