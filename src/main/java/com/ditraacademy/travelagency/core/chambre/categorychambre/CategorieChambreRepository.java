package com.ditraacademy.travelagency.core.chambre.categorychambre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieChambreRepository extends JpaRepository<CategorieChambre , Integer> {

    Optional<CategorieChambre> findCategorieChambreByTypeEquals(String type);
}
