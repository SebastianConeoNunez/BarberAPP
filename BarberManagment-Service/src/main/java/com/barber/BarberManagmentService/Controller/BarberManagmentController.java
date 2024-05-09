package com.barber.BarberManagmentService.Controller;

import com.barber.BarberManagmentService.Service.ManagmentService;
import com.barber.BarberManagmentService.dto.BarberInformationResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/managment/barber")
public class BarberManagmentController {

    private final ManagmentService service;

    @GetMapping("/GeneralInformation")
    @ResponseStatus(HttpStatus.OK)
    public BarberInformationResponse showInformation(@RequestHeader("Authorization") String token){
        System.out.println(token);
        String jwt = token.substring(7);
        return service.Getinformation(jwt);
    }

}
