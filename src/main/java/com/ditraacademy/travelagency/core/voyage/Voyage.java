package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update voyage set deleted = true where id=?")
public class Voyage extends Audible<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;
    private String titre ;
    @Column (columnDefinition = "text")
    private  String description  ;
    private Date date ;
    private  int nbPlaces ;
    private  double prix ;
    @ManyToOne( cascade = {CascadeType.REMOVE , CascadeType.PERSIST })
    private Destination destination ;
    @JsonIgnore
    private boolean deleted = false ;

}
