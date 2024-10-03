package org.example.cd_market.services;

import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PanierService {
    private PanierRepository panierRepository;
    private Panier panierUnique;

    // Initialisation dans le constructeur
    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
        initPanier();
    }

    // Créer un panier unique lors de l'initialisation
    public void initPanier() {
        if (panierRepository.count() == 0) {
            panierUnique = new Panier();
            panierRepository.save(panierUnique);
        } else {
            panierUnique = panierRepository.findAll().get(0);
        }
    }

    // Ajouter un film au panier unique
    public ResponseEntity<Panier> addFilmToPanier(Film film) {
        // Vérifiez si le film est déjà dans le panier
        if (panierUnique.getFilms().contains(film)) {
            return ResponseEntity.badRequest().body(null); // ou un message d'erreur personnalisé
        }

        panierUnique.getFilms().add(film);
        return ResponseEntity.ok(panierRepository.save(panierUnique));
    }

    // Supprimer un film du panier unique
    public Panier removeFilmFromPanier(Film film) {
        panierUnique.getFilms().remove(film);
        return panierRepository.save(panierUnique);
    }

    // Vider le panier unique
    public void clearPanier() {
        panierUnique.getFilms().clear();
        panierRepository.save(panierUnique);
    }

    // Récupérer tous les films dans le panier
    public List<Film> getAllFilmsDansLePanier() {
        if (panierUnique != null) {
            System.out.println("Films dans le panier: " + panierUnique.getFilms());
            return panierUnique.getFilms();
        }
        return new ArrayList<>(); // Retourne une liste vide si le panier est nul
    }
}
