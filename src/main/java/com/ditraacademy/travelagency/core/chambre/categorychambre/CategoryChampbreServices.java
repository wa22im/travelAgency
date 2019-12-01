package com.ditraacademy.travelagency.core.chambre.categorychambre;


import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
public class CategoryChampbreServices {

    @Autowired
    CategorieChambreRepository categorieChambreRepository;


    public ResponseEntity<?> addCatergorie (CategorieChambre categorieChambre ) {

        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findCategorieChambreByTypeEquals(categorieChambre.getType());
        if ( categorieChambreOptional.isPresent())
        {
            return  new ResponseEntity<>( new ErrorResponseModel("Type exist deja "), HttpStatus.BAD_REQUEST);

        }
        categorieChambreRepository.save(categorieChambre);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    public  ResponseEntity<?> deleteCategorie ( int id )   {
        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(id);
        if ( !categorieChambreOptional.isPresent()){
            return  new ResponseEntity<>(new ErrorResponseModel("Id not found"),HttpStatus.BAD_REQUEST);
        }
        categorieChambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);


    }
    public ResponseEntity<?> updateCategorie( int id  , CategorieChambre categorieChambre){
        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(id);
        if ( !categorieChambreOptional.isPresent()){
            return  new ResponseEntity<>(new ErrorResponseModel("Id not found"),HttpStatus.BAD_REQUEST);
        }
        CategorieChambre categorieChambreLegacy = categorieChambreOptional.get();

        if ( categorieChambreLegacy.getType() == null || categorieChambreLegacy.getType().equals("")){

        return  new ResponseEntity<>(new ErrorResponseModel("Type is empty"), HttpStatus.BAD_REQUEST);
        }

        categorieChambreLegacy.setType(categorieChambre.getType());
        categorieChambreRepository.save(categorieChambreLegacy);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public  ResponseEntity<?> getCategorieById( int id ){
        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(id);
        if ( !categorieChambreOptional.isPresent()){
            return  new ResponseEntity<>(new ErrorResponseModel("Id not found "), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(categorieChambreOptional.get(),HttpStatus.OK);

    }
    public ResponseEntity<?> getAllCategories(){
        return  new ResponseEntity<>( categorieChambreRepository.findAll(),HttpStatus.OK);
    }



}
