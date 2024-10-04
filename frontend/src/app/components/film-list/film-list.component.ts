import { Component, OnInit } from '@angular/core';
import { FilmService } from '../../services/film.service';
import { Film } from '../../models/film.model';

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent implements OnInit {
  films: Film[] = [];
  searchTerm: string = '';
  panierId: number = 1; // Supposons un panier avec ID 1 pour simplifier

  constructor(private filmService: FilmService) { }

  ngOnInit(): void {
    this.getFilms();
  }

  getFilms(): void {
    this.filmService.getFilms().subscribe(
      data => {
        console.log('Films récupérés :', data);
        this.films = data;
      },
      error => {
        console.error('Erreur lors de la récupération des films', error);
      }
    );
  }

  ajouterAuPanier(film: Film) {
    if (film.id) {
      this.filmService.addToPanier(film.id)
        .subscribe({
          next: () => {
            alert('Film ajouté au panier avec succès');
          },
          error: (err) => {
            console.error('Erreur lors de l\'ajout au panier', err);
            alert('Erreur lors de l\'ajout au panier');
          }
        });
    } else {
      console.error('Erreur : L\'ID du film est manquant');
      alert('Impossible d\'ajouter ce film au panier car l\'ID est manquant.');
    }
  }


  clearSearch(): void {
    this.searchTerm = '';
    this.getFilms();
  }

  onSearch() {
    if (this.searchTerm.trim()) {
      this.filmService.searchFilmByTitle(this.searchTerm)
        .subscribe({
          next: (films) => {
            if (films.length > 0) {
              this.films = films;
            } else {
              alert("Ce film ne se trouve pas pour le moment.");
              this.films = []; // Réinitialise la liste de films
            }
          },
          error: () => {
            alert("Erreur lors de la recherche. Veuillez réessayer.");
            this.films = []; // Réinitialise la liste de films
          }
        });
    } else {
      alert("Veuillez entrer un titre de film.");
    }
  }



}
