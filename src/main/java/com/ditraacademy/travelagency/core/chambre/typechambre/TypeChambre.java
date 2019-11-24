package com.ditraacademy.travelagency.core.chambre.typechambre;

import com.ditraacademy.travelagency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update type_chambre  set deleted = true where id=?")
public class TypeChambre extends Audible<String > {

    @Id
    @GeneratedValue ( strategy =  GenerationType.IDENTITY)
    private int id ;
    @Column(columnDefinition = "text")
    private String type ;
    @JsonIgnore
    private boolean deleted = false ;

}
