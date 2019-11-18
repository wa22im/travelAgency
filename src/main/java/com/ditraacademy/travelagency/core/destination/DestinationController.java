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
    public ResponseEntity<?> getDestination (@PathVariable int  id ){
        return  destinationService.getDestination(id);
    }
    // Get all destinations
    @GetMapping("/destinations")
    public ResponseEntity<?> getAllDestinations ( ){
        return  destinationService.getAllDestinations ( );
    }
    // add a destination
    @PostMapping("/destination")
    public ResponseEntity <?> addDestination( @RequestBody Destination destination ){
        return  destinationService.addDestination(destination);

    }
    @DeleteMapping("/destination/{id}")
    public ResponseEntity <?> deleteDestination ( @PathVariable int id ){
        return   destinationService.deleteDestination(id);
    }
    @PutMapping("/destination/{id}")
    public ResponseEntity<?> UpdateDestination (@PathVariable int id  , @RequestBody Destination destination)
    {
        return  destinationService.updateDestination ( id,destination);
    }

}
