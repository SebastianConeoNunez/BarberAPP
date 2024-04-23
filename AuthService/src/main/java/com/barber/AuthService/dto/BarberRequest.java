package com.barber.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BarberRequest {
    private String name;
    private String lastname;
    private String password;
    private String age;
    private String email;
    private String nickname;
}
