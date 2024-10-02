// src/app/services/film.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import { Film } from '../models/film.model';
import { Panier } from '../models/panier.model';
import { Achat } from '../models/achat.model';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  private baseUrl = 'http://localhost:8080';  // URL de ton back-end

  constructor(private http: HttpClient) { }

  // Récupérer tous les films
// src/app/services/film.service.ts
  getFilms(): Observable<Film[]> {
    console.log('Appel à l\'API pour récupérer les films...');
    return this.http.get<Film[]>(`${this.baseUrl}/films`).pipe(
      tap((data) => console.log('Données reçues :', data)) // Affiche les données dans la console
    );
  }


  // Rechercher des films par titre
  searchFilms(titre: string): Observable<Film[]> {
    return this.http.get<Film[]>(`${this.baseUrl}/films/search?title=${titre}`);
  }

  // Ajouter un film au panier
  addToPanier(panierId: number, filmId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/paniers/${panierId}/add`, { filmId });
  }

  // Récupérer le contenu du panier
  getPanier(panierId: number): Observable<Panier> {
    return this.http.get<Panier>(`${this.baseUrl}/paniers/${panierId}`);
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
}
