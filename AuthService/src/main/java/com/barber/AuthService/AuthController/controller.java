package com.barber.AuthService.AuthController;

import com.barber.AuthService.Service.AuthenticationService;
import com.barber.AuthService.dto.BarberShopRequest;
import com.barber.AuthService.dto.LoginRequest;
import com.barber.AuthService.dto.RequestrRegister;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class controller {

    private final AuthenticationService service ;

    @PostMapping("/Register")
    @ResponseStatus(HttpStatus.CREATED)
    public String Register(@RequestBody RequestrRegister register){
        return service.Register(register);
    }

    @PostMapping("/RegisterBarber")
    @ResponseStatus(HttpStatus.CREATED)
    public String RegisterBarber(@RequestBody BarberShopRequest registerBarbershop){
        return service.RegisteBarbershop(registerBarbershop);
    }

    @GetMapping("/Login")
    @ResponseStatus(HttpStatus.OK)
    public String Login(@RequestBody LoginRequest loginRequest){
        return service.Login(loginRequest);
    }

}


