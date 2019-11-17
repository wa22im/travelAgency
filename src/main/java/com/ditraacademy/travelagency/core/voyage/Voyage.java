package com.ditraacademy.travelagency.core.voyage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;
    private String titre ;
    private  String description  ;
    private Date date ;
    private  int nbPlaces ;
    private  float prix ;


}
