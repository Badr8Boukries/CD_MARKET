package org.example.cd_market.repositories;


import org.example.cd_market.models.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AchatRepository extends JpaRepository<Achat, Long> {
    List<Achat> findByDateAchatBetween(LocalDateTime debut, LocalDateTime fin);
}