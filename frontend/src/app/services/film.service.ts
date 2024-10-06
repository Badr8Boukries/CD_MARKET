import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, throwError, tap, catchError} from 'rxjs';
import { Film } from '../models/film.model';
import { Panier } from '../models/panier.model';
import { Achat } from '../models/achat.model';

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  private baseUrl = 'http://localhost:8080';  // URL de ton back-end

  constructor(private http: HttpClient) {}

  // Récupérer tous les films
  getFilms(): Observable<Film[]> {

    return this.http.get<Film[]>(`${this.baseUrl}/films`).pipe(
      tap((data) => console.log('Données reçues :', data)) // Affiche les données dans la console
    );
  }

  // Ajouter un film au panier
  addToPanier(filmId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/panier/add/${filmId}`, {});
  }



  // Supprimer un film du panier
  removeFromPanier(panierId: number, filmId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/paniers/${panierId}/remove`, { filmId });
  }

  // Acheter les films dans le panier
  acheterFilms(panierId: number): Observable<Achat> {
    return this.http.post<Achat>(`${this.baseUrl}/achats/buy`, { panierId });
  }

  // Récupérer les achats
  getAchats(): Observable<Achat[]> {
    return this.http.get<Achat[]>(`${this.baseUrl}/achats`);
  }
  searchFilmByTitle(title: string): Observable<Film[]> {
    return this.http.get<Film[]>(`${this.baseUrl}/films/search?title=${title}`);
  }



}
