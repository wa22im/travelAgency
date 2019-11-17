package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.ditraacademy.travelagency.utils.AcceptedResponseModel;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;
import java.util.Optional;

public class DestinationService {
    //TODO Destination Services GET POST UPDATE DELETE

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> updateDestination(int id , Destination destination) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if ( !(destinationOptional.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel("Id not found "), HttpStatus.BAD_REQUEST); }
        Destination destinationlegacy = destinationOptional.get();
        if ( destination.getNom()!=null || destination.getNom()!=""){
            destinationlegacy.setNom(destination.getNom());
        }
        if ( destination.getDescription()!=null || destination.getDescription()!=""){
            destinationlegacy.setDescription(destination.getDescription());
        }
        destinationRepository.deleteById(id);
        destinationRepository.save(destinationlegacy);

        return  new ResponseEntity<>(new AcceptedResponseModel("Updated "), HttpStatus.OK) ;
    }


    public ResponseEntity<?> getDestination(int id) {
        Optional<Destination> destinationOptional =  destinationRepository.findById(id);
        if(!(destinationOptional.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel("ID not found "), HttpStatus.BAD_REQUEST); }
        return  new ResponseEntity<>(destinationOptional.get(),HttpStatus.OK) ;
    }


    public ResponseEntity<?> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return  new  ResponseEntity<>(destinations ,HttpStatus.OK) ;
    }


    public ResponseEntity<?> addDestination(Destination destination) {
        if (destination.getNom().length() < 2) {
            return new ResponseEntity<>(new ErrorResponseModel("Error nom destination non valide "),
                    HttpStatus.BAD_REQUEST); }
        destinationRepository.save(destination);
        return  new ResponseEntity<>(HttpStatus.OK); }


    public ResponseEntity<?> deleteDestination(int id) {
       Optional< Destination > destinationOptional =  destinationRepository.findById(id);

        if (!(destinationOptional.isPresent())) {
            new ResponseEntity<>(new ErrorResponseModel("id not found"), HttpStatus.BAD_REQUEST);
        }
        destinationRepository.deleteById(id);
        return   new ResponseEntity<>(new AcceptedResponseModel("Deleted"),HttpStatus.OK)  ;

    }

}
