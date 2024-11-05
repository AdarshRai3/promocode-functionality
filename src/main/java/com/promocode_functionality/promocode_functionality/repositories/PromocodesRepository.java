package com.promocode_functionality.promocode_functionality.repositories;

import com.promocode_functionality.promocode_functionality.entities.Promocodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PromocodesRepository extends JpaRepository<Promocodes, Long> {

    // Find a promocode by its code
    Optional<Promocodes> findByCode(String code);

    // Check if a promocode with the given code exists
    boolean existsByCode(String code);

}