package com.barber.AuthService.Configuration;

import com.barber.AuthService.AuthFilter.JwtAuthFilter;
import com.barber.AuthService.Repository.BarberRepository;
import com.barber.AuthService.Repository.BarberUserRepository;
import com.barber.AuthService.Repository.UsersRepository;
import com.barber.AuthService.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UsersRepository repository;
    private final BarberRepository repositoryB;

    private final BarberUserRepository repositorC;



    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDetails userFromUserRepository = repository.findByEmail(username)
                    .orElse(null);
            UserDetails userFromBarberRepository = repositoryB.findByEmail(username)
                    .orElse(null);
            UserDetails userFromBarberUserRepository = repositorC.findByEmail(username)
                    .orElse(null);

            if (userFromUserRepository != null) {
                return userFromUserRepository;
            } else if (userFromBarberRepository != null) {
                return userFromBarberRepository;
            } else if (userFromBarberUserRepository != null) {
                return userFromBarberUserRepository;
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(userDetailsService());
         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
         return daoAuthenticationProvider;
    }



    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
                return authenticationConfiguration.getAuthenticationManager();
    }


}
