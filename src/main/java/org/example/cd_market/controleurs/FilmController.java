package org.example.cd_market.controleurs;


import org.example.cd_market.models.Film;
import org.example.cd_market.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Film> searchFilmByTitle(@RequestParam String title) {
        Film film = filmService.searchFilmByTitle(title);
        return film != null ? ResponseEntity.ok(film) : ResponseEntity.notFound().build();
    }

    @GetMapping("/byPrice")
    public List<Film> getFilmsByMaxPrice(@RequestParam Double maxPrice) {
        return filmService.getFilmsByMaxPrice(maxPrice);
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.ok().build();
    }
}