package com.TravelAgency.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;
    private String hotelName;
    private String hotelLocation;
    private String status;

    // Getters and Setters
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setName(String name) {
        this.hotelName = name;
    }

    public String getLocation() {
        return hotelLocation;
    }

    public void setLocation(String location) {
        this.hotelLocation = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}