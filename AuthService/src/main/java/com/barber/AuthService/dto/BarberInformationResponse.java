package com.barber.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BarberInformationResponse {
    private String name;
    private String lastname;
    private String age;
    private String barbershop;
    private String email;
    private String nickname;
}
