package org.example.cd_market.controleurs;


import org.example.cd_market.models.Achat;
import org.example.cd_market.models.Film;
import org.example.cd_market.services.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/achats")
public class AchatController {
    @Autowired
    private AchatService achatService;

    @PostMapping("/buy")
    public ResponseEntity<Achat> buyFilm(@RequestBody Film film) {
        Achat achat = achatService.buyFilm(film);
        return ResponseEntity.ok(achat);
    }

    @GetMapping
    public List<Achat> getAllAchats() {
        return achatService.getAllAchats();
    }

    @GetMapping("/between")
    public List<Achat> getAchatsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return achatService.getAchatsBetweenDates(debut, fin);
    }
}