package com.barber.AuthService.AuthController;

import com.barber.AuthService.Service.AuthenticationService;
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
}
