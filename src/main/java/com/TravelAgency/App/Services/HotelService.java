package com.TravelAgency.App.Services;

import com.TravelAgency.App.Model.Hotel;
import com.TravelAgency.App.Repos.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public Optional<Hotel> getHotelById(int id) {
        return hotelRepository.findById(id);
    }

    public String addHotel(Hotel hotel) {
        Optional<Hotel> existingHotel = hotelRepository.findById(hotel.getHotelId());
        if (existingHotel.isPresent()) {
            return "Hotel Already Exists";
        }
        hotelRepository.save(hotel);
        return "Hotel has been added successfully.";
    }

    public boolean removeHotel(int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            hotelRepository.deleteById(id);
            System.out.println("Hotel with ID " + id + " has been deleted.");
            return true;
        }
        System.err.println("Hotel with ID " + id + " does not exist.");
        return false;
    }

    public String updateHotel(int id, Hotel updatedHotel) {
        Optional<Hotel> existingHotel = hotelRepository.findById(id);
        if (existingHotel.isPresent()) {
            updatedHotel.setHotelId(id); // Set the ID to update the correct hotel
            hotelRepository.save(updatedHotel);
            return "Hotel updated successfully!";
        }
        return "Hotel not found!";
    }
}
