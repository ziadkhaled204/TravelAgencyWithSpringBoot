package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate,Integer> {
    Optional<NotificationTemplate> findByType(String type);
}
