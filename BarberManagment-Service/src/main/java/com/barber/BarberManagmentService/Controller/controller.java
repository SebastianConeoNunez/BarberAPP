package com.barber.BarberManagmentService.Controller;

import com.barber.BarberManagmentService.Service.ManagmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("api/managment/barber")
public class controller {

    private final ManagmentService service;

    @GetMapping("/GeneralInformation")
    @ResponseStatus(HttpStatus.OK)
    public String showInformation(){return service.Getinformation();}

}
