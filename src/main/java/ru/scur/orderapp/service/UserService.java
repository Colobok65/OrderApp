package ru.scur.orderapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.UserDTO;
import ru.scur.orderapp.exception.UserExistException;
import ru.scur.orderapp.model.Role;
import ru.scur.orderapp.model.User;
import ru.scur.orderapp.payload.request.SignupRequest;
import ru.scur.orderapp.repository.UserRepository;

import java.security.Principal;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setUsername(userIn.getUsername());
        user.setLogin(userIn.getLogin());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(Role.USER);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistException("This user " + user.getUsername() + " already exist. Please check your credentials");
        }
    }

    public User updateUser(UserDTO userDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setUsername(userDto.getUsername());
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

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
