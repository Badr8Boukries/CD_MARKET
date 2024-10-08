// src/app/models/achat.model.ts
import { Film } from './film.model';

export class Achat {
  id: number;
  dateAchat: Date;
  films: Film[];  // Tableau de films associés à l'achat

  constructor(id: number, dateAchat: Date, films: Film[]) {
    this.id = id;
    this.dateAchat = dateAchat;
    this.films = films;
  }
}
