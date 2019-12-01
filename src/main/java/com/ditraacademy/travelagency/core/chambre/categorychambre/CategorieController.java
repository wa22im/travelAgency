package com.ditraacademy.travelagency.core.chambre.categorychambre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {
    @Autowired
    CategoryChampbreServices categoryChampbreServices ;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories (){
        return categoryChampbreServices.getAllCategories();
    }



}
