package com.example.logindos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNotLocked;
    private boolean accountNotExpired;
    private boolean credentialNotExpired;

    //Usamos set porque no admite repetidos
    //List permite repetidos

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//el eager me va a cargar todos los roles
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns=@JoinColumn(name = "role_id"))
    private Set<Role> rolesList = new HashSet<>();


}
