package com.TravelAgency.App.Repos;

import com.TravelAgency.App.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    List<Notification> findByStatus(String status);
    @Query("SELECT n.recipient FROM Notification n GROUP BY n.recipient ORDER BY COUNT(n.recipient) DESC LIMIT 1")
    String findTopRecipient();
    @Query("SELECT n.content FROM Notification n GROUP BY n.content ORDER BY COUNT(n.content) DESC LIMIT 1")
    String findTopTemplateType();
    long countByStatus(String status);
}
