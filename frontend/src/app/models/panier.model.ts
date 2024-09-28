// src/app/models/panier.model.ts
import { Film } from './film.model';

export interface Panier {
  id: number;
  films: Film[];
}
