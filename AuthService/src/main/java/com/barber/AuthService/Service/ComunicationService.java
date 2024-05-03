package com.barber.AuthService.Service;

import com.barber.AuthService.Repository.BarberUserRepository;
import com.barber.AuthService.dto.BarberInformationResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComunicationService {

    @Autowired
    private final BarberUserRepository barberUserRepository;

    @Transactional(readOnly = true)
    public BarberInformationResponse getInfomation (){

        String userEmail = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            userEmail = user.getUsername();
        }

        var barberuser = barberUserRepository.findByEmail(userEmail).orElseThrow();
        BarberInformationResponse barberInformationResponse = BarberInformationResponse.builder()
                .name(barberuser.getName())
                .barbershop(barberuser.getBarbershop())
                .age(barberuser.getAge())
                .email(barberuser.getEmail())
                .lastname(barberuser.getLastname())
                .nickname(barberuser.getNickname())
                .build();
        return barberInformationResponse;
    }
}
