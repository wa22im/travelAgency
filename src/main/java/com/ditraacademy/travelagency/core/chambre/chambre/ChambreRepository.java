package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorychambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.typechambre.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChambreRepository extends JpaRepository<Chambre , Integer > {

    Optional<Chambre> findChambreByCategorieChambreAndTypeChambre(CategorieChambre categorieChambre , TypeChambre typeChambre);
}
