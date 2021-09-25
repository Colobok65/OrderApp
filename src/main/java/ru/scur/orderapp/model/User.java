package ru.scur.orderapp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Table(name = "user")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "login")
    private String login;

    @Column(name = "password", length = 3000)
    private String password;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

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
                String password,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

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
