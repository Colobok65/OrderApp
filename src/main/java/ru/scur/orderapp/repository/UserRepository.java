package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.scur.orderapp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u JOIN FETCH u.authorities r WHERE u.login = :login")
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);

}
