package com.TravelAgency.App.Controller;

import com.TravelAgency.App.Model.EventBooking;
import com.TravelAgency.App.Model.HotelBooking;
import com.TravelAgency.App.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Get all hotel bookings
    @GetMapping("/hotelbooking")
    public List<HotelBooking> getHotelBookings() {
        return bookingService.getHotelBookings();
    }

    // Get all event bookings
    @GetMapping("/eventbooking")
    public List<EventBooking> getEventBookings() {
        return bookingService.getEventBookings();
    }

    // Create a hotel booking
    @PostMapping("/hotelbooking")
    public ResponseEntity<String> createHotelBooking(@RequestBody HotelBooking hotelBooking) {
        try {
            bookingService.createHotelBooking(hotelBooking);
            return ResponseEntity.ok("Hotel booking created and notification sent.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Create an event booking
    @PostMapping("/eventbooking")
    public ResponseEntity<String> createEventBooking(@RequestBody EventBooking eventBooking) {
        bookingService.createEventBooking(eventBooking);
        return ResponseEntity.ok("Event booking created and notification sent.");
    }

    // Delete an event booking
    @DeleteMapping("/eventbooking/{id}")
    public ResponseEntity<String> deleteEventBooking(@PathVariable("id") int id) {
        bookingService.deleteEventBooking(id);
        return ResponseEntity.ok("Event booking deleted.");
    }

    // Delete a hotel booking
    @DeleteMapping("/hotelbooking/{id}")
    public ResponseEntity<String> deleteHotelBooking(@PathVariable("id") int id) {
        bookingService.deleteHotelBooking(id);
        return ResponseEntity.ok("Hotel booking deleted.");
    }

    // Update a hotel booking
    @PutMapping("/hotelbooking/{id}")
    public ResponseEntity<String> updateHotelBooking(@PathVariable("id") int id, @RequestBody HotelBooking hotelBooking) {
        bookingService.updateHotelBooking(id, hotelBooking);
        return ResponseEntity.ok("Hotel booking updated.");
    }

    // Update an event booking
    @PutMapping("/eventbooking/{id}")
    public ResponseEntity<String> updateEventBooking(@PathVariable("id") int id, @RequestBody EventBooking eventBooking) {
        bookingService.updateEventBooking(id, eventBooking);
        return ResponseEntity.ok("Event booking updated.");
    }
}
