// src/main/java/org/example/cd_market/services/AchatService.java
package org.example.cd_market.services;

import org.example.cd_market.models.Achat;
import org.example.cd_market.models.Film;
import org.example.cd_market.repositories.AchatRepository;
import org.example.cd_market.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private FilmRepository filmRepository;

    // Acheter un seul film par ID
    public Achat acheter(Long filmId) {
        Film film = filmRepository.findById(filmId).orElse(null);

        if (film == null) {
            return null; // Film non trouvé
        }

        Achat achat = new Achat();
        achat.setDateAchat(LocalDateTime.now());
        achat.setFilms(List.of(film));

        return achatRepository.save(achat);
    }

    // Acheter tous les films dans le panier
    public Achat acheterTous(List<Film> films) {
        if (films == null || films.isEmpty()) {
            return null; // Pas de films dans le panier
        }

        Achat achat = new Achat();
        achat.setDateAchat(LocalDateTime.now());
        achat.setFilms(films); // Associer les films à l'achat

        return achatRepository.save(achat);
    }

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public List<Achat> getAchatsBetweenDates(LocalDateTime debut, LocalDateTime fin) {
        return achatRepository.findByDateAchatBetween(debut, fin);
    }
}