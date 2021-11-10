package ru.scur.orderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.scur.orderapp.model.User;
import ru.scur.orderapp.payload.request.LoginRequest;
import ru.scur.orderapp.payload.request.SignupRequest;
import ru.scur.orderapp.payload.response.JwtTokenSuccessResponse;
import ru.scur.orderapp.payload.response.MessageResponse;
import ru.scur.orderapp.security.JwtTokenProvider;
import ru.scur.orderapp.security.SecurityConstants;
import ru.scur.orderapp.service.UserService;
import ru.scur.orderapp.validations.ResponseErrorValidation;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order_app/auth/")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;

    @PostMapping("signin")
    public ResponseEntity<Object> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.getUserByUsername(loginRequest.getLogin());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getLogin(),
                loginRequest.getPassword(),
                user.getAuthorities()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenSuccessResponse(true, jwt));
    }


    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(
            @Valid @RequestBody SignupRequest signupRequest,
            BindingResult bindingResult) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
