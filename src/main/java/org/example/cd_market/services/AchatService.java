package org.example.cd_market.services;

import org.example.cd_market.models.Achat;
import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.repositories.AchatRepository;
import org.example.cd_market.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchatService {
    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private PanierRepository panierRepository; // Injecter le repository du panier

    public Achat acheter(Long filmId) {

        Panier panier = panierRepository.findAll().get(0); // Supposons qu'il n'y a qu'un seul panier

        // Rechercher le film par ID dans le panier
        Film filmToBuy = panier.getFilms().stream()
                .filter(film -> film.getId().equals(filmId))
                .findFirst()
                .orElse(null);

        if (filmToBuy == null) {
            return null; // Film non trouvé dans le panier
        }

        // Créer un nouvel achat
        Achat achat = new Achat();
        achat.setFilm(filmToBuy);
        achat.setDateAchat(LocalDateTime.now());

        // Supprimer le film du panier
        panier.getFilms().remove(filmToBuy);
        panierRepository.save(panier); // Mettre à jour le panier

        return achatRepository.save(achat); // Enregistrer l'achat
    }

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public List<Achat> getAchatsBetweenDates(LocalDateTime debut, LocalDateTime fin) {
        return achatRepository.findByDateAchatBetween(debut, fin);
    }
}
