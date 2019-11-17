package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.voyage.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class DestinationController  {
    @Autowired
    DestinationService destinationService ;

    /* Get A Destination */
    @GetMapping("/destination/{id}")
    public ResponseEntity<?> GetVoyage (@PathVariable int  id ){
        return  destinationService.getDestination(id);
    }
    // Get all destinations
    @GetMapping("/destinations")
    public ResponseEntity<?> GetAllVoyages ( ){
        return  destinationService.getAllDestinations ( );
    }
    // add a destination
    @PostMapping("/destination")
    public ResponseEntity <?> AddVoyage( @RequestBody Destination destination ){
        return  destinationService.addDestination(destination);

    }
    @DeleteMapping("/destination/{id}")
    public ResponseEntity <?> DeleteVoyage ( @PathVariable int id ){
        return   destinationService.deleteDestination(id);
    }
    @PutMapping("/destination/{id}")
    public ResponseEntity<?> UpdateVoyage (@PathVariable int id  , @RequestBody Destination destination)
    {
        return  destinationService.updateDestination ( id,destination);
    }

}
