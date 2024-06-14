package com.barber.BarberManagmentService.Controller;

import com.barber.BarberManagmentService.Service.BarbershopManagmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/managment/Barbershop")
public class BarberShopManagmentController {

    private final BarbershopManagmentService service;

    @PatchMapping("/addBarber/{barber}")
    @ResponseStatus(HttpStatus.OK)
    public String addBarber(@PathVariable String barber, @RequestHeader ("Authorization") String token){
        String jwt = token.substring(7);
        return service.addbaber(barber,jwt);}
}
