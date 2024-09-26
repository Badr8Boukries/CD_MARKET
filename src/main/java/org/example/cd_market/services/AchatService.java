package org.example.cd_market.services;


import org.example.cd_market.models.Achat;
import org.example.cd_market.models.Film;
import org.example.cd_market.repositories.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchatService {
    @Autowired
    private AchatRepository achatRepository;

    public Achat buyFilm(Film film) {
        Achat achat = new Achat();
        achat.setFilm(film);
        achat.setDateAchat(LocalDateTime.now());
        return achatRepository.save(achat);
    }

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public List<Achat> getAchatsBetweenDates(LocalDateTime debut, LocalDateTime fin) {
        return achatRepository.findByDateAchatBetween(debut, fin);
    }
}