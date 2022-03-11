package ru.scur.orderapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.UserDTO;
import ru.scur.orderapp.exception.UserExistException;
import ru.scur.orderapp.model.User;
import ru.scur.orderapp.payload.request.SignupRequest;
import ru.scur.orderapp.repository.AuthorityRepository;
import ru.scur.orderapp.repository.UserRepository;

import java.security.Principal;
import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setUsername(userIn.getUsername());
        user.setLogin(userIn.getLogin());
        user.setAddress(userIn.getAddress());
        user.setAuthorities(Collections.singletonList(authorityRepository.getOne(1L)));
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistException("This user " + user.getUsername() + " already exist. Please check your credentials");
        }
    }

    public User updateUser(UserDTO userDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setUsername(userDto.getUsername());
        user.setLogin(userDto.getLogin());
        user.setAddress(userDto.getAddress());
        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
