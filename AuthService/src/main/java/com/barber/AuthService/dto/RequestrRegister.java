package com.barber.AuthService.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestrRegister {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String user;
    private Integer age;
}
