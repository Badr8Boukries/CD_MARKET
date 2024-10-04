package org.example.cd_market.services;

import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PanierService {
    private PanierRepository panierRepository;
    private Panier panierUnique;

    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
        initPanier();
    }

    public void initPanier() {
        if (panierRepository.count() == 0) {
            panierUnique = new Panier();
            panierRepository.save(panierUnique);
        } else {
            panierUnique = panierRepository.findAll().get(0);
        }
    }

    public ResponseEntity<Panier> addFilmToPanier(Film film) {
        if (panierUnique.getFilms().contains(film)) {
            return ResponseEntity.badRequest().body(null);
        }

        panierUnique.getFilms().add(film);
        return ResponseEntity.ok(panierRepository.save(panierUnique));
    }

    public Panier removeFilmFromPanier(Film film) {
        panierUnique.getFilms().remove(film);
        return panierRepository.save(panierUnique);
    }

    public void clearPanier() {
        panierUnique.getFilms().clear();
        panierRepository.save(panierUnique);
    }

    public List<Film> getAllFilmsDansLePanier() {
        if (panierUnique != null) {
            System.out.println("Films dans le panier: " + panierUnique.getFilms());
            return panierUnique.getFilms();
        }
        return new ArrayList<>();
    }

    public Panier getPanierUnique() {
        return panierUnique;
    }

    // Nouvelle méthode pour acheter un film
    public ResponseEntity<Panier> acheterFilm(Long filmId) {
        // Rechercher le film par ID
        Film filmToBuy = panierUnique.getFilms().stream()
                .filter(film -> film.getId().equals(filmId))
                .findFirst()
                .orElse(null);

        if (filmToBuy == null) {
            return ResponseEntity.notFound().build(); // Film non trouvé dans le panier
        }

        // Supprimer le film du panier
        panierUnique.getFilms().remove(filmToBuy);
        panierRepository.save(panierUnique);

        // Ici, vous pouvez ajouter la logique pour traiter l'achat (paiement, etc.)
        System.out.println("Film acheté : " + filmToBuy.getTitre());

        return ResponseEntity.ok(panierUnique); // Retourner le panier mis à jour
    }

    public void supprimerFilmDuPanier(Long filmId) {
        if (panierUnique != null) {
            panierUnique.getFilms().removeIf(film -> film.getId().equals(filmId));
            panierRepository.save(panierUnique); // Enregistrer les modifications dans la base de données
        }
    }


}
