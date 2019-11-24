package com.ditraacademy.travelagency.core.chambre.chambre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class ChambreController  {
    @Autowired
    ChambreServices chambreServices ;

    @PostMapping("/chambre")
    public ResponseEntity<?> addChambre(@RequestBody Chambre chambre ) {
        return  chambreServices.addChambre(chambre);
    }

//    @GetMapping("/chambre/{id}")
//    public ResponseEntity<?> getAllChambre ( ){
//
//    }

}
