package org.example.cd_market.services;


import org.example.cd_market.models.Film;
import org.example.cd_market.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public List<Film> searchFilmByTitle(String title) {
        // Utilise findByTitreContainingIgnoreCase pour récupérer une liste de films
        return filmRepository.findByTitreContainingIgnoreCase(title);
    }
    public List<Film> getFilmsByMaxPrice(Double maxPrice) {
        return filmRepository.findByPrixLessThanEqual(maxPrice);
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}