package com.promocode_functionality.promocode_functionality.repositories;

import com.promocode_functionality.promocode_functionality.entities.PromocodeUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocodeUsageRepository extends JpaRepository<PromocodeUsage,Long> {
}
