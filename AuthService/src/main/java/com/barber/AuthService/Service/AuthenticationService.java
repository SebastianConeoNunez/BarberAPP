package com.barber.AuthService.Service;

import com.barber.AuthService.Repository.UsersRepository;
import com.barber.AuthService.dto.LoginRequest;
import com.barber.AuthService.dto.RequestrRegister;
import com.barber.AuthService.dto.ResponseRegister;
import com.barber.AuthService.model.Role;
import com.barber.AuthService.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UsersRepository repository;
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager manager;

    public String Register(RequestrRegister register){
        Users user = Users.builder()
                .name(register.getName())
                .age(register.getAge())
                .email(register.getEmail())
                .user(register.getUser())
                .role(Role.USER)
                .password(encoder.encode(register.getPassword()))
                .build();

        repository.save(user);

        ResponseRegister responseRegister = ResponseRegister.builder()
                .jwt(jwtService.GenerateToken(user))
                .build();

        return responseRegister.getJwt();
    }

    public String Login(LoginRequest loginRequest){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        Users users = repository.findByemail(loginRequest.getEmail()).orElseThrow();
        ResponseRegister response = ResponseRegister.builder()
                .jwt(jwtService.GenerateToken(users))
                .build();
        String jwt = response.getJwt();
        return jwt;
    }

}
