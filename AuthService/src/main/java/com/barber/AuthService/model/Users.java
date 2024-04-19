package com.barber.AuthService.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.channels.Pipe;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "UsersDataBase")
public class Users implements UserDetails {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    private String lastname;

    private String useres;

    private String password;

    private String email;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {return true;
    }

    @Override
    public boolean isAccountNonLocked() {return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {return true;
    }

    @Override
    public boolean isEnabled() {return true;
    }
}
