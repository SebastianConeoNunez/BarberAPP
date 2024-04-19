package com.barber.AuthService.Service;

import com.barber.AuthService.Repository.BarberRepository;
import com.barber.AuthService.Repository.UsersRepository;
import com.barber.AuthService.dto.*;
import com.barber.AuthService.model.Role;
import com.barber.AuthService.model.Users;
import com.barber.AuthService.model.barber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UsersRepository repository;

    @Autowired
    private final BarberRepository barberRepository;
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager manager;


    public String Register(RequestrRegister register){
        var user = Users.builder()
                .name(register.getName())
                .age(register.getAge())
                .lastname(register.getLastname())
                .email(register.getEmail())
                .useres(register.getUser())
                .role(Role.USER)
                .password(encoder.encode(register.getPassword()))
                .build();

        repository.save(user);

        String value = String.valueOf(user.getRole());
        Map<String,Object>claims = new HashMap<>();
        claims.put("Role",value);


        ResponseRegister responseRegister = ResponseRegister.builder()
                .jwt(jwtService.GenerateToken(claims,user))
                .build();

        return responseRegister.getJwt();
    }

    public String RegisteBarbershop(BarberShopRequest request){

        var baberShop = barber.builder()
                .companyname(request.getCompanyname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        barberRepository.save(baberShop);

        String value = String.valueOf(baberShop.getRole());
        Map<String,Object>claims = new HashMap<>();
        claims.put("Role",value);

        ResponseRegister responseRegister = ResponseRegister.builder()
                .jwt(jwtService.GenerateToken(claims,baberShop))
                .build();
        return responseRegister.getJwt();
    }

    public String Login(LoginRequest loginRequest) {

        String jwt = null;
        if(loginRequest.getRole().equals("admin")){
            try {
                manager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );

                var BarberShop = barberRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
                String value = String.valueOf(BarberShop.getRole());
                Map<String, Object> claims = new HashMap<>();
                claims.put("Role", value);

                ResponseRegister response = ResponseRegister.builder()
                        .jwt(jwtService.GenerateToken(claims, BarberShop))
                        .build();

                jwt = response.getJwt();
                String rol = jwtService.ExtracRole(jwt);
                String emaill = jwtService.ExtracEmail(jwt);
                System.out.print("Bienvenido " + rol + emaill);
            } catch (Exception e) {
                System.out.println("No soy Barberia");
            }
        } else if(loginRequest.getRole().equals("user")) {
            try {
                manager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );

                var user = repository.findByEmail(loginRequest.getEmail()).orElseThrow();
                String value = String.valueOf(user.getRole());
                Map<String, Object> claims = new HashMap<>();
                claims.put("Role", value);

                ResponseRegister response = ResponseRegister.builder()
                        .jwt(jwtService.GenerateToken(claims, user))
                        .build();

                jwt = response.getJwt();
                String rol = jwtService.ExtracRole(jwt);
                String emaill = jwtService.ExtracEmail(jwt);
                System.out.print("Bienvenido " + rol + emaill);
            } catch (Exception e) {
                System.out.println("No soy usurario");
            }
        }

        return jwt;
    }
}
