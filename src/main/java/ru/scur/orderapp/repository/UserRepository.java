package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
}
