package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.destination.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService ;

    /* Get A hotel */
    @GetMapping("/hotel/{id}")
    public ResponseEntity<?> gethotel (@PathVariable int  id ){
        return  hotelService.getHotelById(id);
    }
    // Get all hotels
    @GetMapping("/hotels")
    public ResponseEntity<?> getAllDestinations ( ){
        return  hotelService.getHotels ( );
    }
    // add a hotel
    @PostMapping("/hotel")
    public ResponseEntity <?> addDestination( @RequestBody Hotel hotel ){
        return  hotelService.addtHotel(hotel );

    }
    @DeleteMapping("/hotel/{id}")
    public ResponseEntity <?> deleteDestination ( @PathVariable int id ){
        return   hotelService.deleteHotelbyId(id);
    }
    @PutMapping("/hotel/{id}")
    public ResponseEntity<?> UpdateDestination (@PathVariable int id  , @RequestBody Hotel hotel)
    {
        return  hotelService.updateHotel(id , hotel);
    }
}
