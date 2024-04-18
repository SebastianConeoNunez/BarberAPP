package com.barber.AuthService.Repository;

import com.barber.AuthService.model.BaberShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository <BaberShop,Integer>{
    Optional<BaberShop> findByemai(String email);
}
