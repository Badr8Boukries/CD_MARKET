// src/app/components/panier/panier.component.ts
import { Component, OnInit } from '@angular/core';
import { FilmService } from '../../services/film.service';
import { Panier } from '../../models/panier.model';
import { Film } from '../../models/film.model';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  panier: Panier | null = null;
  panierId: number = 1; // Supposons un panier avec ID 1 pour simplifier

  constructor(private filmService: FilmService) { }

  ngOnInit(): void {
    this.getPanier();
  }

  getPanier(): void {
    this.filmService.getPanier(this.panierId).subscribe(data => {
      this.panier = data;
    }, error => {
      console.error('Erreur lors de la récupération du panier', error);
      alert('Erreur lors de la récupération du panier.');
    });
  }

  supprimerDuPanier(film: Film): void {
    this.filmService.removeFromPanier(this.panierId, film.id).subscribe(() => {
      alert(`${film.titre} a été supprimé du panier.`);
      this.getPanier(); // Rafraîchir le panier
    }, error => {
      console.error('Erreur lors de la suppression du film', error);
      alert('Erreur lors de la suppression du film.');
    });
  }

  acheter(): void {
    if (!this.panier || this.panier.films.length === 0) {
      alert('Votre panier est vide.');
      return;
    }

    if (confirm('Êtes-vous sûr de vouloir acheter les films dans votre panier ?')) {
      this.filmService.acheterFilms(this.panierId).subscribe(() => {
        alert('Achat réussi !');
        this.getPanier(); // Vider le panier après achat
      }, error => {
        console.error('Erreur lors de l\'achat', error);
        alert('Erreur lors de l\'achat.');
      });
    }
  }
}
