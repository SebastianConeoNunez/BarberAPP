package com.barber.AuthService.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "BarberShopsDataBase")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class barber implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String companyname;

    private String password;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Barbers> ListofBarbers ;



    @Enumerated(EnumType.STRING)
    private Role role ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
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
