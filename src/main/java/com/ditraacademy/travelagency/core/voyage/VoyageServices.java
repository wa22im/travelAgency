package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {

    @Autowired
    VoyageRepository voyageRepository ;
    // put
    public ResponseEntity<?> updateVoyage(int id , Voyage voyage ) {
        Optional<Voyage> optionalVoyage =  voyageRepository.findById(id);

        if (!optionalVoyage.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel(" Invalid voyage"), HttpStatus.BAD_REQUEST);

        Voyage voyageLegacy = optionalVoyage.get();

        if ( voyage.getDate()!=null ||   voyageLegacy.getDate() != voyage.getDate())
            voyageLegacy.setDate(voyage.getDate());

        if (   voyage.getDescription()!=null || !(voyageLegacy.getDescription().equals(voyage.getDescription())))
            voyageLegacy.setDescription(voyage.getDescription());

        if (  voyage.getNbPlaces()!=voyageLegacy.getNbPlaces())
            voyageLegacy.setNbPlaces(voyage.getNbPlaces());

        if ( voyage.getPrix()!=voyageLegacy.getPrix())
            voyageLegacy.setPrix(voyage.getPrix());

        if ( voyage.getTitre()!= null || !(voyage.getTitre().equals(voyageLegacy.getTitre())))
            voyageLegacy.setTitre(voyage.getTitre());

        voyageRepository.save(voyageLegacy);

        return  new ResponseEntity<>(HttpStatus.OK) ; }


    // Get a voyage
    public ResponseEntity<?> getVoyage(int id) {
        Optional<Voyage> optionalVoyage  =  voyageRepository.findById(id);
        if(!(optionalVoyage.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel("ID not found "), HttpStatus.BAD_REQUEST); }
        return  new ResponseEntity<>(optionalVoyage.get(),HttpStatus.OK) ;
         }


    // Get all voyages
    public ResponseEntity<?> getAllVoyages() {
        List<Voyage> voyages = voyageRepository.findAll();
        return  new  ResponseEntity<>(voyages ,HttpStatus.OK) ;
         }


    // post  a voyage
    public ResponseEntity<?> addVoyage( Voyage voyage) {
        voyage  = voyageRepository.save(voyage);
        return  new ResponseEntity<>(voyage,HttpStatus.OK);
       }



    // Delete voyage
    public ResponseEntity<?> deleteVoyage(int id) {
        Optional< Voyage > optionalVoyage =  voyageRepository.findById(id);

        if (!(optionalVoyage.isPresent())) {
            new ResponseEntity<>(new ErrorResponseModel("id not found"), HttpStatus.BAD_REQUEST);
        }
        voyageRepository.deleteById(id);
        return   new ResponseEntity<>(HttpStatus.OK)  ;
    }

}
