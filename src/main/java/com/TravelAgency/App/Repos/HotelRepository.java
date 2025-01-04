package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
