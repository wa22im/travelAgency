package com.ditraacademy.travelagency.core.voyage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoyageController {

    @Autowired
    VoyageServices voyageServices ;

    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> GetVoyage (@PathVariable int  id ){
        return  voyageServices.getVoyage(id);
    }

    @GetMapping("/voyages")
    public ResponseEntity<?> GetAllVoyages ( ){
        return  voyageServices.getAllVoyages ( );
    }

    @PostMapping("/voyage")
    public ResponseEntity <?> AddVoyage(@RequestBody Voyage voyage ){
        return  voyageServices.addVoyage( voyage);

    }
    @DeleteMapping("/voyage/{id}")
    public ResponseEntity <?> DeleteVoyage ( @PathVariable int id ){
        return   voyageServices.deleteVoyage(id);
    }
    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> UpdateVoyage (@PathVariable int id , @RequestBody Voyage voyage  )
    {
        return  voyageServices.updateVoyage ( id , voyage);
    }
}
