package org.example.cd_market.services;


import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierService {
    @Autowired
    private PanierRepository panierRepository;

    public Panier createPanier() {
        return panierRepository.save(new Panier());
    }

    public Optional<Panier> getPanierById(Long id) {
        return panierRepository.findById(id);
    }

    public Panier addFilmToPanier(Long panierId, Film film) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            Panier panier = optionalPanier.get();
            panier.getFilms().add(film);
            return panierRepository.save(panier);
        }
        return null;
    }

    public Panier removeFilmFromPanier(Long panierId, Film film) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            Panier panier = optionalPanier.get();
            panier.getFilms().remove(film);
            return panierRepository.save(panier);
        }
        return null;
    }

    public void clearPanier(Long panierId) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        optionalPanier.ifPresent(panier -> {
            panier.getFilms().clear();
            panierRepository.save(panier);
        });
    }
}