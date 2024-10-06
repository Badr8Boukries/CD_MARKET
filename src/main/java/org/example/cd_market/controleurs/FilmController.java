package org.example.cd_market.controleurs;


import org.example.cd_market.models.Film;
import org.example.cd_market.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "http://localhost:4200")

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
    public ResponseEntity<List<Film>> searchFilmByTitle(@RequestParam String title) {
        List<Film> films = filmService.searchFilmByTitle(title);

        return !films.isEmpty() ? ResponseEntity.ok(films) : ResponseEntity.notFound().build();
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