package org.example.cd_market.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double prix;

    private String imageUrl;
}