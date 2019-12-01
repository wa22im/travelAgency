package com.ditraacademy.travelagency.core.chambre.typechambre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeChambreRepository extends JpaRepository<TypeChambre,Integer> {

    Optional<TypeChambre> findByType(String type );
}
