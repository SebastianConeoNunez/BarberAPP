package com.barber.AuthService.Controller;

import com.barber.AuthService.Service.ComunicationService;
import com.barber.AuthService.dto.BarberInformationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/comunication")
public class ComunicationController {

    private final ComunicationService service;

    @GetMapping("/generalinformation")
    @ResponseStatus(HttpStatus.OK)
    public BarberInformationResponse Information(){
        return service.getInfomation();
    }

    @PostMapping("/addbarbertobarbershop/{barber}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addBarberToBarbershop (@PathVariable String barber){return service.Addbarbertobarbershop(barber);}

}
