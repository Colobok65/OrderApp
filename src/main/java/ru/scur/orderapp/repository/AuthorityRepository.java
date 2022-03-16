package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByName(String name);
}
