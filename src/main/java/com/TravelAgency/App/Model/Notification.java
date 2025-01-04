package com.TravelAgency.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int NotificationId;
    private String content;
    private String recipient;

    public String getNotificationChannel() {
        return notificationChannel;
    }

    private  String notificationChannel;
    private String status;

    public int getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(int notificationId) {
        NotificationId = notificationId;
    }
    public String getContent() {
        return content;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getType() {
        return notificationChannel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content=content;
    }

    public void setRecipient(String recipient) {
        this.recipient=recipient;
    }

    public void setNotificationChannel(String channel) {
        this.notificationChannel=channel;
    }
}
