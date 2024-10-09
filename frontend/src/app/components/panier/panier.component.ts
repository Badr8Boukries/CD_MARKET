// src/app/components/panier/panier.component.ts

import { Component, OnInit } from '@angular/core';
import { Panier } from '../../models/panier.model';
import { Film } from '../../models/film.model';
import { PanierService } from "../../services/panier/panier.service";

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  panier: Panier | null = null;

  constructor(private panierService: PanierService) { }

  ngOnInit() {
    this.chargerPanier();
  }

  chargerPanier() {
    this.panierService.getPanier().subscribe(
      data => this.panier = data
    );
  }

  getPanier(): void {
    this.panierService.getPanier().subscribe(data => {
      this.panier = data;
    }, error => {
      console.error('Erreur lors de la récupération du panier', error);
      alert('Erreur lors de la récupération du panier.');
    });
  }

  supprimerDuPanier(film: Film): void {
    this.panierService.removeFromPanier(film.id).subscribe(() => {
      alert(`${film.titre} a été supprimé du panier.`);
      this.getPanier(); // Rafraîchir le panier
    }, error => {
      console.error('Erreur lors de la suppression du film', error);
      alert('Erreur lors de la suppression du film.');
    });
  }

  acheter(film: Film): void {
    if (!this.panier || this.panier.films.length === 0) {
      alert('Votre panier est vide.');
      return;
    }

    if (confirm(`Êtes-vous sûr de vouloir acheter ${film.titre} ?`)) {
      this.panierService.acheterFilms(film.id).subscribe(() => {
        alert(`Achat réussi pour ${film.titre} !`);
        this.supprimerDuPanier(film); // Appeler supprimerDuPanier ici
      }, error => {
        console.error('Erreur lors de l\'achat', error);
        alert('Erreur lors de l\'achat.');
      });
    }
  }
  acheterToutLePanier() {
    if (!this.panier || this.panier.films.length === 0) {
      alert('Votre panier est vide.');
      return;
    }

    this.panierService.acheterToutLePanier().subscribe({
      next: (response) => {
        console.log('Achat réussi', response);
      },
      error: (error) => {
        console.error('Erreur lors de l\'achat:', error);
        // Affichez un message d'erreur à l'utilisateur
        alert('Erreur lors de l\'achat. Vérifiez votre panier et réessayez.');
      }
    });

  }


  calculerTotal(): number {
    if (!this.panier || !this.panier.films) {
      return 0;
    }
    return this.panier.films.reduce((total, film) => total + film.prix, 0);
  }
}
