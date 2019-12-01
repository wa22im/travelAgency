package com.ditraacademy.travelagency.core.chambre.typechambre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypeController {

    @Autowired
    TypeServices typeServices ;
    @GetMapping("/type/{id}")
    public ResponseEntity<?> getTypeById(@PathVariable int id ){
                 return  typeServices.getTypebyId(id);
    }

    @GetMapping("/types")
    public  ResponseEntity<?> getAllTypes(){
        return typeServices.getAlltypes();
    }
    @PostMapping("/type")
    public  ResponseEntity<?> addType(@RequestBody  TypeChambre typeChambre){
        return  typeServices.addType(typeChambre);
    }
    @PutMapping("/type/id")
    public  ResponseEntity<?> updateType(@RequestBody TypeChambre typeChambre , @PathVariable int id){
        return  typeServices.updateType(id , typeChambre);
    }
    @DeleteMapping("/type/id")
    public ResponseEntity<?> deleteType (@PathVariable int id ){
        return typeServices.deleteTypeById(id);  }

}
