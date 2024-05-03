package com.barber.AuthService.AuthController;

import com.barber.AuthService.Service.AuthenticationService;
import com.barber.AuthService.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class controller {

    private final AuthenticationService service ;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String Register(@RequestBody RequestrRegister register){
        return service.Register(register);
    }

    @PostMapping("/RegisterBarber")
    @ResponseStatus(HttpStatus.CREATED)
    public String RegisterBarber(@RequestBody BarberShopRequest registerBarbershop){
        return service.RegisteBarbershop(registerBarbershop);
    }

    @PostMapping("/RegisterBarbersUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String RegisterBarbersUsers(@RequestBody BarberRequest barberRequest){
        return service.RegisterBarber(barberRequest);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String Login(@RequestBody LoginRequest loginRequest){
        return service.Login(loginRequest);
    }



}


