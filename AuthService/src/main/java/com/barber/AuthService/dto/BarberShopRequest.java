package com.barber.AuthService.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarberShopRequest {

    private String CompanyName;
    private String password;
    private String email;
}
