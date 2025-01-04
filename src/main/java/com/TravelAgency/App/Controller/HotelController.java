package com.TravelAgency.App.Controller;


import com.TravelAgency.App.Model.Event;
import com.TravelAgency.App.Model.Hotel;
import com.TravelAgency.App.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels()
    {
        return hotelService.getAllHotels();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") int id)
    {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isPresent())
        {
            return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel)
    {
        hotelService.addHotel(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") int id, @RequestBody Hotel hotel)
    {
        Optional<Hotel> existingHotel = hotelService.getHotelById(id);
        if(existingHotel.isPresent()) {
            hotelService.updateHotel(id, hotel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable("id") int id)
    {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        hotelService.removeHotel(id);
        return new ResponseEntity<>(hotel.get(),HttpStatus.OK);
    }
}
