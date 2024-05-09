package com.barber.BarberManagmentService.Service;

import com.barber.BarberManagmentService.dto.BarberInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ManagmentService {

    @Autowired
    private final WebClient webClient;

    public BarberInformationResponse Getinformation (String token){

        BarberInformationResponse barberInformationResponse = webClient.get()
                .uri("http://localhost:8080/api/comunication/generalinformation")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(BarberInformationResponse.class)
                .block();

        return barberInformationResponse;
    }
}
