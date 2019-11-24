package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Hotel extends Audible<String> {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    @Column(columnDefinition = "text")

    private String description ;
    private int etoile ;
    private String addresse ;
    private String telephone ;
    @JsonIgnore
     @ManyToMany
     @JoinTable(name = "hotel_chambre",

     joinColumns = {@JoinColumn(name="hotel_id")},
             inverseJoinColumns = {@JoinColumn (name = "chambre_id")}

     )
    List<Chambre>chambres ;
    @JsonIgnore
    private boolean deleted = false ;

}
