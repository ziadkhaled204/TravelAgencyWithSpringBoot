package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
