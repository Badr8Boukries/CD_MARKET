package org.example.cd_market.services;


import org.example.cd_market.models.Film;
import org.example.cd_market.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Film> searchFilmByTitle(String inputTitle) {
        List<Film> allFilms = filmRepository.findAll(); // Récupère tous les films
        String[] inputWords = inputTitle.toLowerCase().split(" "); // Découpe la recherche utilisateur en mots
        List<Film> matchingFilms = new ArrayList<>();

        // Pour chaque film dans la base de données
        for (Film film : allFilms) {
            String[] filmWords = film.getTitre().toLowerCase().split(" "); // Découpe le titre du film en mots

            int matchCount = 0; // Compteur de mots correspondants
            for (String word : inputWords) {
                for (String filmWord : filmWords) {
                    if (filmWord.startsWith(word) || filmWord.contains(word)) {
                        matchCount++;
                    }
                }
            }

            double similarity = (double) matchCount / inputWords.length;

            //  similarité à 80%
            if (similarity >= 0.8) {
                matchingFilms.add(film);
            }
        }


        return matchingFilms;
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