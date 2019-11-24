package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorychambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.typechambre.TypeChambre;
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
@SQLDelete(sql = "Update chambre  set deleted = true where id=?")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type_chambre_id","categorie_chambre_id"})
})
public class Chambre extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @ManyToOne
    private CategorieChambre categorieChambre;

    @ManyToOne
    private TypeChambre typeChambre;
    @JsonIgnore
    private boolean deleted = false ;


}
