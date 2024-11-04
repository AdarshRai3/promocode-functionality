package com.promocode_functionality.promocode_functionality.repositories;

import com.promocode_functionality.promocode_functionality.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRespository extends JpaRepository<Users,Long> {
}
