package com.barber.AuthService.Service;

import com.barber.AuthService.Repository.BarberRepository;
import com.barber.AuthService.Repository.BarberUserRepository;
import com.barber.AuthService.dto.BarberInformationResponse;
import com.barber.AuthService.model.Barbers;
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
    private final BarberRepository barberRepository;

    private String  UpdateSecurity(){

        String userEmail = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            return userEmail = user.getUsername();
        }
        else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public BarberInformationResponse getInfomation (){

        String userEmail = UpdateSecurity();
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


    @Transactional
    public String Addbarbertobarbershop(String barber){
        String userEmail = UpdateSecurity();
        System.out.println(userEmail);
        System.out.println(barber);
        var barberUser = barberUserRepository.findByEmail(barber);
        var myBarber = barberRepository.findByEmail(userEmail);
        Barbers Barber = barberUser.orElse(null);
        myBarber.get().getListofBarbers().add(Barber);
        System.out.println(myBarber.get().getListofBarbers());
        return "Barbero agregado";
    }
}
