package com.ditraacademy.travelagency.core.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelService  {
    @Autowired
    HotelRepository hotelRepository;
    public ResponseEntity<?> addtHotel(Hotel hotel){
        return   new ResponseEntity<>(hotelRepository.save(hotel), HttpStatus.OK);

    }
//    public  ResponseEntity <?> deleteHotel ( int id ){
//        return
//    }


}
