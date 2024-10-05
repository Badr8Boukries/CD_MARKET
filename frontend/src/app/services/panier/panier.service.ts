// src/app/services/panier.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Panier } from "../../models/panier.model";

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  private baseUrl = 'http://localhost:8080/panier'; // Changez cela selon votre configuration

  constructor(private http: HttpClient) { }

  // Récupérer le panier
  getPanier(): Observable<Panier> {
    return this.http.get<Panier>(`${this.baseUrl}/paniers`);
  }

  // Supprimer un film du panier
  removeFromPanier(filmId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${filmId}`);
  }

  // Effectuer un achat pour un film spécifique
  acheterFilms(filmId: number): Observable<void> {
    return this.http.post<void>(`http://localhost:8080/achats/achter/${filmId}`, {}); // Mettre à jour l'URL ici
  }
  acheterToutLePanier(): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/acheter-tout`, {});
  }

}
