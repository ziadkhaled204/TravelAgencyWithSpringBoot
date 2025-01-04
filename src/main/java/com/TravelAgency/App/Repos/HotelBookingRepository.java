package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingRepository extends JpaRepository<HotelBooking,Integer> {

}
