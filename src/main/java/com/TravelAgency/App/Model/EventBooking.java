package com.TravelAgency.App.Model;

import jakarta.persistence.*;

@Entity
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    private int userId;
    private int eventId;
    private String eventDate;
    private int seatNumber ;
    private String status;


    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
