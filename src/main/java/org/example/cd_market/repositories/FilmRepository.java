package org.example.cd_market.repositories;


import org.example.cd_market.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByTitreContainingIgnoreCase(String titre);
    List<Film> findByPrixLessThanEqual(Double prix);
}
