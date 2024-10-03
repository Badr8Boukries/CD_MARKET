// src/app/components/film-list/film-list.component.ts
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

  ajouterAuPanier(film: Film): void {
    this.filmService.addToPanier(film.id).subscribe(
      () => {
        alert(`${film.titre} a été ajouté au panier.`);
        console.log('le film est ajouter avec suceeeeeeeeeeeeeee');

      },
      (error) => {
        console.error('Erreur lors de l\'ajout au panier', error);
        alert('Erreur lors de l\'ajout au panier : ce film existe deja sur le panier  ' );
      }
    );
  }

  clearSearch(): void {
    this.searchTerm = '';
    this.getFilms();
  }
  onSearch(): void {
    this.getFilms();
  }
}
