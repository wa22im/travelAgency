package com.ditraacademy.travelagency.core.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User     {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;
    private String name ;
    private int age ;
    //@UniqueElements
    private  String mail ;
    private String role = "USER";

    private boolean activated = false ;
    private String activateToken ;
   // @JsonIgnore
    private  String password ;


}
