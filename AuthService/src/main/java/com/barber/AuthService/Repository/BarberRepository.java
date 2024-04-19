package com.barber.AuthService.Repository;


import com.barber.AuthService.model.barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BarberRepository extends JpaRepository <barber,Integer>{
    Optional<barber> findByEmail(String email);
}
