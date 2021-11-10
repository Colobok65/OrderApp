package ru.scur.orderapp.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
