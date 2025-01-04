package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.NotificationTemplate;
import com.TravelAgency.App.Repos.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public class TemplateService {
    @Autowired
    NotificationTemplateRepository notificationTemplateRepository;
    public String generateTemplate(String type , Map<String , String> placeholders)
    {
        Optional<NotificationTemplate> template = notificationTemplateRepository.findByType(type);

        if (template.isEmpty()) {
            throw new RuntimeException("Notification template not found for type: " + type);
        }
        String content = template.get().getContent();
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            content = content.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return content;
    }
}
