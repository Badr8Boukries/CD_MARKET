package org.example.cd_market.controleurs;


import org.example.cd_market.models.Film;
import org.example.cd_market.models.Panier;
import org.example.cd_market.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {
    @Autowired
    private PanierService panierService;

    @PostMapping
    public Panier createPanier() {
        return panierService.createPanier();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanier(@PathVariable Long id) {
        return panierService.getPanierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Panier> addFilmToPanier(@PathVariable Long id, @RequestBody Film film) {
        Panier updatedPanier = panierService.addFilmToPanier(id, film);
        return updatedPanier != null ? ResponseEntity.ok(updatedPanier) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<Panier> removeFilmFromPanier(@PathVariable Long id, @RequestBody Film film) {
        Panier updatedPanier = panierService.removeFilmFromPanier(id, film);
        return updatedPanier != null ? ResponseEntity.ok(updatedPanier) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/clear")
    public ResponseEntity<Void> clearPanier(@PathVariable Long id) {
        panierService.clearPanier(id);
        return ResponseEntity.ok().build();
    }
}
