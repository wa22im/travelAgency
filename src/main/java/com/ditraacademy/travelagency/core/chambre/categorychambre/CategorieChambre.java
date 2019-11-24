package com.ditraacademy.travelagency.core.chambre.categorychambre;

import com.ditraacademy.travelagency.core.hotel.Hotel;
import com.ditraacademy.travelagency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update categorie_chambre  set deleted = true where id=?")
public class CategorieChambre extends Audible<String> {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id ;
    @Column(columnDefinition = "text")
    private String type ;
    @JsonIgnore
    @ManyToMany (mappedBy = "chambres")
    private List<Hotel> hotels ;
    @JsonIgnore
    private boolean deleted = false ;
}
