package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.core.chambre.chambre.ChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ChambreRepository chambreRepository ;

    public ResponseEntity<?> addtHotel(Hotel hotel) {
        return new ResponseEntity<>(hotelRepository.save(hotel), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteHotelbyId(int id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            return new ResponseEntity<>(new ErrorResponseModel("id n'exist pas "), HttpStatus.BAD_REQUEST);

        }

        hotelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getHotelById(int id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            new ResponseEntity<>(new ErrorResponseModel("id nexist pas "), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optionalHotel.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> getHotels() {
        return new ResponseEntity<>(hotelRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<?> updateHotel(int id, Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) {
            return new ResponseEntity<>(new ErrorResponseModel("id hotel n'exist pas !"), HttpStatus.BAD_REQUEST);
        }
        Hotel hotelLegacy = optionalHotel.get();
        if (hotel.getAddresse() != null || !hotel.getAddresse().equals(hotelLegacy.getAddresse()))
            hotelLegacy.setAddresse(hotel.getAddresse());
        if (hotel.getDescription() != null || !hotel.getDescription().equals(hotelLegacy.getDescription()))
            hotelLegacy.setDescription(hotel.getDescription());
        if (hotel.getEtoile() != hotelLegacy.getEtoile())
            hotelLegacy.setEtoile(hotel.getEtoile());
        if (hotel.getName() != null || !hotel.getName().equals(hotelLegacy.getName())) {
            hotelLegacy.setName(hotel.getName());
        }

        if (hotel.getChambres() != null) {
            hotelLegacy.addChambers(hotel.getChambres());
        }

        hotelRepository.save(hotelLegacy);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> addChambersToHotel (int id , List<Chambre> chambreList){
        for ( Chambre chambre : chambreList ){
            Optional<Chambre > chambreOptional  = chambreRepository.findById(chambre.getId());
            if ( ! chambreOptional.isPresent()){
                return  new ResponseEntity<>( new ErrorResponseModel( " le Chamber avec l id "+ chambre.getId()
                +"n'existe pas "), HttpStatus.BAD_REQUEST );

            }

            Optional<Hotel> optionalHotel = hotelRepository.findById(id);

            if ( !optionalHotel.isPresent()){
            return  new ResponseEntity<>( new ErrorResponseModel("Id hotel incorrecte "),HttpStatus.BAD_REQUEST) ;

            }

            Hotel hotel =  optionalHotel.get();

            hotel.addChambers(chambre);

        }
        return  new ResponseEntity<>( HttpStatus.OK);


    }
}