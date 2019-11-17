package com.ditraacademy.travelagency.core.voyage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VoyageServices {

    @Autowired
    VoyageRepository voyageRepository ;
    // put
    public ResponseEntity<?> updateVoyage(int id , Voyage voyage ) {
        return  null ; }
    // Get a voyage
    public ResponseEntity<?> getVoyage(int id) {
        return  null ; }
    // Get all voyages
    public ResponseEntity<?> getAllVoyages() {
        return  null ; }
    // post  a voyage
    public ResponseEntity<?> addVoyage( Voyage voyage) {
        return  null ; }
    // Delete voyage
    public ResponseEntity<?> deleteVoyage(int id) {
        return  null ; }

}
