package com.TravelAgency.App.Controller;

import com.TravelAgency.App.Model.Event;
import com.TravelAgency.App.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;
    @GetMapping
    public List<Event> getAllEvents()
    {
        return eventService.getAllEvents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") int id)
    {
        Optional<Event> existingEvent = eventService.getEventById(id);
        if(existingEvent.isPresent()) {
           return new ResponseEntity<>(existingEvent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event)
    {
        String result =eventService.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") int id,@RequestBody Event event)
    {
        Optional<Event> existingEvent = eventService.getEventById(id);
        if(existingEvent.isPresent()) {
            eventService.updateEvent(id, event);
            return new ResponseEntity<>(existingEvent.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") int id)
    {
        Optional<Event> event = eventService.getEventById(id);
        eventService.removeEvent(id);
        return new ResponseEntity<>(event.get(),HttpStatus.OK);
    }

}
