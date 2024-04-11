package com.barber.AuthService.Configuration;

import com.barber.AuthService.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UsersRepository repository;

    @Bean
    public UserDetailsService userDetailsService (){
       return username -> repository.findByemail(username)
               .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }


}
