package com.TravelAgency.App.Controller;

import com.TravelAgency.App.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getNotificationStatistics() {
        Map<String, Object> statistics = notificationService.getNotificationStatistics();
        return ResponseEntity.ok(statistics);
    }
}
