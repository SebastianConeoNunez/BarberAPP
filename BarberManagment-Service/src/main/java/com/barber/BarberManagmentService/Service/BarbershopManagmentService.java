package com.barber.BarberManagmentService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BarbershopManagmentService {

    @Autowired
    private final WebClient webClient;

    public String addbaber(String barber, String token){

        String acepted = webClient.patch()
                .uri("http://localhost:8080/api/comunication/addbarbertobarbershop",uriBuilder -> uriBuilder.queryParam("barber",barber).build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return  acepted;

    }
}
