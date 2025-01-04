package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBookingRepository extends JpaRepository<EventBooking,Integer> {
}
