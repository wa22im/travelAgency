package com.ditraacademy.travelagency.core.voyage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Voyage")
public class VoyageController {

    @Autowired
    VoyageServices voyageServices ;

    @GetMapping("/voyage/{id}")
    @ApiOperation(value = "Voyage", response = Voyage.class)
    public ResponseEntity<?> GetVoyage (@PathVariable int  id ){
        return  voyageServices.getVoyage(id);
    }

    @GetMapping("/voyages")
    public ResponseEntity<?> GetAllVoyages ( ){
        return  voyageServices.getAllVoyages ( );
    }

    @PostMapping("/voyage")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
    @GetMapping("/voyages/byprice")
    public  ResponseEntity<?> findVoyagesBetween(@RequestParam double min ,@RequestParam double max){
        return  voyageServices.findVoyagesBetween(min,max);
    }
    @GetMapping("/voyages/bypriceAndnbplace")
    public  ResponseEntity<?> findVoyagesBetweenAndNbplace(@RequestParam double min ,@RequestParam double max ,@RequestParam int nbplace){
        return  voyageServices.findVoyagesBetweenAndNbplace(min,max,nbplace);
    }
}
