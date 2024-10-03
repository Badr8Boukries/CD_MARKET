package org.example.cd_market.controleurs;


import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.services.FilmService;
import org.example.cd_market.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/panier")
@CrossOrigin(origins = "http://localhost:4200")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private FilmService filmService;

    // Ajouter un film au panier unique via son ID
    @PostMapping("/add/{filmId}")
    public ResponseEntity<Panier> addFilmToPanier(@PathVariable Long filmId) {
        Optional<Film> filmOptional = filmService.getFilmById(filmId);
        if (!filmOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Film film = filmOptional.get();
        return panierService.addFilmToPanier(film); // Appel à la méthode mise à jour
    }

    // Supprimer un film du panier unique via son ID
    @PostMapping("/remove/{filmId}")
    public ResponseEntity<Panier> removeFilmFromPanier(@PathVariable Long filmId) {
        Optional<Film> filmOptional = filmService.getFilmById(filmId);
        if (!filmOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Film film = filmOptional.get();  // Obtenez le film
        Panier updatedPanier = panierService.removeFilmFromPanier(film);
        return ResponseEntity.ok(updatedPanier);
    }

    // Vider le panier unique
    @PostMapping("/clear")
    public ResponseEntity<Void> clearPanier() {
        panierService.clearPanier();
        return ResponseEntity.ok().build();
    }

    // Récupérer le panier unique

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilmsDansLePanier() {
        List<Film> films = panierService.getAllFilmsDansLePanier();
        return ResponseEntity.ok(films);
    }
}