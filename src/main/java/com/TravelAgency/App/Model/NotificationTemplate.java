package com.TravelAgency.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int templateId;

    private String type; // Email, SMS
    private String content; // Template content with placeholders
    private String channel; // Email, SMS, etc.


    // Getters and Setters
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


}
