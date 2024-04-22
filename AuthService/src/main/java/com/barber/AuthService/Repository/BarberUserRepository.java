package com.barber.AuthService.Repository;

import com.barber.AuthService.model.Barbers;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberUserRepository extends JpaRepository<Barbers, Integer> {

    Optional<Barbers> findByEmail(String email);
}
