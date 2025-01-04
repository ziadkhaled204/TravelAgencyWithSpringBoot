package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.Notification;
import com.TravelAgency.App.Repos.NotificationRepository;
import com.TravelAgency.App.Repos.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationTemplateRepository notificationTemplateRepository;
    @Autowired
    TemplateService templateService;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("$travelaagencyy@gmail.com")
    private String emailId;


    public void queueNotification(String type, Map<String, String> placeholders, String recipient, String channel) {
        // Generate the notification content from the template
        String content = templateService.generateTemplate(type, placeholders);

        // Create a new notification entity and save it
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setRecipient(recipient);
        notification.setNotificationChannel(channel);
        notification.setStatus("PENDING"); // if we use queuing method then it should be "pending"
        notificationRepository.save(notification);

    }

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void processQueue() {
        // Fetch pending notifications
        List<Notification> pendingNotifications = notificationRepository.findByStatus("PENDING");

        for (Notification notification : pendingNotifications) {
            try {
                // Simulate sending
                boolean sent = sendNotification(notification);

                if (sent) {
                    notification.setStatus("SENT");
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setSubject(notification.getType());
                    simpleMailMessage.setFrom(emailId);
                    simpleMailMessage.setTo(notification.getRecipient());
                    simpleMailMessage.setText(notification.getContent());

                    javaMailSender.send(simpleMailMessage);
                } else {
                    notification.setStatus("FAILED");
                }
            } catch (Exception e) {
                notification.setStatus("FAILED");
            }

            notificationRepository.save(notification); // Update the notification status
        }
    }

    private boolean sendNotification(Notification notification) {
        // Logic for sending email/SMS (stubbed here)
        if ("Email".equals(notification.getNotificationChannel())) {
            return true; // Simulate successful email send
        } else if ("SMS".equals(notification.getNotificationChannel())) {
            return false; // Simulate failed SMS send
        }
        return false;
    }


    public Map<String, Object> getNotificationStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // Count of successfully sent notifications
        long successCount = notificationRepository.countByStatus("SENT");
        statistics.put("successCount", successCount);

        // Count of failed notifications
        long failureCount = notificationRepository.countByStatus("FAILED");
        statistics.put("failureCount", failureCount);

        // Top recipient
        String mostNotifiedRecipient = notificationRepository.findTopRecipient();
        statistics.put("mostNotifiedRecipient", mostNotifiedRecipient);

        // Top notification template type
        String mostUsedTemplateType = notificationRepository.findTopTemplateType();
        statistics.put("mostUsedTemplateType", mostUsedTemplateType);

        return statistics;
    }
}

