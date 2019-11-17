package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.security.krb5.internal.crypto.Des;

import java.util.Optional;

public class DestinationService {
    //TODO Destination Services GET POST UPDATE DELETE

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> updateDestination(int id , Destination destination) {
        return  null ;
    }

    public ResponseEntity<?> getDestination(int id) {
        return  null ;

    }

    public ResponseEntity<?> getAllDestinations() {
        return  null ;

    }

    public ResponseEntity<?> addDestination(Destination destination) {
        if (destination.getNom().length() < 2) {
            return new ResponseEntity<>(new ErrorResponseModel("Error nom destination non valide "),
                    HttpStatus.BAD_REQUEST);

        }
        if (destination.getDescription().length() < 2) {
            return new ResponseEntity<>(new ErrorResponseModel("Error nom destination non valide "),
                    HttpStatus.BAD_REQUEST);
        }
        destinationRepository.save(destination);
        return  new ResponseEntity<>(HttpStatus.OK); }


    public ResponseEntity<?> deleteDestination(int id) {
       Optional< Destination > destinationLegacy =  destinationRepository.findById(id);
        return   null ;

    }

}
