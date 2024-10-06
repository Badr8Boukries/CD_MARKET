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
  filmsPaginated: Film[] = [];
  searchTerm: string = '';

  // Pagination variables
  currentPage: number = 1;
  itemsPerPage: number = 10; // On affiche 15 films par page
  totalPages: number = 0;

  constructor(private filmService: FilmService) { }

  ngOnInit(): void {
    this.getFilms();
  }

  // Méthode pour récupérer tous les films
  getFilms(): void {
    this.filmService.getFilms().subscribe(
      data => {
        this.films = data;
        this.totalPages = Math.ceil(this.films.length / this.itemsPerPage);
        this.updateFilmsPaginated(); // Met à jour les films paginés
      },
      error => {
        console.error('Erreur lors de la récupération des films', error);
      }
    );
  }

  // Mise à jour de la liste paginée des films
  updateFilmsPaginated(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.filmsPaginated = this.films.slice(startIndex, endIndex);
  }

  // Navigation vers la page suivante
  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updateFilmsPaginated();
    }
  }

  // Navigation vers la page précédente
  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateFilmsPaginated();
    }
  }

  // Recherche de films
  onSearch() {
    if (this.searchTerm.trim()) {
      this.filmService.searchFilmByTitle(this.searchTerm)
        .subscribe({
          next: (films) => {
            if (films.length > 0) {
              this.films = films;
              this.totalPages = Math.ceil(this.films.length / this.itemsPerPage);
              this.currentPage = 1; // Revenir à la première page après la recherche
              this.updateFilmsPaginated();
            } else {
              alert("Aucun film ne correspond à votre recherche.");
              this.films = [];
              this.filmsPaginated = []; // Réinitialiser films paginés
              this.totalPages = 0; // Réinitialiser le nombre de pages
            }
          },
          error: () => {
            alert("Erreur lors de la recherche. Veuillez réessayer.");
            this.films = [];
            this.filmsPaginated = []; // Réinitialiser films paginés
            this.totalPages = 0; // Réinitialiser le nombre de pages
          }
        });
    } else {
      alert("Veuillez entrer un titre de film.");
    }
  }

  // Réinitialiser la recherche
  clearSearch(): void {
    this.searchTerm = '';
    this.getFilms(); // Réafficher tous les films
  }

  // Ajouter un film au panier
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
}
