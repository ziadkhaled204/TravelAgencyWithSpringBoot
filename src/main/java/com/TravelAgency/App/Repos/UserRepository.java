package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // You can add custom queries here if needed
    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);
}
