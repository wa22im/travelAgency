package com.ditraacademy.travelagency.core.chambre.chambre;


import com.ditraacademy.travelagency.core.chambre.categorychambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.categorychambre.CategorieChambreRepository;
import com.ditraacademy.travelagency.core.chambre.typechambre.TypeChambre;
import com.ditraacademy.travelagency.core.chambre.typechambre.TypeChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChambreServices  {

    @Autowired
    ChambreRepository chambreRepository ;
    @Autowired
    TypeChambreRepository typeChambreRepository ;
    @Autowired
    CategorieChambreRepository categorieChambreRepository ;
    public ResponseEntity<?> addChambre(Chambre chambre){
        Optional<CategorieChambre>  categorieChambreOptional = categorieChambreRepository.findById(chambre.getCategorieChambre().getId());


        if (!(categorieChambreOptional.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel(" Categorie not found "),HttpStatus.BAD_REQUEST);
        }
        CategorieChambre categorieChambre =  categorieChambreOptional.get();

       Optional<TypeChambre> typeChambreRepositoryOptional =  typeChambreRepository.findById(chambre.getTypeChambre().getId()) ;




        if (!(typeChambreRepositoryOptional.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel(" Type not found "),HttpStatus.BAD_REQUEST);
        }
        TypeChambre  typeChambre= typeChambreRepositoryOptional.get();

        Optional <Chambre> chambreOptional = chambreRepository.findChambreByCategorieChambreAndTypeChambre(categorieChambre,typeChambre);
        if ( !(chambreOptional.isPresent())){
            return  new ResponseEntity<>(new ErrorResponseModel("Exist "),HttpStatus.BAD_REQUEST);

        }
        chambreRepository.save(chambreOptional.get());
        try {
              chambre =  chambreRepository.save(chambreOptional.get());
              return  new ResponseEntity<>(chambre,HttpStatus.OK);
        }catch (Exception ex) {

        }



       return new ResponseEntity<>( chambreRepository.save(chambre), HttpStatus.OK);
    }
    public  ResponseEntity<?> getAllChambres ( ) {
        return  new ResponseEntity<>(chambreRepository.findAll() , HttpStatus.OK);

    }
    public ResponseEntity<?> deleteChambreById ( int id ){
        chambreRepository.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
