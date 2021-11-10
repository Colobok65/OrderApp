package ru.scur.orderapp.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "user")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password", length = 3000)
    private String password;

    @Column(name = "address")
    @JoinColumn(name = "addr")
    private String address;

    @ManyToMany
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "user")
    private List<GoodsOrder> orders = new ArrayList<>();

    public User() {
    }

    public User(Long id,
                String username,
                String login,
                String address,
                String password,
                List<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.address = address;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
