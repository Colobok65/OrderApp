package ru.scur.orderapp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
