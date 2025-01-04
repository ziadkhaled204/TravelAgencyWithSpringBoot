package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.Event;
import com.TravelAgency.App.Repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public String addEvent(Event event) {
        Optional<Event> existingEvent = eventRepository.findById(event.getEventId());
        if (existingEvent.isPresent()) {
            return "Event Already Exists";
        }
        eventRepository.save(event);
        return "Event has been added successfully.";
    }

    public boolean removeEvent(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            System.out.println("Event with ID " + id + " has been deleted.");
            return true;
        }
        System.err.println("Event with ID " + id + " does not exist.");
        return false;
    }

    public String updateEvent(int id, Event updatedEvent) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            updatedEvent.setEventId(id); // Set the ID to update the correct event
            eventRepository.save(updatedEvent);
            return "Event updated successfully!";
        }
        return "Event not found!";
    }
}
