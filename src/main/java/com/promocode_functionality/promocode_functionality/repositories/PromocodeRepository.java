package com.promocode_functionality.promocode_functionality.repositories;

import com.promocode_functionality.promocode_functionality.entities.Promocodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocodeRepository extends JpaRepository<Promocodes,Long> {
}
