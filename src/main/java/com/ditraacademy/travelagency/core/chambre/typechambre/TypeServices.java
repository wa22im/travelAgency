package com.ditraacademy.travelagency.core.chambre.typechambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeServices {


    @Autowired
    TypeChambreRepository typeChambreRepository ;


    public ResponseEntity<?> addType( TypeChambre typeChambre){
        Optional<TypeChambre> optionalTypeChambre = typeChambreRepository.findByType(typeChambre.getType());
        if ( !optionalTypeChambre.isPresent())
            return  new ResponseEntity<>(new ErrorResponseModel("type exist deja "), HttpStatus.BAD_REQUEST);

        if ( typeChambre.getType() ==null || typeChambre.equals(""))
        return  new ResponseEntity<>(new ErrorResponseModel(" Champ type obligatoire  "), HttpStatus.BAD_REQUEST);

        typeChambreRepository.save(typeChambre);
        return  new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity <?> updateType (int id , TypeChambre typeChambre) {
    Optional<TypeChambre> optionalTypeChambre = typeChambreRepository.findById(id);
    if (!optionalTypeChambre.isPresent())
        return new ResponseEntity<>(new ErrorResponseModel("id n'exist pas "), HttpStatus.BAD_REQUEST);
    TypeChambre typeChambreLegacy = optionalTypeChambre.get();

    if (typeChambre.getType().equals("") || typeChambre.getType() == null)
        return new ResponseEntity<>(new ErrorResponseModel("Error : donne votre mise a jour"), HttpStatus.BAD_REQUEST);

    typeChambreLegacy.setType(typeChambre.getType());

    typeChambreRepository.save(typeChambreLegacy);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity <?> getTypebyId (int id ){


        Optional<TypeChambre> optionalTypeChambre  = typeChambreRepository.findById(id);

        if ( !optionalTypeChambre.isPresent()){
            return  new ResponseEntity<>(new ErrorResponseModel("id n'existe pas"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optionalTypeChambre.get(),HttpStatus.OK);
}

    public  ResponseEntity<?> getAlltypes ( ){
        return  new ResponseEntity<>(typeChambreRepository.findAll(),HttpStatus.OK);
}

    public  ResponseEntity<?> deleteTypeById(int id ){

        Optional<TypeChambre> optionalTypeChambre  = typeChambreRepository.findById(id);

        if ( !optionalTypeChambre.isPresent()){
            return  new ResponseEntity<>(new ErrorResponseModel("id n'existe pas"), HttpStatus.BAD_REQUEST);
        }

        typeChambreRepository.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
