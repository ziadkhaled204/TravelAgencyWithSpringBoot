package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.*;
import com.TravelAgency.App.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private EventBookingRepository eventBookingRepository;
    @Autowired
    private HotelBookingRepository hotelBookingRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;

    // Get all hotel bookings
    public List<HotelBooking> getHotelBookings() {
        return hotelBookingRepository.findAll();
    }

    // Get all event bookings
    public List<EventBooking> getEventBookings() {
        return eventBookingRepository.findAll();
    }

    // Create a hotel booking
    public void createHotelBooking(HotelBooking hotelBooking) {

        hotelBookingRepository.save(hotelBooking);

        // Prepare placeholders for notification
        Map<String, String> placeholders = new HashMap<>();
        Optional<User> tempUser = userRepository.findById(hotelBooking.getUserId());
        Optional<Hotel> tempHotel = hotelRepository.findById(hotelBooking.getHotelId());

        if (tempUser.isEmpty() || tempHotel.isEmpty()) {
            throw new RuntimeException("User or Hotel not found");
        }

        // Prepare notification type and content
        String type = "Booking Confirmation";
        placeholders.put("x", tempUser.get().getUserName());
        placeholders.put("y", tempHotel.get().getHotelName());
        String recipient = tempUser.get().getEmail();

        // Create notification
        notificationService.queueNotification(type, placeholders, recipient, "Email");
    }

    // Create an event booking
    public void createEventBooking(EventBooking eventBooking) {

        eventBookingRepository.save(eventBooking);
        // Prepare placeholders for notification
        Map<String, String> placeholders = new HashMap<>();
        Optional<User> tempUser = userRepository.findById(eventBooking.getUserId());
        Optional<Event> tempevent = eventRepository.findById(eventBooking.getEventId());

        if (tempUser.isEmpty() || tempevent.isEmpty()) {
            throw new RuntimeException("User or Hotel not found");
        }

        // Prepare notification type and content
        String type = "Booking Confirmation";
        placeholders.put("x", tempUser.get().getUserName());
        placeholders.put("y", tempevent.get().getEventName());
        String recipient = tempUser.get().getEmail();

        // Create notification
        notificationService.queueNotification(type, placeholders, recipient, "Email");
    }

    // Delete an event booking
    public void deleteEventBooking(int id) {
        eventBookingRepository.deleteById(id);
    }

    // Delete a hotel booking
    public void deleteHotelBooking(int id) {
        hotelBookingRepository.deleteById(id);
    }

    // Update a hotel booking
    public void updateHotelBooking(int id, HotelBooking hotelBooking) {
        if (hotelBookingRepository.existsById(id)) {
            hotelBooking.setBookingId(id);
            hotelBookingRepository.save(hotelBooking);
        }
    }

    // Update an event booking
    public void updateEventBooking(int id, EventBooking eventBooking) {
        if (eventBookingRepository.existsById(id)) {
            eventBooking.setBookingId(id);
            eventBookingRepository.save(eventBooking);
        }
    }
}
